/**
 * junix – Unix specific functions for Java
 * 
 * Copyright © 2013  Mattias Andrée (maandree@member.fsf.org)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package junix;


/**
 * Process methods
 * 
 * @author  Mattias Andrée  <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Proc
{
    /**
     * Non-constructor
     */
    private Proc()
    {
	assert false : "Do not create instances of junix.Proc";
    }
    
    
    
    public static native long fork(); // fork (should throw exceptions)
    
    public static native int alarm(final int seconds); // alarm
    
    public static native int alarm(final int microseconds, final int interval); // ualarm
    
    public static native int cancelAlarm(); // alarm(0)
    
    public static String[] cmdline()
    {
	try
	{   final InputStream is = new FileInputStream("/proc/self/cmdlin");
	    byte[] data = new byte[256];
	    int ptr = 0;
	    for (;;)
	    {
		int av = is.available();
		if (av == 0)
		    break;
		if (ptr + av > data.length)
		    System.arraycopy(data, 0, data = new byte[ptr + av], 0, ptr);
		ptr += rs.read(data, ptr);
	    }
	    is.close();
	    String rc = new String(data, 0, ptr, "UTF-8");
	    if (rc.endsWith("\0"))
		rc = rc.substring(0, rc.length() - 1);
	    return rc.split("\0");
	}
	catch (final IOException err)
	{   throw new IOError("Cannot use the API filesystem proc, it should be mounted on /proc.", err);
	}
    }
    
    
    // void chroot(final String path)
    // String getWorkingDirectory()
    // void setWorkingDirectory(final String path)
    // void setWorkingDirectory(final int fd)
    // /proc/self/fd/
    // man 3 tcsetpgrp
    // man 3 tcgetpgrp
    // man 2 dup
    // man 2 pipe
    // set environment
    // open process with arbitrary fd:s
    // getppid getpid
    // forkpty kill killpg nice plock
    // man 2 sched_*
}


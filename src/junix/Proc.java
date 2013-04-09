/**
 * junix – Unix specific functions for Java
 * 
 * Copyright © 2013  Mattias Andrée (maandree@member.fsf.org)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
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
    
    
    
    // void chroot(final String path)
    // String getWorkingDirectory()
    // void setWorkingDirectory(final String path)
    // void setWorkingDirectory(final int fd)
    // /proc/self/cmdline
    // /proc/self/fd/
    // man 3 tcsetpgrp
    // man 3 tcgetpgrp
    // man 2 dup
    // man 2 pipe
    // set environment
    // open process with arbitrary fd:s
    // getppid getpid
    // fork forkpty kill killpg nice plock
    // man 2 sched_*
}


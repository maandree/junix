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

import java.util.*;


/**
 * File system functions
 * 
 * @author  Mattias Andrée  <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Path
{
    /**
     * Non-constructor
     */
    private Path()
    {
	assert false : "Do not create instances of junix.Path";
    }
    
    
    
    public boolean isSameFile(final int fd1, final int fd2)
    {
	final Stat stat1 = stat(fd1);
	final Stat stat2 = stat(fd2);
	
	return (stat1.inode  == stat2.inode)  && 
	       (stat1.device == stat2.device) && 
	       (stat1.size   == stat2.size);
    }
    
    public boolean isSameFile(final String path1, final String path2)
    {
	final Stat stat1 = stat(path1);
	final Stat stat2 = stat(path2);
	
	return (stat1.inode  == stat2.inode)  && 
	       (stat1.device == stat2.device) && 
	       (stat1.size   == stat2.size);
    }
    
    public boolean isMountPoint(final String path)
    {
	return isMountPointByMtab(path) || isMountPointByStat(path);
    }
    
    public boolean isMountPointByStat(final String path)
    {
	if (path.replace("/", "").length() == 0)
	    return true;
	
	final String current = (new File(path)).getAbsolutePath();
	final String parent = current.substring(0, current.lastIndexOf('/'));
	
	final Stat stat1 = stat(current);
	final Stat stat2 = stat(parent);
	
	return stat1.device != stat2.device;
    }
    
    public boolean isMountPointByMtab(final String path)
    {
	final String abs = (new File(path)).getAbsolutePath();
	String mtab = "/proc/self/mounts";
	
	try (final InputStream is = new FileInputStream(new File(mtab)))
	{
	    byte[] data = new byte[10240];
	    byte[] buf = new byte[1024];
	    int ptr = 0;
	    for (;;)
	    {   int read = is.read(buf);
		if (read <= 0)
		    break;
		if (ptr + read > data.length)
		    System.arraycopy(data, 0, data = new byte[ptr << 1], 0, ptr);
		System.arraycopy(buf, 0, data, ptr, read);
		ptr += read;
	    }
	    mtab = new String(data, 0, ptr, "UTF-8");
	}
	
	if (mtab.endsWith("\n"))
	    mtab = mtab.substring(0, mtab.length() - 1);
	
	final String[] mounts = mtab.split("\n");
	for (final String mount : mounts)
	    if (abs.equals(mount.split(" ")[1]))
		return true;
	return false;
    }
    
    public Mount[] getMounts()
    {
	String mtab = "/proc/self/mounts";
	
	try (final InputStream is = new FileInputStream(new File(mtab)))
	{
	    byte[] data = new byte[10240];
	    byte[] buf = new byte[1024];
	    int ptr = 0;
	    for (;;)
	    {   int read = is.read(buf);
		if (read <= 0)
		    break;
		if (ptr + read > data.length)
		    System.arraycopy(data, 0, data = new byte[ptr << 1], 0, ptr);
		System.arraycopy(buf, 0, data, ptr, read);
		ptr += read;
	    }
	    mtab = new String(data, 0, ptr, "UTF-8");
	}
	
	if (mtab.endsWith("\n"))
	    mtab = mtab.substring(0, mtab.length() - 1);
	
	final String[] mounts = mtab.split("\n");
	final Mount[] rc = new Mount[mounts.length];
	
	for (int i = 0, n = rc.length; i < n; i++)
	    rc[i] = new Mount(mounts[i]);
	
	return rc;
    }
    
    // String readlink(final String path)
    // boolean isSymbolicLink(final String path)
    // boolean isDirectory(final String path)
    // boolean isRegularFile(final String path)
    // boolean isCharacterSpecialFile(final String path)
    // boolean isBlockSpecialFile(final String path)
    // boolean isHighPerformanceFile(final String path)
    // boolean isNamedPipe(final String path)
    // boolean isDomainSocket(final String path)
    // boolean isDoor(final String path)
    // boolean isOfflineFile(final String path)
    // boolean isNetworkSpecialFile(final String path)
    // boolean isPortFile(final String path)
    // void createSymbolicLink(final String target, final String link)
    // void createHardLink(final String target, final String link)
    // [f]stat[v]fs
    // stat (lstat)  fstat
    // int getMajor(final int device)
    // int getMinor(final int device)
    // void makeNode(final String path, final int mode=0600)
    // void makeNode(final String path, final int mode, final long device=0)
    // void makeNode(final String path, final int mode, final int major=0, final int minor=0)
    // void makeFIFO(final String path)
    // void makeFIFO(final String path, final int mode=0666)
    // void chown(final String path, final int owner, final int group)
    // void chown(final int fd, final int owner, final int group)
    // void chmod(final String path, final int mode)
    // void chmod(final int fd, final int mode)
    // os.chflags (python)
    // void access(final String path)
    // man 2 umask
    // flock lockf fallocate fadvise
    // getxattr listxattr removexattr setxattr
}


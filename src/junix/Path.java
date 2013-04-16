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
    
    
    
    public static boolean isSameFile(final int fd1, final int fd2)
    {
	final Stat stat1 = stat(fd1);
	final Stat stat2 = stat(fd2);
	
	return (stat1.inode  == stat2.inode)  && 
	       (stat1.device == stat2.device) && 
	       (stat1.size   == stat2.size);
    }
    
    public static boolean isSameFile(final String path1, final String path2)
    {
	final Stat stat1 = stat(path1);
	final Stat stat2 = stat(path2);
	
	return (stat1.inode  == stat2.inode)  && 
	       (stat1.device == stat2.device) && 
	       (stat1.size   == stat2.size);
    }
    
    public static boolean isMountPoint(final String path)
    {
	return isMountPointByMtab(path) || isMountPointByStat(path);
    }
    
    public static boolean isMountPointByStat(final String path)
    {
	if (path.replace("/", "").length() == 0)
	    return true;
	
	final String current = (new File(path)).getAbsolutePath();
	final String parent = current.substring(0, current.lastIndexOf('/'));
	
	final Stat stat1 = stat(current);
	final Stat stat2 = stat(parent);
	
	return stat1.device != stat2.device;
    }
    
    public static boolean isMountPointByMtab(final String path)
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
    
    public static Mount[] getMounts()
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
    
    public static boolean isNamedPipe(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x1000; // S_IFIFO
    }
    
    public static boolean isCharacterSpecialFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x2000; // S_IFCHR
    }
    
    public static boolean isMultiplexedCharacterSpecialFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x3000; // S_IFMPC
    }
    
    public static boolean isDirectory(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x4000; // S_IFDIR
    }
    
    public static boolean isNamedSpecialFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x5000; // S_IFNAM
    }
    
    public static boolean isNamedSemaphoreFile(final String path)
    {
	return (stat(path).mode & 0xF003) == 0x5001; // S_IFNAM | S_INSEM
    }
    
    public static boolean isNamedSharedDataFile(final String path)
    {
	return (stat(path).mode & 0xF003) == 0x5002; // S_IFNAM | S_INSHD
    }
    
    public static boolean isBlockSpecialFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x6000; // S_IFBLK
    }
    
    public static boolean isMultiplexedBlockSpecialFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x7000; // S_IFMPB
    }
    
    public static boolean isRegularFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x8000; // S_IFREG
    }
    
    public static boolean isNetworkSpecialFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x9000; // S_IFNWK
    }
    
    public static boolean isVxFSCompressedFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0x9000; // S_IFCMP
    }
    
    public static boolean isSymbolicLink(final String path)
    {
	return (stat(path).mode & 0xF000) == 0xA000; // S_IFLNK
    }
    
    public static boolean isShadowInode(final String path)
    {
	return (stat(path).mode & 0xF000) == 0xB000; // S_IFSHAD
    }
    
    public static boolean isDomainSocket(final String path)
    {
	return (stat(path).mode & 0xF000) == 0xC000; // S_IFSOCK
    }
    
    public static boolean isDoor(final String path)
    {
	return (stat(path).mode & 0xF000) == 0xD000; // S_IFDOOR
    }
    
    public static boolean isWhiteout(final String path)
    {
	return (stat(path).mode & 0xF000) == 0xE000; // S_IFWHT
    }
    
    public static boolean isPortFile(final String path)
    {
	return (stat(path).mode & 0xF000) == 0xE000; // S_IFPORT
    }
    
    public static boolean isHighPerformanceFile(final String path)
    {
	return (stat(path).mode & 0xF000) == -1; // XXX S_IFCTG
    }
    
    public static boolean isOfflineFileWithData(final String path)
    {
	return (stat(path).mode & 0xF000) == -1; // XXX S_IFOFD
    }
    
    public static boolean isOfflineFileWithoutData(final String path)
    {
	return (stat(path).mode & 0xF000) == -1; // XXX S_IFOFL
    }
    
    public static boolean testBehaviour(final String path)
    {
	testBehaviour(path, 1);
    }
    
    public static boolean testBehaviour(final String path, final int seekable)
    {
	testBehaviour(path, seekable, seekable > 0 ? 1 : 0, seekable > 0 ? 1 : 0);
    }
    
    public static boolean testBehaviour(final String path, final int seekable, final int finity, final int knownsize)
    {
	final int fmt = stat(path).mode & 0xF000;
	
	// TODO return false  if not  S_IFIFO S_IFCHR S_IFMPC S_IFBLK S_IFMPB S_IFREG S_IFCTG S_IFOFD S_IFOFL
	
	if (seekable > 0)
	{
	    // TODO return false  on  S_IFIFO S_IFCHR S_IFMPC
	}
	else if (seekable < 0)
	{
	    // TODO return false  on  S_IFBLK S_IFMPB S_IFREG S_IFCTG S_IFOFD S_IFOFL
	}
	
	if (finity > 0)
	{
	    // TODO return false  on  S_IFCHR S_IFMPC
	}
	else if (finity < 0)
	{
	    // TODO return false  on  S_IFBLK S_IFMPB S_IFREG S_IFCTG S_IFOFD S_IFOFL
	}
	
	if (knownsize > 0)
	{
	    // TODO return false  on  S_IFREG S_IFCTG S_IFOFD S_IFOFL
	}
	else if (knownsize < 0)
	{
	    // TODO return false  on  S_IFIFO S_IFCHR S_IFMPC S_IFBLK S_IFMPB
	}
	
	return true;
    }
    
    public static native long makeDevice(final int major, final int minor); // makedev
    
    public static native int getMajor(final long device); // major
    
    public static native int getMinor(final long device); // minor
    
    public static native int umask(final int mask); // umask
    
    // String readlink(final String path)
    // void createSymbolicLink(final String target, final String link)
    // void createHardLink(final String target, final String link)
    // [f]stat[v]fs
    // stat (lstat)  fstat
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
    // flock lockf fallocate fadvise
    // getxattr listxattr removexattr setxattr
    // man 7 sem_overview shm_overview mq_overview
}


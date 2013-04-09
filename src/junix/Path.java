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
    
    
    
    // boolean isSameFile(final int fd1, final int fd2)
    // boolean isSameFile(final String path1, final String path2)
    // boolean isMountPoint(final String path)
    // String readlink(final String path)
    // boolean isSymbolicLink(final String path)
    // boolean isDirectory(final String path)
    // boolean isRegularFile(final String path)
    // boolean isCharacterSpecialFile(final String path)
    // boolean isBlockSpecialFile(final String path)
    // boolean isHighPerformaceFile(final String path)
    // boolean isNamedPipe(final String path)
    // boolean isDomainSocket(final String path)
    // boolean isDoor(final String path)
    // boolean isOfflineFile(final String path)
    // boolean isNetworkSpecialFile(final String path)
    // boolean isPortFile(final String path)
    // void createSymbolicLink(final String target, final String link)
    // void createHardLink(final String target, final String link)
    // [f]stat[v]fs
    // stat (lstat)
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
    // /proc/self/mounts
    // lockf fallocate fadvise
    // getxattr listxattr removexattr setxattr
}


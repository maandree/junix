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
 * File information structure
 * 
 * @author  Mattias Andrée  <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Stat
{
    /**
     * Device
     */
    public final long device;
    
    /**
     * File serial number
     */
    public final long inode;
    
    /**
     * Protection bits
     */
    public final int mode;
    
    /**
     * Link count
     */
    public final int nlink;
    
    /**
     * User ID of the file's owner
     */
    public final int uid;
    
    /**
     * Group ID of the file's group
     */
    public final int gid;
    
    /**
     * Device number, if device
     */
    public final long rdevice;
    
    /**
     * Size of file, in bytes
     */
    public final long size;
    
    /**
     * Optimal block size for I/O
     */
    public final int blocksize;
    
    /**
     * Number 512-byte blocks allocated
     */
    public final long blocks;
    
    /**
     * Time of last access
     */
    public final long atime;
    
    /**
     * Nanosecond of last access
     */
    public final long atime_nsec;
    
    /**
     * Time of last modification
     */
    public final long mtime;
    
    /**
     * Nanosecond of last modification
     */
    public final long mtime_nsec;
    
    /**
     * Time of last status change
     */
    public final long ctime;
    
    /**
     * Nanosecond of last status change
     */
    public final long ctime_nsec;
    
}


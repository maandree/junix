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
 * System methods
 * 
 * @author  Mattias Andrée  <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class System
{
    /**
     * Non-constructor
     */
    private System()
    {
	assert false : "Do not create instances of junix.System";
    }
    
    
    
    // /proc/self/loginuid
    // man 2 uname
    // setuid setsid getsid setreuid setresuid setresgid setregid setpgid
    // setpgrp setgroups setgid seteuid setegid getuid getresgid getresuid
    // getpgrp getpgid getlogin initgroups getgroups getgrouplist getgid
    // geteuid getegid ctermid
    // pathconf sysconf
    // ggetloadavg
    // /etc/{group,passwd,gshadow,shadow}
    // man 3 crypt
    // /etc/services
    // fcntl ioctl
    // http://docs.python.org/3.3/library/syslog.html
    // http://docs.python.org/3.3/library/resource.html
}


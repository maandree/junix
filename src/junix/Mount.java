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
 * File system mount structure
 * 
 * @author  Mattias Andrée  <a href="mailto:maandree@member.fsf.org">maandree@member.fsf.org</a>
 */
public class Mount
{
    /**
     * Constructor
     * 
     * @param  data  Information as printed by the kernel
     */
    protected Mount(final String data)
    {
	final String[] columns = data.split(" ");
	this.device = columns[0];
	this.mountPoint = columns[1];
	this.fileSystem = columns[2];
	this.options = columns[3].split(",");
	this.dump = Integer.parseInt(columns[4]);
	this.fsckOrder = Integer.parseInt(columns[5]);
    }
    
    
    /**
     * Mount device if applicable
     */
    public final String device;
    
    /**
     * Mount point
     */
    public final String mountPoint;
    
    /**
     * File system
     */
    public final String fileSystem;
    
    /**
     * Mount options
     */
    public final String[] options;
    
    /**
     * Used by the dump command, 0 to ignore
     */
    public final int dump;
    
    /**
     * Used by the fsck command (which order to check at boot), 0 to ignore
     */
    public final int fsckOrder;
    
}


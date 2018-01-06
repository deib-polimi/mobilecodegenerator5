/************************************************************************************************************************
 * Copyright 2017 Gregorio Perego, Stefania Pezzetti, Aldo Pintus, Alessio Rossotti, Massimo Beccari
 * This file is part of it.polimi.mobilecodegenerator.
 * 
 * it.polimi.mobilecodegenerator is free software: 
 * you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 * 
 * it.polimi.mobilecodegenerator is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.You should have received a copy of the GNU General Public License
 * along with it.polimi.mobilecodegenerator. If not, see <http://www.gnu.org/licenses/>
 * ***********************************************************************************************************************/

package app_extensions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppJavaUtil {
	
	public static String APPLICATION_NAME;
	
	/**
	 * Sets the application name
	 * @param applicationName
	 */
	public static void setApplicationName(String applicationName) {
		APPLICATION_NAME = applicationName;
	}
	
	/**
	 * Returns the list of user's files
	 * @return
	 */
	public static List<String> getUserFilesNames(){
		File folder = new File("utils/user_files");
		List<String> userFilesNames = new ArrayList<String>();
		for(File source : folder.listFiles()){
				userFilesNames.add(source.getName());
		}
		return userFilesNames;
	}

	/**
	 * Copies a file
	 * @param source
	 * @param destination
	 */
	public static void copyFile(File source, File destination){
		try {
			if(!destination.exists()){
				destination.createNewFile();
			}
			InputStream in = new FileInputStream(source);
			OutputStream out = new FileOutputStream(destination);
			byte[] buffer = new byte[1024];
			int length;
			while((length=in.read(buffer))>0){
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Deletes a folder and all its content
	 * @param folder
	 */
	public static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) {
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	public static boolean existsAtLeastOne(Collection<?> c) {
		Object[] array = c.toArray();
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the object is not null
	 * @return
	 */
	public static boolean existsObject(Object obj) {
		
		return obj != null;
	}
	
	/**
	 * Returns true if the relationship has an inverse
	 * @return
	 */
	public static boolean hasInverse(Relationship entRel, Collection<Entity> entities){
		
		for(Entity candidateEntity : entities){
			
			if(entRel.getDestination().equals(candidateEntity.getName())){
				
				ArrayList<Relationship> rels = candidateEntity.getEntityRelationships();
				for(Relationship rel : rels){
					if(rel.getDestination().equals(entRel)) return true;
				}
			}
		}
		return false;
	}
	
	public boolean isAMenuDestination(String name, List<?> menuItems) {
		for(int i = 0; i < menuItems.size(); i++) {
			if(name.equals(menuItems.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param isWidth is used to distinguish between width and height
	 * @param layoutEdgesConstrained gives the control's layout edges which are binded by position constraints
	 * @return if the control's dimension (width or height) is unconstrained
	 */
	public static boolean dimensionIsUnbinded(Boolean isWidth, List<?> layoutEdgesConstrained) {
		int n = 0;
		if(isWidth) {
			for(int i = 0; i < layoutEdgesConstrained.size(); i++)
				if((layoutEdgesConstrained.get(i).toString().equals("start")) || (layoutEdgesConstrained.get(i).toString().equals("end")))
					n++;
			if(n > 1)
				return false;
		} else {
			for(int i = 0; i < layoutEdgesConstrained.size(); i++)
				if((layoutEdgesConstrained.get(i).toString().equals("top")) || (layoutEdgesConstrained.get(i).toString().equals("bottom")))
					n++;
			if(n > 1)
				return false;
		}
		return true;
	}
	
	/**
	 * @param isPosX is used to distinguish between posX and posY
	 * @param layoutEdgesConstrained gives the control's layout edges which are binded by position constraints
	 * @return if the control's position (posX or posY) is unconstrained
	 */
	public static boolean positionIsUnbinded(Boolean isPosX, List<?> layoutEdgesConstrained) {
		if(isPosX) {
			for(int i = 0; i < layoutEdgesConstrained.size(); i++)
				if((layoutEdgesConstrained.get(i).toString().equals("start")) || (layoutEdgesConstrained.get(i).toString().equals("end")) || (layoutEdgesConstrained.get(i).toString().equals("centerX")))
					return false;
		} else {
			for(int i = 0; i < layoutEdgesConstrained.size(); i++)
				if((layoutEdgesConstrained.get(i).toString().equals("top")) || (layoutEdgesConstrained.get(i).toString().equals("bottom")) || (layoutEdgesConstrained.get(i).toString().equals("centerY")))
					return false;
		}
		return true;
	}
}

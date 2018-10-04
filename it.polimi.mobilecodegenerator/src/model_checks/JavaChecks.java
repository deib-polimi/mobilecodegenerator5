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

package model_checks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaChecks {

	/**
	 * Check if the application name matches ^[a-zA-Z]+
	 * 
	 * @param appName
	 * @return
	 */
	public static boolean isAppNameValid(String appName) {
		String regex = "^[a-zA-Z]+";
		if (!appName.matches(regex)) {
			return false;
		}
		return true;
	}

	/**
	 * Check if the company identifier matches ^[a-z]+(\\.[a-z]+)*
	 * 
	 * @param companyIdentifier
	 * @return
	 */
	public static boolean isCompanyIdentifierValid(String companyIdentifier) {
		String regex = "^[a-z]+(\\.[a-z]+)*";
		if (!companyIdentifier.matches(regex)) {
			return false;
		}
		return true;
	}

	/**
	 * Returns the list of invalid ids
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> invalidIds(List<String> ids) {
		String regex = "^[a-zA-Z][a-zA-Z0-9_]*";
		ArrayList<String> invalidIds = new ArrayList<String>();
		for (String id : ids) {
			if (!id.matches(regex)) {
				invalidIds.add(id);
				System.out.println("-->" + id);
			}
		}
		return invalidIds;
	}

	/**
	 * Returns the list of duplicated ids
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> duplicatedIds(List<String> ids) {
		ArrayList<String> duplicates = new ArrayList<String>();
		for (int i = 0; i < ids.size(); i++)
			for (int j = 0; j < ids.size(); j++) {
				if (ids.get(i).equals(ids.get(j)) && i != j && !duplicates.contains(ids.get(i))) {
					duplicates.add(ids.get(i));
					System.out.println("-->" + ids.get(i));
				}
			}
		return duplicates;
	}

	public static boolean isDestinationNotAMenuItem(String destination, List<String> menuItems) {
		for (int i = 0; i < menuItems.size(); i++) {
			if (destination.equals(menuItems.get(i).toString())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the list of invalid files
	 * 
	 * @return
	 */
	public static List<String> invalidFilesNames() {
		String regex = "^[a-z0-9_]+";
		File folder = new File("utils/user_files");
		ArrayList<String> invalidFilesNames = new ArrayList<String>();
		File[] files = folder.listFiles();
		if (files != null) { // i.e. if utils/user_files is a valid and existing
								// directory
			for (File source : files) {
				if (source.isDirectory() || source.getName().length() < 1 || source.getName().split("\\.").length != 2
						|| !source.getName().split("\\.")[0].matches(regex)) {
					invalidFilesNames.add(source.getName());
					System.out.println("-->" + source.getName());
				}
			}
		}
		return invalidFilesNames;
	}

	/**
	 * Returns the list of invalid files
	 * 
	 * @return
	 */
	public static List<String> invalidFilesExtensions() {
		File folder = new File("utils/user_files");
		ArrayList<String> invalidFilesExtensions = new ArrayList<String>();
		File[] files = folder.listFiles();
		if (files != null) {
			for (File source : folder.listFiles()) {
				String sourceFileExtension = source.getName().split("\\.")[1];
				if (!(sourceFileExtension.equalsIgnoreCase("html") || sourceFileExtension.equalsIgnoreCase("txt")
						|| sourceFileExtension.equalsIgnoreCase("css") || sourceFileExtension.equalsIgnoreCase("png")
						|| sourceFileExtension.equalsIgnoreCase("jpg") || sourceFileExtension.equalsIgnoreCase("gif")
						|| sourceFileExtension.equalsIgnoreCase("mp4") || sourceFileExtension.equalsIgnoreCase("3gp")
						|| sourceFileExtension.equalsIgnoreCase("mp3"))) {
					invalidFilesExtensions.add(source.getName());
					System.out.println("-->" + source.getName());
				}
			}
		}
		return invalidFilesExtensions;
	}

	/**
	 * Returns the list of duplicated files
	 * 
	 * @return
	 */
	public static List<String> duplicatedFilesNames() {
		File folder = new File("utils/user_files");
		ArrayList<String> duplicatedFilesNames = new ArrayList<String>();
		File[] files = folder.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				for (int j = 0; j < files.length; j++)
					if (i != j
							&& files[i].getName().split("\\.")[0]
									.equals(files[j].getName().split("\\.")[0])
							&& !duplicatedFilesNames.contains(files[i])) {
						duplicatedFilesNames.add(files[i].getName());
						System.out.println("-->" + files[i].getName());
					}
			}
		}
		return duplicatedFilesNames;
	}

	/**
	 * @param isWidth
	 *            is used to distinguish between width and height
	 * @param layoutEdgesConstrained
	 *            gives the control's layout edges which are binded by position
	 *            constraints
	 * @return if the control's dimension (width or height) is unconstrained
	 */
	public static boolean dimensionIsUnbinded(Boolean isWidth, List<?> layoutEdgesConstrained) {
		int n = 0;
		if (isWidth) {
			for (int i = 0; i < layoutEdgesConstrained.size(); i++)
				if ((layoutEdgesConstrained.get(i).toString().equals("start"))
						|| (layoutEdgesConstrained.get(i).toString().equals("end")))
					n++;
			if (n > 1)
				return false;
		} else {
			for (int i = 0; i < layoutEdgesConstrained.size(); i++)
				if ((layoutEdgesConstrained.get(i).toString().equals("top"))
						|| (layoutEdgesConstrained.get(i).toString().equals("bottom")))
					n++;
			if (n > 1)
				return false;
		}
		return true;
	}
}

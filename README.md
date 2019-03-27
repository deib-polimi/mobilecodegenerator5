# What's new in version 5.0 #

* added code generation for ReactNative
* added support for Protocode 5 and controller generation

# What's new in version 4.0 #

* added support for Protocode 4.0 and model generation
* updated for supporting the last features of Android Studio 3.0.1 (Gradle plug-in and support libraries)
* updated for supporting XCode 9.2

# Mobilecodegenerator #

Mobilecodegenerator is an application that generates Android (Java) applications and iOS (Swift) applications, translating a generic xml model into native source code.

## Installation ##

You have to import the it.polimi.mobilecodegenerator project in Eclipse:

1. Download an Eclipse distribution containing a stable version of Epsilon and EMF:
   http://www.eclipse.org/epsilon/download

2. Install the following 2 plugins in Eclipse (Help --> Install new software):
   * Xpand/Xtend:  http://download.eclipse.org/modeling/m2t/xpand/updates/nightly/
   * EMFT MWE:     http://download.eclipse.org/modeling/emft/mwe/updates/nightly/

3. Download and unzip the MobileCodeGenerator-master.zip

4. Import the it.polimi.mobilecodegenerator project in Eclipse:
    File --> Import --> General --> Existing Projects into Workspace


## How it works - Android and iOS ##

If you already have a model of the application generated with [Protocode], ignore 1-4.

To produce source code of your application you need to:

1. Open the metamodel.ecore file in src/metamodel folder

2. Right click on the Application element and select "Create Dynamic Instance"

3. Enter the application name and save the .xmi file in src/model folder

4. Open the .xmi file and define the application model through the graphical editor

5. Create a folder named "user_files" in utils directory and put in all files referenced by the model

6. Open the iOSAndAndroidGenerator.mwe file in src/workflow folder and change the value of the "model" property by setting the path of your .xmi file

7. Right click on the iOSAndAndroidGenerator.mwe file and select "Run As MWE Workflow"

8. You will find the generated Android and iOS application source code in src-gen folder


## How it works - ReactNative ##

If you already have a model of the application generated with [Protocode], ignore 1-4.

To produce source code of your application you need to:

1. Open the metamodel.ecore file in src/metamodel folder

2. Right click on the Application element and select "Create Dynamic Instance"

3. Enter the application name and save the .xmi file in src/model folder

4. Open the .xmi file and define the application model through the graphical editor

5. Open the ReactNativeGenerator.mwe file in src/workflow folder and change the value of the "model" property by setting the path of your .xmi file

6. Right click on the ReactNativeGenerator.mwe file and select "Run As MWE Workflow"

7. You will find the generated ReactNative application source code in src-gen folder

8. Follow instructions in ReactNative.md file (in this repository) to run the generated application

## Developers contacts ##

osioalberto@gmail.com - mobilecodegenerator 5

massimo.beccari@gmail.com - mobilecodegenerator 4

alessiorossotti@gmail.com - mobilecodegenerator 3

aldo.pintus@gmail.com - mobilecodegenerator 2

gregorio.perego@gmail.com - mobilecodegenerator

pezzettistefania@gmail.com - mobilecodegenerator






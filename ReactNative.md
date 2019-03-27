## Prerequisites

Ensure you have [Yarn](https://yarnpkg.com) installed. Even if good old NPM is fine, Yarn is much more performant in installing packages.

You will also need AndroidStudio to run applications on an emulated Android device, or XCode for an emulated iOS device (OSX only).

You can also run your application directly on your device, refer to [ReactNative documentation](https://facebook.github.io/react-native) for details.

## Environment setup

Open a terminal in the directory containing the generated code. Ensure it contains the `package.json` file.

Install dependencies

```
yarn install
```

Upgrade ReactNative environment and generated project files

```
react-native upgrade
```

Link native libraries

```
react-native link
```

If you are using a relational database or a map in your application, you may need to manually link the corresponding libraries. This is necessary because automatic linking feature is broken for these two packages. You can find detailed instructions here
* [Maps](https://github.com/react-native-community/react-native-maps)
* [Relational Database](https://github.com/andpor/react-native-sqlite-storage)

Sometimes the Firebase component (included in applications that use a cloud database) has linking problems too, in this case just refer to [this docs](https://github.com/invertase/react-native-firebase)

## Run on Android emulator

Start the emulator, either from AndroidStudio or from command line

```
emulator @<emulator_name>
```

Start the bundler 

```
react-native start
```

Build and install the application

```
react-native run-android
```

You may get silly errors about directories that cannot be created during build, just be patient and repeat the build command several times. Sooner or later you'll get it working.

## Debug on Android emulator

To spawn the console and read the logs, open a new terminal window and run 

```
react-native log-android
```

## Run on iOS emulator

You can find detailed instructions [here](https://facebook.github.io/react-native/docs/running-on-simulator-ios)

## Debug on iOS emulator

To spawn the console and read the logs, open a new terminal window and run 

```
react-native log-ios
```
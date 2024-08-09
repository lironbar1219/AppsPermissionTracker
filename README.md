# App Permissions Tracker

## Overview

**App Permissions Tracker** is an Android application designed to help users view the permissions requested by the apps installed on their device. The app displays a categorized list of permissions for each installed app, helping users better understand what access each app has to their device's resources.

## Features

- **List of Installed Apps**: Displays a list of all installed apps on the device.
- **Permission Categories**: Permissions are categorized (e.g., Camera, Location, Contacts) for better readability.
- **Real-time Permissions**: Shows the current permissions requested by each app.
- **Back Navigation**: Users can easily navigate back to the main list of apps.

## Screenshots

### Main Screen
- Displays a list of installed apps.

### Permissions Screen
- Shows categorized permissions for a selected app.

## Installation

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/lironbar1219/AppsPermissionTracker.git
   ```
2. Open the project in Android Studio.
3. Build the project to ensure all dependencies are installed.
4. Run the application on an emulator or a physical device.

## Usage

1. Launch the app to see a list of all installed apps on your device.
2. Tap on any app to view its permissions.
3. The permissions will be categorized (e.g., Camera, Location) for easy understanding.
4. Use the "Go Back" button to return to the main list of apps.

## Project Structure
* MainActivity.java: Displays the list of installed apps.
* PermissionActivity.java: Displays the permissions for the selected app.
* AppAdapter.java: Handles the display of app items in the RecyclerView.
* activity_main.xml: Layout for the main activity.
* activity_permission.xml: Layout for the permissions activity.

## Customization
* You can easily modify the categories or add new ones in PermissionActivity.java.
* The layout can be customized by editing the XML files in the res/layout directory.




# StudyTime - Professional Study Timer

<div align="center">

![StudyTime Logo](https://via.placeholder.com/150x150/2563EB/FFFFFF?text=StudyTime)

**A Professional Android Study Timer App with Pomodoro Technique**

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com/)
[![Java](https://img.shields.io/badge/Language-Java-orange.svg)](https://www.java.com/)
[![API Level](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)

</div>

---

## 📱 About The Project

**StudyTime** is a professional Android application designed to help students and professionals maintain focus during study or work sessions using the proven Pomodoro Technique. The app features a clean circular timer as the main screen, session history tracking, comprehensive statistics dashboard, and customizable settings with professional Material Design UI.

**Student Information:**
- **Name:** Sanjaya Singh Kunwor
- **Student Number:** 002678485
- **Course:** Mobile Development Skills
- **Institution:** Lappeenranta University of Technology (LUT)

---

## 🛠️ Technical Implementation

### Architecture & Components

**Activities:**
- `MainActivity` - Large circular timer as home screen with bottom navigation
- `HistoryActivity` - Session history with ListView
- `StatisticsActivity` - Analytics dashboard with 4 metrics
- `SettingsActivity` - Customization and preferences

**Data Models:**
- `FocusSession` - Session data with timestamps, duration, session type

**Database:**
- `DatabaseHelper` - SQLite database manager
- Schema for sessions table
- CRUD operations
- Aggregate queries for statistics

**Custom Adapters:**
- `SessionAdapter` - Custom ListView adapter for session history
- ViewHolder pattern for performance

### Technologies Used

- **Language:** Java
- **IDE:** Android Studio
- **Minimum SDK:** API 24 (Android 7.0 Nougat)
- **Target SDK:** API 34 (Android 14)
- **Database:** SQLite
- **UI Framework:** Material Design Components
- **Layout:** ConstraintLayout, LinearLayout, ScrollView
- **Components:** MaterialButton, MaterialCardView, TextInputLayout



---



## 🚀 Getting Started

### Prerequisites

- Android Studio Arctic Fox or newer
- Android SDK API 24 or higher
- Java Development Kit (JDK) 8 or higher
- Android device or emulator running Android 7.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/AndroidAssignment.git
   cd AndroidAssignment
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory
   - Click "OK"

3. **Sync Gradle Files**
   - Android Studio will automatically prompt you to sync
   - Click "Sync Now" in the notification bar
   - Wait for dependencies to download

4. **Build the Project**
   - Go to `Build > Make Project` (or press Ctrl+F9)
   - Wait for the build to complete
   - Check the Build Output for any errors

5. **Run on Emulator**
   - Go to `Tools > AVD Manager`
   - Create a new Virtual Device (recommended: Pixel 5, API 30+)
   - Click the Run button (green play icon) or press Shift+F10
   - Select your emulator from the device list

6. **Run on Physical Device**
   - Enable Developer Options on your Android device
   - Enable USB Debugging
   - Connect your device via USB
   - Click Run and select your device

### First Time Setup

1. Launch the app - you'll see the large circular timer on the main screen
2. The app comes pre-loaded with 12 sample sessions for demo
3. Tap Start to begin a Pomodoro session (25 minutes)
4. Use Pause/Resume and Reset buttons to control the timer
5. Navigate using bottom bar to explore History, Statistics, and Settings
6. Check Statistics tab to see aggregated data
7. Visit Settings to customize your preferences

---

## 📁 Project Structure

```
AndroidAssignment/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/rajan/androidassignment/
│   │   │   │   ├── adapters/
│   │   │   │   │   └── SessionAdapter.java
│   │   │   │   ├── database/
│   │   │   │   │   └── DatabaseHelper.java
│   │   │   │   ├── models/
│   │   │   │   │   └── FocusSession.java
│   │   │   │   ├── MainActivity.java
│   │   │   │   ├── HistoryActivity.java
│   │   │   │   ├── StatisticsActivity.java
│   │   │   │   └── SettingsActivity.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── activity_history.xml
│   │   │   │   │   ├── activity_statistics.xml
│   │   │   │   │   ├── activity_settings.xml
│   │   │   │   │   └── item_session.xml
│   │   │   │   ├── menu/
│   │   │   │   │   └── bottom_nav_menu.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   │   └── mipmap/ (app icons)
│   │   │   └── AndroidManifest.xml
│   │   └── androidTest/ (instrumented tests)
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
├── README.md
├── LEARNING_DIARY.md
├── FEATURES_LIST.md
└── VIDEO_LINK.md
```

---

## 🎥 Demo Video

A comprehensive video demonstration of all 7 features is available here:

**[Watch Demo Video](VIDEO_LINK.md)**

The video covers:
1. Main timer screen with circular countdown
2. Starting and pausing a Pomodoro session
3. Session counter and mode display
4. Viewing session history (ListView)
5. Checking statistics dashboard with 4 metrics
6. Exploring settings and customization options
7. Bottom navigation demonstration

## 👨‍💻 Developer

**Sanjaya Singh Kunwor**  
Student Number: 002678485  

---

## 📄 License

This project was created as part of the Mobile Development Skills course at Lappeenranta University of Technology (LUT) by Sanjaya Singh Kunwor (Student #002678485). It is available for educational and portfolio purposes.

---

## Acknowledgments

- LUT Mobile Development course instructors
- Material Design team for UI components
- Android Developer documentation
- Pomodoro Technique by Francesco Cirillo

---

<div align="center">

**Built with dedication by Sanjaya Singh Kunwor**

⭐ StudyTime - Professional Study Timer for Android

</div>

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

## ✨ Features

### 1. 🎯 Pomodoro Timer (Main Screen)
- Large circular timer display (280dp) as home screen
- Real-time countdown with minute:second format
- 3 quick preset buttons: Pomodoro (25m), Short Break (5m), Long Break (15m)
- Floating Action Buttons for Start/Pause and Reset
- Session counter tracking (1/4, 2/4, 3/4, 4/4 Pomodoro cycles)
- Clean, professional interface with no emojis

### 2. 📜 Session History (ListView)
- Complete list of all completed focus sessions
- Custom ListView with professional card design
- Shows: Session type, date/time, duration, completion status
- ViewHolder pattern for smooth scrolling performance
- 12 pre-loaded sample sessions for demo
- Empty state message for first-time users

### 3. 📊 Statistics Dashboard
- **4 Key Metrics:**
  - Total Focus Time (hours)
  - Sessions Completed (count)
  - Average Session Duration (minutes)
  - Total Distractions (count)
- Personalized insights section with dynamic messages
- Real-time data aggregation from SQLite database
- Professional card-based layout with Material Design

### 4. ⚙️ Settings & Customization
- **Timer Settings:**
  - Keep Screen On toggle
  - Sound Notifications toggle
  - Vibration toggle
- **Session Settings:**
  - Auto-start Break toggle
  - Daily Focus Goal (1-12 hours adjustable)
- **Data Management:**
  - Reset All Statistics with confirmation
- Professional app info section

### 5. 🔄 Auto Session Flow
- Automatic switch to break mode after focus completion
- Automatic return to focus mode after break ends
- Smart session counter (4-session Pomodoro cycles)
- Seamless transitions between modes
- Toast notifications for mode changes

### 6. 💾 Data Persistence System
- SQLite database for session history
- SharedPreferences for user settings
- Automatic data seeding (12 sample sessions)
- Complete CRUD operations
- Complex queries for statistics
- Session tracking with timestamps

### 7. 🎨 Professional Material Design UI
- Bottom Navigation Bar (4 tabs: Timer, History, Stats, Settings)
- Blue primary color scheme (#2563EB)
- MaterialCardView throughout
- Floating Action Buttons
- Switch toggles and modern controls
- NO emojis - all Material Design icons
- Responsive layouts with ConstraintLayout

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

### Key Android Concepts Demonstrated

✅ **Activities & Intents** - Navigation between 4 activities with bottom nav  
✅ **Activity Lifecycle** - Proper handling of onCreate, onResume, onDestroy  
✅ **SQLite Database** - Full CRUD operations with aggregate queries  
✅ **Custom ListView** - Custom adapter with ViewHolder pattern  
✅ **SharedPreferences** - Storing settings and user preferences  
✅ **Threading** - CountDownTimer for background countdown  
✅ **Material Design** - Cards, FABs, switches, bottom navigation  
✅ **Layouts** - ConstraintLayout, LinearLayout, ScrollView  
✅ **Resources** - Strings, colors, themes configuration  
✅ **Dialogs** - AlertDialog for confirmations  
✅ **Professional UI** - Clean design without emojis  

---

## 📸 Screenshots

### Main Timer Screen
- Large circular countdown timer (home screen)
- Preset duration buttons below timer
- Session counter display
- Floating action buttons for controls
- Bottom navigation bar

### Session History
- Professional card-based list design
- Session details with completion status
- Clean Material Design cards

### Statistics Dashboard
- 4 metric cards in grid layout
- Personalized insights section
- Real-time calculated data

### Settings
- All toggles and controls
- Daily goal adjuster
- Reset statistics option

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

---

## 🧪 Testing

### Manual Testing Performed

- ✅ Timer countdown accuracy
- ✅ Pause/Resume functionality
- ✅ Session completion flow
- ✅ Database CRUD operations
- ✅ ListView scrolling performance
- ✅ Achievement unlock conditions
- ✅ Statistics calculations
- ✅ Activity lifecycle handling
- ✅ Back button behavior
- ✅ Dialog interactions
- ✅ Empty state handling

### Known Limitations

- No cloud sync (local SQLite only)
- No background notifications
- No widget support
- Timer pauses when app is in background

---

## 🔮 Future Enhancements

- 📱 Home screen widget for quick access
- 🔔 Background notifications when timer completes
- ☁️ Cloud sync with Firebase
- 📈 Charts and graphs for progress visualization
- 🌙 Dark mode theme
- 🎯 Weekly/monthly goal tracking
- 📤 Export session data as CSV

---

## 👨‍💻 Developer

**Sanjaya Singh Kunwor**  
Student Number: 002678485  
Email: [your.email@example.com]  
LinkedIn: [Your LinkedIn Profile]  
GitHub: [@yourusername](https://github.com/yourusername)

---

## 📄 License

This project was created as part of the Mobile Development Skills course at Lappeenranta University of Technology (LUT) by Sanjaya Singh Kunwor (Student #002678485). It is available for educational and portfolio purposes.

---

## 🙏 Acknowledgments

- LUT Mobile Development course instructors
- Material Design team for UI components
- Android Developer documentation
- Pomodoro Technique by Francesco Cirillo

---

<div align="center">

**Built with dedication by Sanjaya Singh Kunwor**

⭐ StudyTime - Professional Study Timer for Android

</div>

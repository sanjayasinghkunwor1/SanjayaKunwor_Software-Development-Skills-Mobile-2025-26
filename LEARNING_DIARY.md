# LEARNING DIARY - MOBILE DEVELOPMENT MODULE

**Student Name:** Sanjaya Singh Kunwor  
**Student Number:** 002678485  
**Module:** Mobile Development Skills  
**Project:** StudyTime - Professional Study Timer Android App

---

## June 1, 2026

I started the mobile development module today. I carefully read through the general course information and understood that the main goal is to demonstrate practical Android development skills through a real project. The module focuses on:
- Understanding Android Studio environment
- Core Android components (Activities, Intents, Services)
- UI/UX development with layouts and custom components
- Using ListView with custom adapters
- Building a complete functional app

I set up my Android Studio environment and created a new project called "AndroidAssignment". The initial setup went smoothly after ensuring I had the correct SDK versions installed. I chose Java as my programming language since it's widely used in Android development and I'm comfortable with it.

I decided to build a **study timer app** (StudyTime) because it solves a real problem I face as a student - maintaining focus during study sessions using the Pomodoro Technique. This would also allow me to showcase multiple Android features in a meaningful, professional way.

---

## June 5, 2026

I watched Part 1 of the tutorial videos focusing on Android Studio basics. I learned:
- How to create and structure an Android project
- Understanding the project folder structure (app, res, java, manifests)
- How to run apps in the Android Emulator
- Debugging using Logcat and breakpoints

I practiced by creating simple activities and running them on the emulator. The gradle build system was initially confusing, but I understand now that it manages dependencies and compiles the app. I encountered my first error with SDK versions mismatch, which I resolved by syncing the gradle files.

---

## June 10, 2026

Today I focused on core Android components (Part 2). I learned about:
- **Activities**: Each screen in the app is an Activity
- **Intents**: Used to navigate between activities and pass data
- **Activity Lifecycle**: onCreate, onStart, onResume, onPause, onStop, onDestroy

I started implementing my MainActivity with the main timer screen. I learned how to use findViewById() to connect Java code with XML layout elements. I implemented button click listeners for the timer controls and Intent navigation to move between different screens using a bottom navigation bar.

Key learning: Understanding the Activity lifecycle is crucial because it affects how data is saved and restored when users navigate the app.

---

## June 18, 2026

I dived into layouts and UI design today. I learned about:
- **ConstraintLayout**: Flexible and efficient for complex layouts
- **LinearLayout**: Simple vertical/horizontal arrangements
- **Material Design Components**: Using MaterialCardView, MaterialButton, and BottomNavigationView for modern UI

I designed the main screen with a large circular timer display and preset buttons below. I learned about dp (density-independent pixels) vs sp (scale-independent pixels) for responsive design. I also implemented a professional color scheme using colors.xml with blue as the primary color (#2563EB) - no emojis, just clean Material Design icons.

The Material Design library made the app look professional and modern with minimal effort. The elevation and corner radius properties add nice depth to the cards.

---

## June 25, 2026

Today I worked on implementing the circular timer on the main screen - the most challenging part so far. I learned about:
- **CountDownTimer**: Android's built-in class for countdown functionality
- **Thread management**: Understanding main thread vs background tasks
- **Circular timer display**: Creating a large, clean countdown interface

The timer logic was tricky. I had to handle:
- Starting/pausing/resuming the timer
- Switching between focus and break modes
- Updating the UI every second
- Preventing memory leaks by canceling timers in onDestroy()

I spent 3 hours debugging an issue where the timer would continue running after closing the activity. I learned that I must cancel the CountDownTimer in the onDestroy() method to prevent this.

---

## July 2, 2026

I implemented the **SQLite database** functionality using DatabaseHelper class. This was a significant learning experience:
- Creating database schema with CREATE TABLE statements
- CRUD operations: Create, Read, Update, Delete
- Using ContentValues for inserting data
- Cursor object for reading query results

I created a FocusSession model class to represent each study session with fields like:
- Start/end time
- Focus and break minutes
- Actual focus time completed
- Distraction count
- Session type

The database allows the app to persist data even after closing, which is essential for a productivity app. I learned about database versioning and the onUpgrade() method for handling schema changes.

---

## July 8, 2026

Today I tackled **ListView with custom adapter** (Part 3 requirement). This involved:
- Creating a custom SessionAdapter extending ArrayAdapter
- Implementing the ViewHolder pattern for performance
- Inflating custom item layouts
- Binding data to views

The ViewHolder pattern was an important optimization I learned - it prevents findViewById() from being called repeatedly for each list item by caching view references. This significantly improves scrolling performance.

I created item_session.xml as the custom layout for each session in the history, displaying:
- Session type (Pomodoro, Short Break, Long Break)
- Session date and time
- Focus duration
- Completion checkmark icon

No emojis were used - just clean Material Design icons and professional typography.

---

## July 12, 2026

I implemented the **Statistics Activity** with real-time data aggregation. I learned:
- SQL aggregate functions (SUM, COUNT, AVG) in Android SQLite
- Date/time manipulation with Calendar class
- Formatting numbers with DecimalFormat
- Dynamic UI updates based on data

I created methods to calculate:
- Total focus time across all sessions
- Number of completed sessions
- Average session duration
- Total distractions logged (if any)

I also added an insights section that provides personalized feedback based on the user's performance. This involved conditional logic to generate different motivational messages without using emojis.

---

## July 16, 2026

Today I built the **Settings Activity** - an important customization feature. This taught me about:
- Using Switch components for toggles (Keep Screen On, Sound, Vibration)
- Implementing interactive controls with MaterialButton
- Auto-start Break toggle for automatic session flow
- Daily goal adjustment with increment/decrement buttons

I implemented several key settings:
1. Keep Screen On - Prevents phone from sleeping during timer
2. Sound Notifications - Toggle sound alerts
3. Vibration - Vibration feedback
4. Auto-start Break - Automatically begin break after focus
5. Daily Goal - Adjustable 1-12 hour target
6. Reset Statistics - With confirmation dialog

The reset feature was particularly important to learn - it required:
- Showing AlertDialog for confirmation
- Deleting the entire SQLite database
- Clearing SharedPreferences
- Providing user feedback with Toast messages

This enhanced my understanding of data management and user preferences in Android.

---

## July 19, 2026

I polished the UI and added final touches:
- **Bottom Navigation Bar**: Implemented smooth navigation between 4 main screens
- **Auto Session Flow**: Automatic transitions between focus and break modes
- **Professional design**: Ensured no emojis, only Material Design icons
- **Color coding**: Blue primary theme throughout the app
- **Confirmation dialogs**: Using AlertDialog for important actions

I learned about Material Design BottomNavigationView and how to handle navigation between activities while maintaining selected states. I also implemented the auto-flow feature where completing a focus session automatically switches to break mode, and vice versa.

I tested the app extensively:
- Created multiple sessions to test the history
- Checked if statistics calculate correctly
- Verified settings persist across app restarts
- Tested edge cases like stopping mid-session
- Ensured smooth bottom navigation

---

## July 20, 2026

Final testing and bug fixing day. I discovered and fixed several issues:
1. Timer continuing after activity destruction - Fixed by canceling in onDestroy()
2. Empty history showing nothing - Added empty state message
3. Settings not persisting - Fixed SharedPreferences implementation
4. Bottom navigation state not maintained - Added proper selectedItemId

I also improved the user experience by:
- Adding smooth transitions between activities
- Implementing proper back button handling
- Adding Toast messages for user feedback
- Ensuring consistent professional design (no emojis)
- Pre-loading 12 sample sessions for demo purposes

I learned the importance of edge case testing and proper resource cleanup in Android apps. The app now looks professional and ready for demonstration.

---

## July 21, 2026 (Today)

Final day of the project. I focused on documentation and preparing for submission:
- Created README.md with setup instructions
- Documented code with comments
- Organized project structure
- Prepared for video demonstration
- Wrote this comprehensive learning diary

I recorded a demo video showing all 7 features of the app. I'm proud of what I've built - it's a fully functional productivity app that I actually plan to use for my own studying.

---

## Key Learnings Summary

### Technical Skills Gained:
1. **Android Studio** - Project setup, debugging, emulator usage
2. **Activities & Intents** - Navigation, data passing, lifecycle management
3. **UI/UX Design** - Layouts, Material Design, theming, responsive design
4. **SQLite Database** - Schema design, CRUD operations, complex queries
5. **Custom ListView** - Adapters, ViewHolder pattern, custom layouts
6. **Threading** - CountDownTimer, UI thread vs background tasks
7. **Data Persistence** - SQLite, SharedPreferences
8. **Material Components** - Cards, Buttons, Dialogs, TextFields

### Soft Skills Developed:
- Problem-solving through debugging
- Breaking down complex features into manageable tasks
- User-centric design thinking
- Time management in project development
- Documentation and communication skills

### Challenges Overcome:
- Understanding Activity lifecycle and preventing memory leaks
- Implementing efficient ListView with ViewHolder pattern
- Managing complex timer states (running, paused, focus, break)
- Database design and query optimization
- Full-screen immersive mode implementation

---

## Project Features Summary

**7 Core Features for Video Demonstration:**

1. ✅ **Pomodoro Timer** - Large circular countdown on main screen with preset buttons
2. ✅ **Session History** - ListView with custom adapter showing all past sessions
3. ✅ **Statistics Dashboard** - 4 key metrics with real-time data aggregation
4. ✅ **Settings & Customization** - Toggles, daily goal, and reset functionality
5. ✅ **Auto Session Flow** - Seamless transitions between focus and break modes
6. ✅ **Data Persistence** - SQLite database + SharedPreferences for complete data management
7. ✅ **Professional UI** - Bottom navigation, Material Design, no emojis, blue theme

---

**Final Thoughts:**

This project has been an incredible learning journey. I went from basic Android concepts to building a fully functional, database-backed app with 4 activities, custom adapters, and real-time data processing. The most valuable lesson was learning by doing - each challenge I faced taught me more than any tutorial could.

The redesign midway through the project taught me an important lesson about professional UI/UX design - avoiding emoji overload and AI template looks in favor of clean Material Design. The transition from a dashboard-based app to a timer-first approach made the app more focused and user-friendly.

I'm confident this app demonstrates my Android development skills and would serve as a strong portfolio piece for developer positions. The app implements the Pomodoro Technique effectively, which makes it genuinely useful, not just a class project.

Looking forward to continuing my Android development journey!

---

**Sanjaya Singh Kunwor**  
Student Number: 002678485  
Date: July 21, 2026

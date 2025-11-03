# 😴 LazyList - The Ultimate Lazy Todo App

> **"Todo? Nah! Do Nothing, Still Get Things Done"**

LazyList is a revolutionary Android Todo app designed specifically for users who are too lazy to type or interact extensively. It's sarcastic, fun, and ultra-lazy-friendly!

## 🌟 Key Features

### 1. **Hands-Free Input** 🎤
- Add tasks using **voice commands** (no typing required!)
- Long-press anywhere on the screen to activate voice input
- Tap the microphone FAB to start voice recording
- Emotion/tone detection to auto-set task priority
- Support for multiple languages through Android Speech Recognizer

### 2. **Lazy Smart Suggestions** 🤖
- Context-aware suggestions based on time of day
- Examples:
  - Morning (6-8 AM): "Morning routine: Brush teeth?"
  - Noon (12-1 PM): "Lunch break! 🍛"
  - Evening (9-11 PM): "Bedtime routine: Brush teeth?"
- One-tap to accept suggestions
- Automatically appears every 5 minutes if you're idle

### 3. **"Aalsi Mode"** 🔥
The ultimate laziness mode:
- Complete tasks by **shaking your phone** (seriously!)
- Shake to mark the first active task as done
- Sarcastic motivational quotes
- Special visual indicators for lazy completion
- Aalsi Mode badge displayed on active tasks

### 4. **Super Clean Dark UI** 🌙
- Beautiful Material 3 dark theme
- Large, touch-friendly buttons
- Minimal and clutter-free design
- Animated floating microphone button
- Smooth animations and transitions
- Emoji-rich interface for visual appeal

### 5. **Unique Features** 🎯
- **Shake to Complete**: In Aalsi Mode, shake your phone to complete tasks
- **Priority-based colors**: Visual indicators for task urgency
- **Emotion tags**: See how you felt when adding tasks
- **Daily Summary**: Get notifications about your completion stats
- **Offline Support**: Works completely offline
- **Smart Reminders**: Gentle "poke" notifications to remind you

### 6. **Task Management** ✅
- Add, complete, and delete tasks
- Priority levels (Low, Medium, High, Urgent)
- Emotion tracking (Happy, Tired, Frustrated, Excited, Neutral)
- Separate views for active and completed tasks
- Bulk delete completed tasks
- Auto-due date suggestions

## 🏗️ Technical Architecture

### Tech Stack
- **Language**: Kotlin 100%
- **UI**: Jetpack Compose (Material 3)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database (SQLite)
- **Coroutines**: Kotlin Coroutines & Flow
- **Services**: 
  - Android Speech Recognizer API
  - Sensor Manager (Accelerometer for shake detection)
  - WorkManager (for background tasks)

### Project Structure
```
app/src/main/java/com/rr/lazylist/
├── data/
│   ├── TaskEntity.kt          # Room entity
│   ├── TaskDao.kt             # Data access object
│   ├── TaskDatabase.kt        # Room database
│   └── TaskRepository.kt      # Repository layer
├── ui/
│   ├── viewmodel/
│   │   └── TaskViewModel.kt   # MVVM ViewModel
│   ├── screens/
│   │   ├── TaskScreen.kt      # Main screen
│   │   └── TaskItem.kt        # Task item composable
│   ├── services/
│   │   ├── VoiceRecognitionService.kt  # Voice input
│   │   ├── ShakeDetector.kt           # Shake detection
│   │   └── LazyNotificationService.kt  # Notifications
│   └── theme/
│       ├── Color.kt           # Color palette
│       ├── Theme.kt           # Material theme
│       └── Type.kt            # Typography
└── MainActivity.kt            # Entry point
```

## 📱 Permissions

LazyList requests the following permissions:
- **RECORD_AUDIO**: For voice recognition
- **INTERNET**: For speech-to-text (if needed)
- **VIBRATE**: For lazy notifications

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 36 (Android 14)
- Kotlin: 2.0.21

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/LazyList.git
cd LazyList
```

2. Open in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or physical device

### Build

```bash
./gradlew assembleDebug
```

## 🎮 How to Use

1. **Launch the app**: Open LazyList
2. **Grant permissions**: Allow microphone access when prompted
3. **Add tasks**: 
   - Long-press anywhere OR
   - Tap the floating mic button
   - Speak your task
4. **Complete tasks**:
   - Tap the checkbox (normal mode)
   - Shake your phone (Aalsi Mode)
5. **Enable Aalsi Mode**: Tap the heart icon in the toolbar
6. **Accept suggestions**: Tap "Yes, Add It!" when suggestions appear

## 🎨 UI Screenshots

*Coming soon...*

## 🤝 Contributing

Contributions are welcome! Feel free to submit issues or pull requests.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 💡 Future Enhancements

- [ ] Widget support for quick task view
- [ ] Wear OS companion app
- [ ] Task categories and tags
- [ ] Recurring tasks
- [ ] Integration with calendar apps
- [ ] Cloud sync across devices
- [ ] More emotion options and AI insights
- [ ] Custom reminder sounds
- [ ] Task sharing
- [ ] Multi-language support

## 🙏 Acknowledgments

- Inspired by lazy developers worldwide
- Built with love and minimal effort
- Special thanks to Android Jetpack team

## 📧 Contact

For questions, suggestions, or lazy complaints:
- GitHub Issues: [Create an issue](https://github.com/yourusername/LazyList/issues)
- Email: lazylist@example.com

---

**Made with ❤️ and minimal effort for lazy people everywhere! 😴**


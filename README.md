# 😴 LazyList - The Ultimate Lazy Todo App

> **"Todo? Nah! Do Nothing, Still Get Things Done"**

LazyList is a revolutionary Android Todo app designed specifically for users who are too lazy to type or interact extensively. It's sarcastic, fun, and ultra-lazy-friendly!

## 🌟 Key Features

### 1. **5 Complete Screens** 📱
- **Home**: Main task management with voice & gesture control
- **Analytics**: Comprehensive stats dashboard with insights
- **LazyBot**: AI chatbot assistant with sarcastic personality
- **Achievements**: Trophy system with 10+ unlockable badges
- **Settings**: Complete customization panel

### 2. **Hands-Free Input** 🎤
- Add tasks using **voice commands** (no typing required!)
- Long-press anywhere on the screen to activate voice input
- Tap the floating microphone button to start voice recording
- Emotion/tone detection to auto-set task priority (8 emotion types)
- Support for multiple languages through Android Speech Recognizer

### 3. **Lazy Smart Suggestions** 🤖
- Context-aware suggestions based on time of day
- Examples:
  - Morning (6-8 AM): "Morning routine: Brush teeth?"
  - Noon (12-1 PM): "Lunch break! 🍛"
  - Evening (9-11 PM): "Bedtime routine: Brush teeth?"
- One-tap to accept suggestions
- Automatically appears every 5 minutes if you're idle

### 4. **"Aalsi Mode"** 🔥
The ultimate laziness mode:
- Complete tasks by **shaking your phone** (seriously!)
- Shake to mark the first active task as done
- Sarcastic motivational quotes
- Special visual indicators for lazy completion
- Aalsi Mode badge displayed on active tasks

### 5. **Advanced Task System** ✅
- **8 Categories**: Work, Personal, Health, Fun, Shopping, Learning, Family, Urgent
- **8 Emotions**: Happy, Tired, Frustrated, Excited, Stressed, Hungry, Bored, Neutral
- **4 Priorities**: Low, Medium, High, Urgent
- **Subtasks**: Break down complex tasks
- **Recurring Tasks**: Daily, Weekly, Monthly, Weekdays, Weekend, Custom
- **Streak Tracking**: Daily and best streak records
- **Task History**: Complete audit trail

### 6. **Analytics Dashboard** 📊
- **Daily Summary**: Completed/active tasks with progress
- **Streak Statistics**: Current and best streaks
- **Category Breakdown**: Visual progress for each category
- **Mood Trend**: Track your mood over time
- **Lazy Insights**: Sarcastic AI-generated insights

### 7. **Achievement System** 🏆
- **10+ Achievements**: Streak Warrior, Lazy Master, Perfectionist, etc.
- **Progress Tracking**: Visual progress for locked achievements
- **Unlock Celebration**: Visual feedback when unlocked
- **Statistics Banner**: Overall completion percentage

### 8. **LazyBot AI Assistant** 🤖
- **Full Chat Interface**: Real-time messaging
- **Sarcastic Personality**: Witty, lazy AI with sense of humor
- **Smart Responses**: Context-aware replies for:
  - Task management queries
  - Help requests
  - Motivational messages
  - Jokes and fun interactions
- **Voice Commands**: Chat commands support
- **Beautiful UI**: Custom chat bubbles with avatars

### 9. **Super Clean Modern UI** 🌙
- **Material 3 Design**: Latest Material Design guidelines
- **Bottom Navigation**: Easy access to all 5 screens
- **Dark Theme**: Forced dark mode for lazy aesthetic
- **Card-Based Layout**: Consistent design system
- **Animated Components**: Smooth transitions
- **Emoji-Rich**: Visual appeal throughout

### 10. **Settings & Customization** ⚙️
- **16 Settings**: Theme, mascot, colors, behavior, notifications
- **Personalization**: Customize everything
- **Privacy-First**: All data stored locally
- **Data Export**: Backup your tasks

## 🏗️ Technical Architecture

### Tech Stack
- **Language**: Kotlin 100%
- **UI**: Jetpack Compose Material 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Navigation**: Navigation Compose
- **Database**: Room Database (SQLite) with 5 tables
- **State Management**: StateFlow + Compose
- **Coroutines**: Kotlin Coroutines & Flow
- **Animations**: Lottie (ready)
- **Services**: 
  - Android Speech Recognizer API
  - Sensor Manager (Accelerometer for shake detection)
  - WorkManager (for background tasks)

### Project Structure
```
app/src/main/java/com/rr/lazylist/
├── data/
│   ├── TaskEntity.kt          # Enhanced entity with 15+ fields
│   ├── SubtaskEntity.kt       # Subtasks support
│   ├── TaskHistoryEntity.kt   # Task history
│   ├── AchievementEntity.kt   # Achievements
│   ├── MoodEntryEntity.kt     # Mood tracking
│   ├── TaskDao.kt             # Main DAO
│   ├── SubtaskDao.kt          # Subtask DAO
│   ├── TaskHistoryDao.kt      # History DAO
│   ├── AchievementDao.kt      # Achievement DAO
│   ├── MoodDao.kt             # Mood DAO
│   ├── TaskDatabase.kt        # Room database (v2)
│   ├── TaskRepository.kt      # Repository layer
│   └── Converters.kt          # Enum converters
├── ui/
│   ├── navigation/
│   │   └── LazyNavGraph.kt    # Navigation setup
│   ├── viewmodel/
│   │   └── TaskViewModel.kt   # MVVM ViewModel
│   ├── screens/
│   │   ├── TaskScreen.kt      # Main screen
│   │   ├── TaskItem.kt        # Task item composable
│   │   ├── AnalyticsScreen.kt # Stats dashboard
│   │   ├── ChatbotScreen.kt   # AI assistant
│   │   ├── AchievementsScreen.kt # Trophy system
│   │   └── SettingsScreen.kt  # Settings panel
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


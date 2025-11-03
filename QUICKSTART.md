# 🚀 LazyList Quick Start Guide

## Prerequisites
- Android Studio Hedgehog (or later)
- Android device running Android 7.0 (API 24) or higher
- Internet connection (for initial Gradle sync)

## Installation Steps

### 1. Open Project
1. Launch Android Studio
2. Click "Open" or "Open an Existing Project"
3. Navigate to `C:\Users\admin\Desktop\LazyList`
4. Click "OK" to open

### 2. Sync Gradle
1. Android Studio will automatically prompt "Gradle files have changed"
2. Click "Sync Now"
3. Wait for the sync to complete (may take 2-5 minutes on first run)

### 3. Run the App
1. Connect your Android device via USB and enable USB debugging
2. OR use an emulator (recommended: Pixel 7 API 33+)
3. Click the green "Run" button (▶️) or press `Shift + F10`
4. Select your device and click "OK"

## First Time Setup

### 1. Grant Permissions
When the app launches:
- **Microphone Permission**: Click "Allow" when prompted
  - This enables voice input for adding tasks
  - Essential for hands-free operation

### 2. Welcome to LazyList! 😴
You'll see the empty state screen with:
- 😴 emoji
- "Too lazy for tasks?" message
- Instructions to tap & hold or use the mic button

## Basic Usage

### Adding Tasks (3 Ways)

#### Method 1: Voice via Button 🎤
1. Tap the **floating purple microphone button** (bottom-right)
2. Speak your task: "Buy groceries" or "Call mom"
3. Wait for recognition
4. Task appears automatically!

#### Method 2: Voice via Long Press 🖐
1. **Long press anywhere** on the screen
2. Say your task
3. Done!

#### Method 3: Aalsi Mode Magic ✨
Enable Aalsi Mode first (heart icon in toolbar), then:
- Suggestions appear automatically
- Tap "Yes, Add It!" to accept

### Completing Tasks

#### Normal Mode ✅
- Tap the checkbox next to any task
- Task moves to "Completed" section

#### Aalsi Mode 🔥
- Enable Aalsi Mode (heart icon)
- **Shake your phone** to complete the first active task!
- Vibrating celebration guaranteed!

### Organizing Tasks

#### View Active Tasks
- See all incomplete tasks under "Active Tasks"
- Priority badges: Low, Med, High, 🔥
- Aalsi Mode badge for lazy completion

#### View Completed Tasks
- Scroll down to "Completed" section
- See last 10 completed tasks
- Tap "Clear All" to delete all completed tasks

#### Delete Individual Tasks
- Tap the **trash icon** on any task
- Task disappears immediately

### Features Deep Dive

#### 🎯 Aalsi Mode
**What it does:**
- Enables shake-to-complete
- Shows sarcastic humor
- Suggests tasks based on time
- Adds "Aalsi Mode" badge to tasks

**How to enable:**
- Tap the **heart icon** (❤️) in the top-right
- Icon turns red when active
- Task bar shows "Aalsi Mode 🔥" badge

#### 🤖 Lazy Smart Suggestions
**Auto-suggestions based on time:**
- **6-8 AM**: "Morning routine: Brush teeth?"
- **9-11 AM**: "Breakfast time! 🥐"
- **12-1 PM**: "Lunch break! 🍛"
- **2-3 PM**: "Afternoon productivity boost?"
- **4-6 PM**: "Evening chill time?"
- **7-9 PM**: "Dinner? 🍽️"
- **9-11 PM**: "Bedtime routine: Brush teeth?"

**When they appear:**
- Every 5 minutes if idle
- Context-aware timing
- Tap to accept or dismiss

#### 🎨 Task Priorities
Tasks are auto-prioritized:
- **🔥 Urgent** (Red): Frustrated or Excited emotions
- **High** (Orange): Important tasks
- **Med** (Purple): Normal priority
- **Low** (Cyan): Tired emotion, basic tasks

## Tips & Tricks

### 1. Voice Input Best Practices
- Speak clearly and slowly
- Phrase naturally: "Buy milk" not "Task: Buy milk"
- One task per voice input
- Works offline (after initial setup)

### 2. Shake Detection Tuning
- Shake firmly but don't drop your phone!
- Works best with quick, deliberate shakes
- May need calibration on some devices

### 3. Battery Optimization
- Voice recognition is lightweight
- Room database is local (no network needed)
- Minimal battery impact

### 4. Privacy
- All data stored **locally** on your device
- Voice recognition processed on-device
- No cloud sync (offline-first)
- No telemetry or tracking

## Troubleshooting

### "Speech recognition not available"
**Solution:** 
- Ensure internet connection for first-time setup
- Check microphone permission in Settings
- Restart the app

### Shake not working
**Solution:**
- Ensure Aalsi Mode is enabled (red heart icon)
- Shake with more force
- Check if device has accelerometer

### No suggestions appearing
**Solution:**
- Wait 5 minutes (suggestions are time-based)
- Keep app open in foreground
- Ensure correct time/date on device

### "Permission denied" error
**Solution:**
- Go to Android Settings > Apps > LazyList
- Permissions > Allow Microphone
- Restart app

## Keyboard Shortcuts (Android Studio)

- `Ctrl + F9` - Build project
- `Shift + F10` - Run app
- `Shift + F9` - Debug app
- `Ctrl + Shift + F10` - Run current file
- `Ctrl + F12` - Show file structure

## What's Next?

### Try These Features:
1. ✅ Add 5 tasks using voice
2. ✅ Enable Aalsi Mode
3. ✅ Complete a task by shaking
4. ✅ Wait for a lazy suggestion
5. ✅ Delete completed tasks

### Explore:
- Dark theme customization
- Emotion tracking
- Priority management
- Smart suggestions timing

## Need Help?

- Check `README.md` for full documentation
- View source code in `app/src/main/java/com/rr/lazylist/`
- Report issues on GitHub
- Contact: lazylist@example.com

---

**Enjoy being lazy! 😴✨**

*Remember: The laziest way to use LazyList is to not use it at all... but if you must, shake that phone!* 🎉


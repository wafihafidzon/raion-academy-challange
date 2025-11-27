# 🎵 MoodTunes - Music App Based on Mood

Aplikasi musik sederhana berbasis mood menggunakan Jetpack Compose. User dapat memilih mood dan mendapat rekomendasi lagu sesuai perasaan mereka.

## 📱 Fitur Aplikasi

### 🎯 Phase 1 - MVP (Static Data)
- **Mood Selection Screen**
  - Grid/List pilihan mood (Happy, Sad, Energetic, Chill, Romantic, Focus)
  - Icon dan warna menarik untuk setiap mood
  - Smooth navigation between screens

- **Music List Screen**
  - Tampil list lagu berdasarkan mood yang dipilih
  - Card design untuk setiap lagu (title, artist, duration)
  - Static data untuk setiap kategori mood
  - Back navigation ke mood selection

- **Basic UI Components**
  - Material 3 Design System
  - Custom theme sesuai branding
  - Responsive layout
  - Loading states dan animations

### 🚀 Phase 2 - CRUD Operations  
- **Add New Song**
  - Form input lagu baru (title, artist, mood category, duration)
  - Validation input
  - Success/Error feedback

- **Edit Song**
  - Update informasi lagu existing
  - Pre-filled form data
  - Confirm changes dialog

- **Delete Song**
  - Remove lagu dari list
  - Confirmation dialog
  - Undo functionality (bonus)

- **Manage Moods**
  - Add custom mood categories
  - Edit mood names dan icons
  - Delete unused moods

### 🎨 Phase 3 - Enhanced Features (Future)
- **Search & Filter**
  - Search lagu by title/artist
  - Filter by multiple moods
  - Sort by name, date added, duration

- **Favorites System**
  - Mark/unmark favorite songs
  - Dedicated favorites screen
  - Quick access to loved tracks

- **Data Persistence**
  - Local database (Room)
  - Export/Import playlist
  - Backup functionality

- **UI Enhancements**
  - Dark/Light theme toggle
  - Custom animations
  - Splash screen
  - Empty states illustration

## 🏗️ Tech Stack
- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Navigation**: Compose Navigation
- **State Management**: ViewModel + StateFlow
- **Database**: Room (Phase 2+)
- **Dependency Injection**: Hilt (Phase 2+)

## 📋 Project Structure
```
app/
├── ui/
│   ├── theme/          # Colors, Typography, Theme
│   ├── components/     # Reusable UI components
│   ├── screens/        # Screen composables
│   │   ├── mood/       # Mood selection screen
│   │   └── musiclist/  # Music list screen
│   └── navigation/     # Navigation setup
├── data/
│   ├── model/          # Data classes
│   └── repository/     # Data source (static → Room)
└── domain/
    └── usecase/        # Business logic
```

## 🎵 Sample Mood Categories
- 😊 **Happy** - Upbeat, cheerful songs
- 😢 **Sad** - Emotional, melancholic tracks  
- ⚡ **Energetic** - High tempo, workout music
- 😌 **Chill** - Relaxing, ambient sounds
- 💕 **Romantic** - Love songs, soft melodies
- 🧠 **Focus** - Instrumental, concentration music

## 🚀 Getting Started
1. Clone repository
2. Open in Android Studio
3. Sync Gradle
4. Run app on device/emulator

---
*Created with ❤️ for Raion Academy Challenge*
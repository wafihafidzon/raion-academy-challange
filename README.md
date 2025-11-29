# 🎵 MoodTunes - Music App Based on Mood

Aplikasi musik berbasis mood menggunakan Jetpack Compose yang memungkinkan pengguna memilih mood dan menemukan lagu yang sesuai dengan perasaan mereka. Aplikasi ini mendukung streaming langsung ke platform musik seperti Spotify, YouTube Music, dan lainnya.

## ✨ Fitur yang Telah Diimplementasi

### 🎯 Core Features
- **Mood Selection Screen**
  - 2 mood default: Senang (😊) dan Sedih (😢)
  - Dynamic gradient background untuk setiap mood
  - CRUD operations untuk mood (add, edit, delete)
  - Random gradient generator untuk mood baru

- **Music List Screen** 
  - Daftar lagu berdasarkan mood yang dipilih
  - CRUD operations untuk lagu (add, edit, delete)
  - FloatingActionButton untuk menambah lagu baru
  - Card design dengan platform info

- **Song Detail Screen**
  - Halaman detail lengkap untuk setiap lagu
  - Informasi judul, artist, deskripsi
  - Platform streaming detection
  - Tombol play yang redirect ke aplikasi musik
  - Alternative browser playback

### 🗄️ Database & Data Management
- **Room Database** untuk persistent storage
- **Entity relationships** (Mood → Songs dengan foreign key)
- **Sample data seeding** untuk pengalaman pertama
- **Real-time data updates** menggunakan StateFlow

### 🎨 UI/UX Features
- **Material 3 Design System**
- **Custom gradient themes** per mood
- **Dialog-based forms** untuk CRUD operations
- **Responsive layouts** untuk berbagai screen size
- **Loading states** dan error handling

### 🎧 Music Integration
- **Multi-platform support**: Spotify, Apple Music, YouTube Music, Deezer, SoundCloud
- **Smart app detection** - buka di app jika terinstall
- **Fallback browser playback** jika app tidak ada
- **Streaming URL validation**

## 🏗️ Teknologi yang Digunakan

### Core Technologies
- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Database**: Room SQLite
- **Async**: Kotlin Coroutines + Flow

### Jetpack Libraries
```kotlin
// Compose BOM
androidx.compose:compose-bom:2023.10.01

// Core Compose
androidx.compose.ui:ui
androidx.compose.material3:material3  
androidx.activity:activity-compose

// Architecture Components
androidx.lifecycle:lifecycle-viewmodel-compose
androidx.lifecycle:lifecycle-runtime-ktx

// Database
androidx.room:room-runtime
androidx.room:room-ktx
```

### Development Tools
- **Build System**: Gradle with Kotlin DSL
- **IDE**: Android Studio
- **Minimum SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📁 Struktur Project

```
app/src/main/java/com/example/raionchallange/
├── 📁 data/
│   ├── 📁 database/
│   │   ├── 📁 dao/
│   │   │   ├── MoodDao.kt           # Database access untuk Mood
│   │   │   └── SongDao.kt           # Database access untuk Song
│   │   ├── 📁 entities/
│   │   │   ├── MoodEntity.kt        # Room entity untuk Mood
│   │   │   └── SongEntity.kt        # Room entity untuk Song
│   │   └── MoodDatabase.kt          # Room database configuration
│   ├── 📁 model/
│   │   ├── Mood.kt                  # Data class untuk UI
│   │   ├── Song.kt                  # Data class untuk UI
│   │   ├── MoodType.kt              # Enum untuk mood types
│   │   └── MusicPlatform.kt         # Enum untuk platform musik
│   ├── 📁 repository/
│   │   ├── MoodRepository.kt        # Business logic untuk Mood
│   │   └── SongRepository.kt        # Business logic untuk Song
│   └── 📁 utils/
│       └── GradientGenerator.kt     # Random gradient generator
├── 📁 ui/
│   ├── 📁 components/
│   │   ├── 📁 dialogs/
│   │   │   ├── AddMoodDialog.kt     # Dialog tambah mood
│   │   │   ├── EditMoodDialog.kt    # Dialog edit mood
│   │   │   ├── AddSongDialog.kt     # Dialog tambah lagu
│   │   │   ├── EditSongDialog.kt    # Dialog edit lagu
│   │   │   └── DeleteSongDialog.kt  # Dialog konfirmasi hapus
│   │   ├── DatabaseMoodCard.kt      # Mood card component
│   │   └── MoodCard.kt              # Static mood card
│   ├── 📁 screens/
│   │   ├── 📁 mood/
│   │   │   └── MoodSelectionScreen.kt    # Screen pilih mood
│   │   ├── 📁 musiclist/
│   │   │   └── MusicListScreen.kt        # Screen daftar lagu
│   │   └── 📁 songdetail/
│   │       └── SongDetailScreen.kt       # Screen detail lagu
│   ├── 📁 theme/
│   │   ├── Color.kt                 # Color definitions
│   │   ├── Theme.kt                 # Material theme setup
│   │   └── Type.kt                  # Typography definitions
│   └── 📁 viewmodel/
│       ├── MoodViewModel.kt         # ViewModel untuk mood
│       ├── SongViewModel.kt         # ViewModel untuk song
│       └── ViewModelFactory.kt      # Factory untuk dependency injection
└── MainActivity.kt                  # Entry point + navigation logic
```

## 🎵 Default Mood Categories

| Mood | Gradient | Sample Songs |
|------|----------|--------------|
| 😊 **Senang** | Orange → Pink | Happy (Pharrell), Walking on Sunshine, Good as Hell (Lizzo) |
| 😢 **Sedih** | Blue → Purple | Someone Like You (Adele), Hurt (Johnny Cash), Mad World |

## 🔧 Database Schema

### Moods Table
```sql
CREATE TABLE moods (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    gradientStartColor TEXT NOT NULL,
    gradientEndColor TEXT NOT NULL,
    iconName TEXT NOT NULL,
    createdAt INTEGER NOT NULL
);
```

### Songs Table
```sql
CREATE TABLE songs (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    artist TEXT NOT NULL,
    description TEXT DEFAULT '',
    streamingUrl TEXT NOT NULL,
    platform TEXT NOT NULL,
    moodId INTEGER NOT NULL,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY (moodId) REFERENCES moods(id) ON DELETE CASCADE
);
```

## 🚀 Setup & Installation

### Prerequisites
- Android Studio Arctic Fox atau lebih baru
- JDK 11 atau lebih baru
- Android SDK dengan minimum API 24

### Steps
1. **Clone repository**
   ```bash
   git clone https://github.com/wafihafidzon/raion-academy-challange.git
   cd RaionChallange
   ```

2. **Open di Android Studio**
   - File → Open → Pilih folder project

3. **Sync Gradle**
   - Tunggu sampai dependency selesai di-download

4. **Run aplikasi**
   - Pilih device/emulator
   - Klik Run (▶️) atau Shift+F10

### Build Commands
```bash
# Debug build
./gradlew assembleDebug

# Release build
./gradlew assembleRelease

# Run tests
./gradlew test
```

## 📱 Screenshots & Flow

**Navigation Flow:**
```
MoodSelectionScreen → MusicListScreen → SongDetailScreen
        ↑                    ↑                ↑
    [Mood CRUD]         [Song CRUD]     [Play Music]
```

## 🤝 Contributing

1. Fork repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Open Pull Request

## 📄 License

This project is created for educational purposes as part of Raion Academy Challenge.

---
*Created with ❤️ using Jetpack Compose for Raion Academy Challenge*

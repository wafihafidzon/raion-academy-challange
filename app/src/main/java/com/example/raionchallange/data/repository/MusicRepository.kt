package com.example.raionchallange.data.repository

import com.example.raionchallange.data.model.MoodType
import com.example.raionchallange.data.model.Song

object MusicRepository {
    
    fun getAllMoods(): List<MoodType> {
        return MoodType.values().toList()
    }
    
    fun getSongsByMood(mood: MoodType): List<Song> {
        return when (mood) {
            MoodType.HAPPY -> listOf(
                Song(1, "Happy", "Pharrell Williams", "3:53", MoodType.HAPPY),
                Song(2, "Good 4 U", "Olivia Rodrigo", "2:58", MoodType.HAPPY),
                Song(3, "Uptown Funk", "Mark Ronson ft. Bruno Mars", "4:30", MoodType.HAPPY),
                Song(4, "Can't Stop the Feeling", "Justin Timberlake", "3:56", MoodType.HAPPY),
                Song(5, "Walking on Sunshine", "Katrina & The Waves", "3:58", MoodType.HAPPY)
            )
            MoodType.SAD -> listOf(
                Song(6, "Someone Like You", "Adele", "4:45", MoodType.SAD),
                Song(7, "Hurt", "Johnny Cash", "3:38", MoodType.SAD),
                Song(8, "Mad World", "Gary Jules", "3:07", MoodType.SAD),
                Song(9, "The Night We Met", "Lord Huron", "3:28", MoodType.SAD),
                Song(10, "Skinny Love", "Bon Iver", "3:58", MoodType.SAD)
            )
            MoodType.ENERGETIC -> listOf(
                Song(11, "Thunder", "Imagine Dragons", "3:07", MoodType.ENERGETIC),
                Song(12, "Pump It", "Black Eyed Peas", "3:33", MoodType.ENERGETIC),
                Song(13, "Till I Collapse", "Eminem", "4:57", MoodType.ENERGETIC),
                Song(14, "Eye of the Tiger", "Survivor", "4:05", MoodType.ENERGETIC),
                Song(15, "Stronger", "Kanye West", "5:11", MoodType.ENERGETIC)
            )
            MoodType.CHILL -> listOf(
                Song(16, "Stay", "Rihanna", "4:00", MoodType.CHILL),
                Song(17, "Breathe", "Télépopmusik", "4:33", MoodType.CHILL),
                Song(18, "Weightless", "Marconi Union", "8:08", MoodType.CHILL),
                Song(19, "River", "Joni Mitchell", "4:00", MoodType.CHILL),
                Song(20, "Mad About You", "Sting", "3:56", MoodType.CHILL)
            )
            MoodType.ROMANTIC -> listOf(
                Song(21, "Perfect", "Ed Sheeran", "4:23", MoodType.ROMANTIC),
                Song(22, "All of Me", "John Legend", "4:29", MoodType.ROMANTIC),
                Song(23, "Thinking Out Loud", "Ed Sheeran", "4:41", MoodType.ROMANTIC),
                Song(24, "Make You Feel My Love", "Adele", "3:32", MoodType.ROMANTIC),
                Song(25, "A Thousand Years", "Christina Perri", "4:45", MoodType.ROMANTIC)
            )
            MoodType.FOCUS -> listOf(
                Song(26, "Weightless", "Marconi Union", "8:08", MoodType.FOCUS),
                Song(27, "Clair de Lune", "Claude Debussy", "4:42", MoodType.FOCUS),
                Song(28, "Gymnopédie No.1", "Erik Satie", "4:28", MoodType.FOCUS),
                Song(29, "Metamorphosis Two", "Max Richter", "1:32", MoodType.FOCUS),
                Song(30, "Elegy for the Arctic", "Ludovico Einaudi", "3:59", MoodType.FOCUS)
            )
        }
    }
}
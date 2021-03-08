package com.example.music.data.source

interface SongDataSource {
    interface Local {
        fun getLocalSongs(callback: OnSongLoadedCallback)
    }
}

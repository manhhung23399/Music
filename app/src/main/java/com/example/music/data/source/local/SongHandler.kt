package com.example.music.data.source.local

import com.example.music.data.entity.Song

interface SongHandler {
    fun getSong(): MutableList<Song>
}

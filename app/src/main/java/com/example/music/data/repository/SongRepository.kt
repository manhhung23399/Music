package com.example.music.data.repository

import com.example.music.data.source.OnSongLoadedCallback
import com.example.music.data.source.SongDataSource

class SongRepository(private val local: SongDataSource.Local) : SongDataSource.Local {
    override fun getLocalSongs(callback: OnSongLoadedCallback) {
        local.getLocalSongs(callback)
    }

}

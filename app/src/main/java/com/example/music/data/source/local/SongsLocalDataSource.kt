package com.example.music.data.source.local

import android.content.ContentResolver
import com.example.music.data.source.OnSongLoadedCallback
import com.example.music.data.source.SongDataSource

class SongsLocalDataSource(private val resolver: ContentResolver) : SongDataSource.Local {
    override fun getLocalSongs(callback: OnSongLoadedCallback) {
        SongAsynTask(SongLocalData(resolver), callback).execute()
    }
}

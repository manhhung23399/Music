package com.example.music.data.source.local

import android.content.ContentResolver
import com.example.music.data.source.OnSongLoadedCallback
import com.example.music.data.source.SongDataSource

class LocalSongDataSource(private val resolver: ContentResolver) : SongDataSource.Local {
    override fun getLocalTracks(callback: OnSongLoadedCallback) {
        GetSongAsynTask(DataLocal(resolver), callback).execute()
    }
}

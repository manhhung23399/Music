package com.example.music.data.source.local

import android.content.ContentResolver
import com.example.music.data.source.OnSongLoadedCallback
import com.example.music.data.source.SongDataSource

class SongsLocalDataSource(private val resolver: ContentResolver) : SongDataSource.Local {
    val songLocalData:SongLocalData = SongLocalData(resolver)
    override fun getLocalSongs(callback: OnSongLoadedCallback) {
        SongAsynTask(songLocalData, callback).execute()
    }
    companion object{
        private var instance:SongsLocalDataSource?=null
        fun getInstance(resolver: ContentResolver):SongsLocalDataSource{
            return instance ?: SongsLocalDataSource(resolver).also { instance=it }
        }
    }
}

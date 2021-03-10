package com.example.music.data.source.local

import android.content.ContentResolver
import android.provider.MediaStore
import com.example.music.data.entity.Song

class SongLocalData(private val resolver: ContentResolver) : SongHandler {
    override fun getSongs(): MutableList<Song> {
        val listSongs = mutableListOf<Song>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = resolver.query(uri, null, null, null, null)
        cursor?.let {
            while (it.moveToNext()) {
                listSongs.add(Song(cursor))
            }
        }
        return listSongs
    }
}

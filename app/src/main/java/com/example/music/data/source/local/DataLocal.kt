package com.example.music.data.source.local

import android.content.ContentResolver
import android.provider.MediaStore
import com.example.music.data.entity.Song

class DataLocal(private val resolver: ContentResolver) : SongHandler {
    override fun getSong(): MutableList<Song> {
        val list = mutableListOf<Song>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val cursor = resolver.query(uri, null, null, null, null)
        if (cursor != null) {
            val id = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            val title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)
            while (cursor.moveToNext()) {
                list.add(Song(cursor.getInt(id), cursor.getString(title)))
            }
        }
        return list
    }
}

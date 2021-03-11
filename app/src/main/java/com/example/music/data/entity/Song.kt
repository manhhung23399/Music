package com.example.music.data.entity

import android.database.Cursor
import android.provider.MediaStore

data class Song(val id: Int, val title: String) {
    constructor(cursor: Cursor) : this(
        id = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)),
        title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
    )
}

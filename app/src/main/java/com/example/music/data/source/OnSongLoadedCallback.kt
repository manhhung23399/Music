package com.example.music.data.source

import com.example.music.data.entity.Song
import java.lang.Exception

interface OnSongLoadedCallback {
    fun onSuccess(data: MutableList<Song>)
    fun onFail(exception: Exception)
}

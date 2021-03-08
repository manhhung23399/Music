package com.example.music.ui

import com.example.music.data.entity.Song
import java.lang.Exception

interface MainContract {
    interface View {
        fun showListSongSuccess(list: MutableList<Song>)
        fun showListSongErorr(exception: Exception)
    }

    interface Presenter {
        fun getSongList()
    }
}

package com.example.music.ui

import com.example.music.data.entity.Song
import java.lang.Exception

interface MainContract {
    interface View {
        fun showListSong(list: MutableList<Song>)
        fun showListSong(exception: Exception)
    }

    interface Presenter {
        fun getSongs()
    }
}

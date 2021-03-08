package com.example.music.ui

import com.example.music.data.entity.Song
import java.lang.Exception

interface MainContract {
    interface View {
        fun showListSuccess(list: MutableList<Song>)
        fun showListErorr(exception: Exception)
    }

    interface Presenter {
        fun getSongList()
    }
}

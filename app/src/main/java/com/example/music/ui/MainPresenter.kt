package com.example.music.ui

import com.example.music.data.entity.Song
import com.example.music.data.repository.SongRepository
import com.example.music.data.source.OnSongLoadedCallback
import java.lang.Exception

class MainPresenter(private val view: MainContract.View, private val repository: SongRepository) :
    MainContract.Presenter {
    override fun getSongList() {
        repository.getLocalTracks(object : OnSongLoadedCallback {
            override fun onSuccess(data: MutableList<Song>) {
                view.showListSuccess(data)
            }

            override fun onFail(exception: Exception) {
                view.showListErorr(exception)
            }
        })

    }
}

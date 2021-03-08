package com.example.music.data.source.local

import android.os.AsyncTask
import com.example.music.data.entity.Song
import com.example.music.data.source.OnSongLoadedCallback
import java.lang.Exception

class SongAsynTask(
    private val songHandler: SongHandler,
    private val callback: OnSongLoadedCallback
) : AsyncTask<Void, Void, MutableList<Song>>() {
    private var exception: Exception? = null
    override fun doInBackground(vararg params: Void?): MutableList<Song>? {
        return try {
            songHandler.getSongs()
        } catch (exception: Exception) {
            this.exception = exception
            null
        }
    }

    override fun onPostExecute(result: MutableList<Song>?) {
        if (result != null) {
            callback.onSuccess(result)
        } else {
            exception?.let { callback.onFail(it) }
        }
    }
}

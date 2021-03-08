package com.example.music.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.data.entity.Song
import kotlinx.android.synthetic.main.row_song.view.*

class SongAdapter(private val listSong: MutableList<Song>, val callback: Callback) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(Song(listSong[position].id, listSong[position].title))
    }

    override fun getItemCount(): Int {
        return listSong.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.title
        var id: Int = view.id
        fun bindView(song: Song) {
            title.text = song.title
            id = song.id
            title.setOnClickListener {
                callback.onClick(id)
            }
        }
    }
}

interface Callback {
    fun onClick(id: Int)
}



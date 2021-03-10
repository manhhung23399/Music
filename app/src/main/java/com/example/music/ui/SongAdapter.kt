package com.example.music.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.data.entity.Song
import kotlinx.android.synthetic.main.row_song.view.*

class SongAdapter(val callback: Callback) :
    RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private val listSong = mutableListOf<Song>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(Song(listSong[position].id, listSong[position].title))
    }

    override fun getItemCount(): Int = listSong.size

    fun updateData(list: MutableList<Song>) {
        listSong.run {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }

    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindView(song: Song) {
            itemView.title.text = song.title
            itemView.id = song.id
            itemView.title.setOnClickListener {
                callback.onClick(itemView.id)
            }
        }
    }
}

interface Callback {
    fun onClick(id: Int)
}

package com.example.music.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music.R
import com.example.music.data.entity.Song
import kotlinx.android.synthetic.main.row_song.view.*

class SongAdapter(
    private val onClickItem: (id: Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    private val songs = mutableListOf<Song>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_song, parent, false)
        return ViewHolder(view, onClickItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(songs[position])
    }

    override fun getItemCount(): Int = songs.size

    fun updateData(list: MutableList<Song>) {
        songs.run {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }

    }

    inner class ViewHolder(view: View, private var onClickItem: (id: Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        init {
            view.setOnClickListener {
                onClickItem(songs[adapterPosition].id)
            }
        }

        fun bindView(song: Song) {
            itemView.apply {
                title.text = song.title
            }
        }
    }
}

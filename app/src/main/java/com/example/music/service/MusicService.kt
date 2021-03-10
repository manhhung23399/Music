package com.example.music.service

import android.app.Service
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder

class MusicService : Service() {
    private var binder = Binder()
    private var mediaPlayer = MediaPlayer()
    override fun onCreate() {
        super.onCreate()
        binder = Binder()
    }

    override fun onBind(intent: Intent?): IBinder = binder
    fun playMusic(position: Long) {
        mediaPlayer.reset()
        val id: Long = position
        val contentUri: Uri =
            ContentUris.withAppendedId(
                android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                id
            )
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(applicationContext, contentUri)
        }
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MusicService::class.java)
    }

    inner class Binder : android.os.Binder() {
        fun getService(): MusicService = this@MusicService
    }
}

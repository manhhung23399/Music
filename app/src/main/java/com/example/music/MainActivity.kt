package com.example.music

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.ServiceConnection
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music.data.entity.Song
import com.example.music.data.repository.SongRepository
import com.example.music.data.source.local.SongsLocalDataSource
import com.example.music.service.MusicService
import com.example.music.ui.Callback
import com.example.music.ui.MainContract
import com.example.music.ui.MainPresenter
import com.example.music.ui.SongAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainContract.View, Callback {
    private val REQUEST_CODE = 1
    private var mainPresenter: MainPresenter? = null
    private var musicService: MusicService? = null
    private var isBound = false
    private val connect: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.Binder
            musicService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }
    }

    override fun onStart() {
        super.onStart()
        if (!isBound) {
            bindService(MusicService.getIntent(this), connect, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPremission()
        mainPresenter =
            MainPresenter(this, SongRepository(SongsLocalDataSource(this.contentResolver)))
        mainPresenter?.getSongList()
    }

    private fun checkPremission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showListSongSuccess(list: MutableList<Song>) {
        val songAdapter = SongAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = songAdapter
        songAdapter.notifyDataSetChanged()
    }

    override fun showListSongErorr(exception: Exception) {
        Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onClick(id: Int) {
        musicService?.playMusic(id.toLong())
    }


}

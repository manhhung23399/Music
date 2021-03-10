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
import com.example.music.data.entity.Song
import com.example.music.data.repository.SongRepository
import com.example.music.data.source.local.SongsLocalDataSource
import com.example.music.service.MusicService
import com.example.music.ui.MainContract
import com.example.music.ui.MainPresenter
import com.example.music.ui.SongAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), MainContract.View {

    private var mainPresenter: MainPresenter? = null
    private var musicService: MusicService? = null
    private var isBound = false
    private val songAdapter = SongAdapter(onClickItem = { id -> onClickItem(id) })
    private val connect: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MusicService.MusicServiceBinder
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
            MainPresenter(
                this,
                SongRepository.getInstance(SongsLocalDataSource.getInstance(contentResolver))
            )
        mainPresenter?.getSongs()
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

    override fun showListSong(list: MutableList<Song>) {
        songAdapter.updateData(list)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = songAdapter
    }

    override fun showError(exception: Exception) {
        Toast.makeText(this, exception.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onClickItem(id: Int) {
        musicService?.playMusic(id.toLong())
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}

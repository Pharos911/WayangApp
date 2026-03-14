package com.example.wayangapp

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wayangapp.content.*
import com.example.wayangapp.content.detail.WayangDetailActivity
import com.example.wayangapp.databinding.ActivityMainBinding
import com.example.wayangapp.wayangcamera.WayangCameraActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var dots: ArrayList<TextView>
    private lateinit var sharedPreferences: SharedPreferences

    private var SHARED_PREF_NAME = "mypref"


    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
        const val EXTRA_OPTION = "extra_option"

    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)


        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.mwayangBeber.setOnClickListener {
            Intent( this, WayangDetailActivity::class.java).also {
                it.putExtra(WayangDetailActivity.EXTRA_ID, 1)
                startActivity(it)
            }
        }

        binding.mwayangGedog.setOnClickListener {
            Intent( this, WayangDetailActivity::class.java).also {
                it.putExtra(WayangDetailActivity.EXTRA_ID, 2)
                startActivity(it)
            }
        }

        binding.mwayangGolek.setOnClickListener {
            Intent( this, WayangDetailActivity::class.java).also {
                it.putExtra(WayangDetailActivity.EXTRA_ID, 3)
                startActivity(it)
            }
        }

        binding.mwayangKulit.setOnClickListener {
            Intent( this, WayangDetailActivity::class.java).also {
                it.putExtra(WayangDetailActivity.EXTRA_ID, 4)
                startActivity(it)
            }
        }

        binding.mwayangKrucil.setOnClickListener {
            Intent( this, WayangDetailActivity::class.java).also {
                it.putExtra(WayangDetailActivity.EXTRA_ID, 5)
                startActivity(it)
            }
        }

        binding.mwayangSuluh.setOnClickListener {
            Intent( this, WayangDetailActivity::class.java).also {
                it.putExtra(WayangDetailActivity.EXTRA_ID, 6)
                startActivity(it)
            }
        }

        binding.mdalang.setOnClickListener {
            val i = Intent(this, DalangBioActivity::class.java)
            startActivity(i)
        }

        binding.mmuseumStudio.setOnClickListener {
            val i = Intent(this, StudioMuseumActivity::class.java)
            startActivity(i)
        }

        binding.mceritaWayang.setOnClickListener {
            val i = Intent(this, StoriesActivity::class.java)
            startActivity(i)
        }

        binding.mvideoWayang.setOnClickListener {
            val  i = Intent(this, VideoActivity::class.java)
            startActivity(i)
        }

        binding.btnStartLearningWayang.setOnClickListener {
            val i = Intent(this, LearnWayangActivity::class.java)
            startActivity(i)
        }

        binding.btnInfo.setOnClickListener {
            val i = Intent(this, InfoApp::class.java)
            startActivity(i)
        }

        binding.btnScanFromCamera.setOnClickListener {
            Intent(this, WayangCameraActivity::class.java).also {
                it.putExtra(WayangCameraActivity.EXTRA_OPTION, "camera")
                startActivity(it)
            }
        }

        binding.btnScanFromGallery.setOnClickListener {
            Intent(this, WayangCameraActivity::class.java).also {
                it.putExtra(WayangCameraActivity.EXTRA_OPTION, "gallery")
                startActivity(it)
            }
        }

    }

}
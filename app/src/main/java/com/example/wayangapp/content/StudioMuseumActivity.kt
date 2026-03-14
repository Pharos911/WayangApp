package com.example.wayangapp.content

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wayangapp.retrofit.responses.ListMuseumStudioItems
import com.example.wayangapp.MainActivity
import com.example.wayangapp.R
import com.example.wayangapp.adapter.MuseumStudioAdapter
import com.example.wayangapp.content.detail.DetailActivity
import com.example.wayangapp.databinding.ActivityStudioMuseumBinding

class StudioMuseumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudioMuseumBinding
    private lateinit var adapter: MuseumStudioAdapter

    private lateinit var sharedPreferences: SharedPreferences
    val listMuseumItem = ArrayList<ListMuseumStudioItems>()

    private var SHARED_PREF_NAME = "mypref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudioMuseumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

        listMuseumItem.addAll(listMuseumOffline)
        initRv()

        binding.btnBackToHome.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private val listMuseumOffline: ArrayList<ListMuseumStudioItems>
        get() {
            val museumId = resources.getIntArray(R.array.data_museum_id)
            val museumName = resources.getStringArray(R.array.data_museum_name)
            val museumDesk = resources.getStringArray(R.array.data_museum_description)
            val museumImage = resources.obtainTypedArray(R.array.data_museum_image)
            val museumLocation = resources.getStringArray(R.array.data_museum_location)
            val dataMuseumOffline = ArrayList<ListMuseumStudioItems>()
            for (i in museumId.indices) {
                val museum = ListMuseumStudioItems(
                    museumImage.getResourceId(i, -1),
                    museumName[i],
                    museumDesk[i],
                    museumLocation[i],
                    museumId[i]
                )
                dataMuseumOffline.add(museum)
            }
            return dataMuseumOffline
        }

    private fun initRv() {
        adapter = MuseumStudioAdapter()
        adapter.setList(listMuseumOffline)
        binding.apply {
            rvMcv.layoutManager = LinearLayoutManager(this@StudioMuseumActivity)
            rvMcv.setHasFixedSize(true)
            rvMcv.adapter = adapter


            adapter.setOnItemClickCallback(object : MuseumStudioAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ListMuseumStudioItems) {
                    Intent(this@StudioMuseumActivity, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.EXTRA_PHOTO, data.image)
                        it.putExtra(DetailActivity.EXTRA_NAME, data.name)
                        it.putExtra(DetailActivity.EXTRA_DESK, data.description)
                        it.putExtra(DetailActivity.EXTRA_DESK1, "Lokasi : " + data.location)
                        startActivity(it)
                    }
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}
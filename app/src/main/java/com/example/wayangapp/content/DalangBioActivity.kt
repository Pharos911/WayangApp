package com.example.wayangapp.content

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wayangapp.retrofit.responses.DataItem
import com.example.wayangapp.MainActivity
import com.example.wayangapp.R
import com.example.wayangapp.adapter.DalangAdapter
import com.example.wayangapp.content.detail.DetailActivity
import com.example.wayangapp.databinding.ActivityDalangBioBinding

class DalangBioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDalangBioBinding
    private lateinit var adapter: DalangAdapter

    private lateinit var sharedPreferences: SharedPreferences
    private val listDalangItem = ArrayList<DataItem>()

    private var SHARED_PREF_NAME = "mypref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDalangBioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

        listDalangItem.addAll(listDalangOffline)
//        setDalang()
        initRv()


        binding.btnBackToHome.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }

    private val listDalangOffline: ArrayList<DataItem>
        get() {
            val dalangId = resources.getIntArray(R.array.data_dalang_id)
            val dalangName = resources.getStringArray(R.array.data_dalang_name)
            val dalangBio = resources.getStringArray(R.array.data_dalang_description)
            val dalangImage = resources.obtainTypedArray(R.array.data_dalang_image)
            val dalangBorn = resources.getStringArray(R.array.data_dalang_born)
            val dataDalangOffline = ArrayList<DataItem>()
            for (i in dalangId.indices) {
                val dalang = DataItem(
                    dalangImage.getResourceId(i, -1),
                    dalangBorn[i],
                    dalangName[i],
                    dalangId[i],
                    dalangBio[i]
                )
                dataDalangOffline.add(dalang)
            }
            return dataDalangOffline
        }

    private fun initRv() {
        adapter = DalangAdapter()
        adapter.setList(listDalangOffline)
        binding.apply {
            rvDalang.layoutManager = LinearLayoutManager(this@DalangBioActivity)
            rvDalang.setHasFixedSize(true)
            rvDalang.adapter = adapter

            adapter.setOnItemClickCallback(object : DalangAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataItem) {
                    Intent(this@DalangBioActivity, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.EXTRA_PHOTO, data.image)
                        it.putExtra(DetailActivity.EXTRA_NAME, data.name)
                        it.putExtra(DetailActivity.EXTRA_DESK, data.biography)
                        it.putExtra(DetailActivity.EXTRA_DESK1, "Asal/Tempat Lahir : " + data.born)
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
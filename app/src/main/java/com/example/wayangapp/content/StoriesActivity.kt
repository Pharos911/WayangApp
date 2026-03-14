package com.example.wayangapp.content

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wayangapp.retrofit.responses.ListStoryItems
import com.example.wayangapp.MainActivity
import com.example.wayangapp.R
import com.example.wayangapp.adapter.StoriesAdapter
import com.example.wayangapp.content.detail.DetailActivity
import com.example.wayangapp.databinding.ActivityStoriesBinding

class StoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoriesBinding
    private lateinit var adapter: StoriesAdapter

    private lateinit var sharedPreferences: SharedPreferences
    val listStoryItem = ArrayList<ListStoryItems>()

    private var SHARED_PREF_NAME = "mypref"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

        listStoryItem.addAll(listStoryOffline)
//        setStory()
        initRv()

        binding.btnBackToHome.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private val listStoryOffline : ArrayList<ListStoryItems>
        get() {
            val storyId = resources.getIntArray(R.array.data_story_id)
            val storyName = resources.getStringArray(R.array.data_story_name)
            val storyDesk =  resources.getStringArray(R.array.data_story_description)
            val storyChar = resources.getStringArray(R.array.data_story_character)
            val storyImage = resources.obtainTypedArray(R.array.data_story_image)
            val dataStoryOffline = ArrayList<ListStoryItems>()
            for (i in storyId.indices){
                val story =  ListStoryItems(
                    storyImage.getResourceId(i, -1),
                    storyDesk[i],
                    storyChar[i],
                    storyId[i],
                    storyName[i]
                )
                dataStoryOffline.add(story)
            }
            return dataStoryOffline
        }

    private fun initRv() {
        adapter = StoriesAdapter()
        adapter.setList(listStoryOffline)
        binding.apply {
            rvMcv.layoutManager = LinearLayoutManager(this@StoriesActivity)
            rvMcv.setHasFixedSize(true)
            rvMcv.adapter = adapter

            adapter.setOnItemClickCallback(object : StoriesAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ListStoryItems) {
                    Intent(this@StoriesActivity, DetailActivity::class.java).also {
                        it.putExtra(DetailActivity.EXTRA_PHOTO, data.image)
                        it.putExtra(DetailActivity.EXTRA_NAME, data.title)
                        it.putExtra(DetailActivity.EXTRA_DESK, data.description)
                        it.putExtra(DetailActivity.EXTRA_DESK1, data.character)
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
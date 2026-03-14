package com.example.wayangapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.wayangapp.retrofit.responses.ListStoryItems
import com.example.wayangapp.databinding.ItemMuseumCeritaVideoBinding

class StoriesAdapter : RecyclerView.Adapter<StoriesAdapter.McvViewHolder>() {

    private val list = ArrayList<ListStoryItems>()
    private var onItemClickCallback: OnItemClickCallback? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setList(data: ArrayList<ListStoryItems>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class McvViewHolder(private val binding: ItemMuseumCeritaVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(stories: ListStoryItems) {
            binding.apply {
                Glide.with(itemView)
                    .load(stories.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .fitCenter()
                    .into(imItemMcv)
                tvItemTitleMcv.text = stories.title
                tvItemDeskMcv.text = stories.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): McvViewHolder {
        val view =
            ItemMuseumCeritaVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return McvViewHolder(view)
    }

    override fun onBindViewHolder(holder: McvViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListStoryItems)
    }
}
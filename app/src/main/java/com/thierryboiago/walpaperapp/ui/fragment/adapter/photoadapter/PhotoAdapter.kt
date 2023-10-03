package com.thierryboiago.walpaperapp.ui.fragment.adapter.photoadapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.ui.fragment.adapter.viewholder.PhotoViewHolder

class PhotoAdapter(
    private  val clickCallBack: ((PhotoDomain) -> Unit)
):PagingDataAdapter<PhotoDomain, PhotoViewHolder>(differCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder.create(parent, clickCallBack)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let{
            holder.bind(it)
        }
    }

    companion object{
        private val differCallback = object : DiffUtil.ItemCallback<PhotoDomain>(){
            override fun areItemsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: PhotoDomain, newItem: PhotoDomain) =
                oldItem == newItem

        }
    }
}
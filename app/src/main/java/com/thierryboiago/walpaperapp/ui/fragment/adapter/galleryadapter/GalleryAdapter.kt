package com.thierryboiago.walpaperapp.ui.fragment.adapter.galleryadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.ui.fragment.adapter.viewholder.PhotoViewHolder

class GalleryAdapter(
    private  val clickCallBack: ((PhotoDomain) -> Unit),
    private val longClickCallBack: ((PhotoDomain) -> Unit)
) :ListAdapter<PhotoDomain, PhotoViewHolder>(differCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return  PhotoViewHolder.create(parent, clickCallBack, longClickCallBack)
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
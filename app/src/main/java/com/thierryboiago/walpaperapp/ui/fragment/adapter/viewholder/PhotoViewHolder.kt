package com.thierryboiago.walpaperapp.ui.fragment.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thierryboiago.core.domain.model.PhotoDomain
import com.thierryboiago.walpaperapp.databinding.ItemPhotoBinding
import com.thierryboiago.walpaperapp.extensions.loadBlurredImageWithPlaceholder

class PhotoViewHolder(
    itemBiding: ItemPhotoBinding,
    private val clickCallBack: (PhotoDomain) -> Unit,
    private val longClickCallBack: (PhotoDomain) -> Unit
): RecyclerView.ViewHolder(itemBiding.root) {

    private val image = itemBiding.image
    private val name = itemBiding.name

    fun bind(photo: PhotoDomain){
        image.loadBlurredImageWithPlaceholder(
            imageUrl = photo.srcDomain?.original,
            placeholderColor = photo.avgColor
        )

        name.text = photo.photographer
        itemView.setOnClickListener{
            clickCallBack.invoke(photo)
        }
        itemView.setOnLongClickListener{
            longClickCallBack.invoke(photo)
            return@setOnLongClickListener true
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            photoCallBack: (PhotoDomain) -> Unit,
            longClickCallBack: ((PhotoDomain) -> Unit)
        ): PhotoViewHolder{
            val inflater = LayoutInflater.from(parent.context)
            val itemBinding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(itemBinding, photoCallBack, longClickCallBack)
        }
    }
}
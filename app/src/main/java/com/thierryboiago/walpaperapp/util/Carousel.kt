package com.thierryboiago.walpaperapp.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewFlipper
import com.thierryboiago.core.domain.model.CarouselDomain
import com.thierryboiago.walpaperapp.databinding.ItemCarouselBinding
import com.thierryboiago.walpaperapp.extensions.loadBlurredImageWithPlaceholder

class Carousel(
    context: Context,
    attr: AttributeSet?
) : ViewFlipper(context, attr) {
    private var carouselDomainList = mutableListOf<CarouselDomain>()


    fun setup(carouselDomainList: MutableList<CarouselDomain>){
        this.carouselDomainList = carouselDomainList
    }

    fun setLayout(){
        carouselDomainList.forEach{
            val binding = ItemCarouselBinding.inflate(LayoutInflater.from(context), this, false)
            binding.apply {
                imgCarousel.loadBlurredImageWithPlaceholder(it.imageUrl, it.placeholderColor)
                txtCategory.text = it.categoryName
                addView(root)
            }
        }
    }
}
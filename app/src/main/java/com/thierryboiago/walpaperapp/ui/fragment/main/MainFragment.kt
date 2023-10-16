package com.thierryboiago.walpaperapp.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.thierryboiago.walpaperapp.R
import com.thierryboiago.walpaperapp.databinding.FragmentMainBinding
import com.thierryboiago.walpaperapp.framework.local.Carousel
import com.thierryboiago.walpaperapp.ui.fragment.categories.FragmentCategories
import com.thierryboiago.walpaperapp.ui.fragment.collections.FragmentCollections
import com.thierryboiago.walpaperapp.ui.fragment.popular.PopularFragment
import com.thierryboiago.walpaperapp.ui.pageadapter.ViewPagerAdapter


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val tabTitles = listOf("Popular", "Collections", "Categories")
    private val fragments = listOf(PopularFragment(), FragmentCollections(), FragmentCategories())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initViewPager()
        initTabLayout()
        initCarousel()
        detail()
    }

    private fun initToolbar() {
        with(binding.toolbar) {
            title = "Wallpapers"
            (activity as AppCompatActivity).setSupportActionBar(this)
        }

    }

    private fun initViewPager() {
        val pageAdapter = ViewPagerAdapter(requireActivity(), fragments)
        binding.run {
            viewPager.adapter = pageAdapter
        }
    }

    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun detail(){
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_galleryFragment)
        }
    }

    fun initCarousel(){
        with(binding.carouselViewFlipper){
            setOutAnimation(activity, android.R.anim.slide_out_right)
            setup(Carousel.list)
            setLayout()
        }
    }

     
}
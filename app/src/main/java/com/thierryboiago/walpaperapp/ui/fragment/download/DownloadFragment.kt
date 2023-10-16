package com.thierryboiago.walpaperapp.ui.fragment.download

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.thierryboiago.walpaperapp.R
import com.thierryboiago.walpaperapp.databinding.FragmentDownlaodBinding

class DownloadFragment : Fragment() {
 private lateinit var binding: FragmentDownlaodBinding
 private val args: DownloadFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDownlaodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadImage(args.image[0])
        backButton()
        buttonSheet()
    }
    private fun  loadImage(url: String){
        Glide.with(requireContext())
            .load(url)
            .centerCrop()
            .fallback(R.drawable.baseline_broken)
            .into(binding.downloadImage)
    }
    private fun backButton(){
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun buttonSheet(){
        val bottomSheet = BottomSheetDownload(args.image[0], args.image[1])
        binding.downloadImage.setOnClickListener{
            bottomSheet.show(requireActivity().supportFragmentManager, "BottomSheet")
        }
    }
}
package com.example.mynewsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mynewsapp.databinding.FragmentMoreDietailsBinding
import com.example.mynewsapp.ui.vm.HomeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMoreDietailsBinding
    val args: MoreDetailsFragmentArgs by navArgs()

    val vm:HomeVM by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMoreDietailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clickedArticle = args.newsData
        Glide.with(requireContext()).load(clickedArticle!!.urlToImage).into(binding.newsImage)
        binding.newsArticle.text = clickedArticle.description
        binding.newsTitle.text = clickedArticle.title
        binding.newsAuthor.text = clickedArticle.author

        binding.iconSave.setOnClickListener {
            vm.saveArticle(clickedArticle)
            Toast.makeText(requireContext(), "article saved", Toast.LENGTH_SHORT).show()
        }

    }

}
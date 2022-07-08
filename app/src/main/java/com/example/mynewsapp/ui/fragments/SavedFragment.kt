package com.example.mynewsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynewsapp.core.adapters.NewsAdapter
import com.example.mynewsapp.databinding.FragmentSavedBinding
import com.example.mynewsapp.ui.vm.HomeVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment() {
lateinit var binding: FragmentSavedBinding
    val vm: HomeVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.savedArticlesRecycler.setHasFixedSize(true)
        binding.savedArticlesRecycler.layoutManager = LinearLayoutManager(requireContext())


        vm.getSavedArticles().observe(requireActivity()){
            val newsAdapter = NewsAdapter(it,requireContext(),null)
            binding.savedArticlesRecycler.adapter = newsAdapter
        }
    }

}
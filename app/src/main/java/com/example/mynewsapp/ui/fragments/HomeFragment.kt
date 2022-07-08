package com.example.mynewsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.core.adapters.NewsAdapter
import com.example.mynewsapp.core.adapters.PopularNewsAdapter
import com.example.mynewsapp.core.helper.FragmentConnection
import com.example.mynewsapp.core.helper.ResponseWrapper
import com.example.mynewsapp.databinding.FragmentHomeBinding
import com.example.mynewsapp.core.models.Article
import com.example.mynewsapp.core.models.NewsResponse
import com.example.mynewsapp.ui.vm.HomeVM
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment(), FragmentConnection {


    private val homeVM: HomeVM by viewModels()

    private var newsAdapter: NewsAdapter? = null
    private lateinit var popularNewsAdapter: PopularNewsAdapter
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.NewsRecycler.setHasFixedSize(true)
        binding.NewsRecycler.layoutManager = LinearLayoutManager(requireContext())

        binding.trendingNewsRecycler.setHasFixedSize(true)
        binding.trendingNewsRecycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)





        homeVM.newsLiveData.observe(requireActivity()) {
            when (it) {
                is ResponseWrapper.Success -> {
                    hideProgress()
                    setnData(it.data?.articles!!)
                }
                is ResponseWrapper.Error -> {
                    hideProgress()
                    makeToast(it.message+it.errorCode)
                }
                is ResponseWrapper.Loading -> {
                    showProgress()
                }
            }
        }
    }


    private fun setnData(list: List<Article>) {
        newsAdapter = NewsAdapter(list, requireContext(), this)
        popularNewsAdapter = PopularNewsAdapter(list, requireContext(), this)

        binding.NewsRecycler.adapter = newsAdapter
        binding.trendingNewsRecycler.adapter = popularNewsAdapter
    }





    private fun hideProgress() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun makeToast(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onTransist(newsData: Article) {
        Toast.makeText(requireContext(), "interface is working", Toast.LENGTH_SHORT).show()
        val action = HomeFragmentDirections.actionHomeFragmentToMoreDietailsFragment(newsData)
        Navigation.findNavController(requireView()).navigate(action)
    }


}



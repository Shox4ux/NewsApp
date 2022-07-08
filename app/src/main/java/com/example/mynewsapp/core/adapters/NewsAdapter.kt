package com.example.mynewsapp.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.core.helper.FragmentConnection
import com.example.mynewsapp.databinding.ItemPopularBinding
import com.example.mynewsapp.core.models.Article

class NewsAdapter(private val list: List<Article>?, private val context: Context, private val listener: FragmentConnection?): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemPopularBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list?.get(position)?.urlToImage).into(holder.binding.newsImage)
        holder.binding.newsTitle.text = list!![position].title
        holder.binding.newsAuthor.text = list[position].source!!.name


        holder.binding.itemContainer.setOnClickListener {
//            callback(list[position])
            listener?.onTransist(list[position])

        }



   }

    override fun getItemCount(): Int {
        return list!!.size
    }


    class ViewHolder(val binding: ItemPopularBinding) : RecyclerView.ViewHolder(binding.root)



}



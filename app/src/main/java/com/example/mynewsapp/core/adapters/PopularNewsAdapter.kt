package com.example.mynewsapp.core.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mynewsapp.core.helper.FragmentConnection
import com.example.mynewsapp.databinding.ItemInTrendBinding
import com.example.mynewsapp.core.models.Article

class PopularNewsAdapter(private val list: List<Article>?, private val context: Context, private val listener: FragmentConnection): RecyclerView.Adapter<PopularNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemInTrendBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(list?.get(position)?.urlToImage).into(holder.binding.popImage)
        holder.binding.popTitle.text = list!![position].title
//        holder.binding.textDescription.text = list[position].description
//        holder.binding.newsAuthor.text = list[position].source!!.name

        holder.binding.popImage.setOnClickListener { View.OnClickListener{
                listener.onTransist(list[position])
        } }
   }

    override fun getItemCount(): Int {
        return list!!.size
    }


    class ViewHolder(val binding: ItemInTrendBinding) : RecyclerView.ViewHolder(binding.root)

}
package com.technipixl.exo1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.technipixl.exo1.databinding.RowCharacterBinding
import com.technipixl.exo1.databinding.RowComicBinding

class ComicAdapter(var comics : MutableList<Item>):RecyclerView.Adapter<ComicAdapter.ComicViewHolder>(){
    class ComicViewHolder(var binding: RowComicBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(comic: Item){
            binding.nameTextView.text = comic.name
        }
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comics[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = RowComicBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ComicAdapter.ComicViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return comics.size
    }
}
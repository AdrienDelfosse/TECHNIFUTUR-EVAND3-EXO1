package com.technipixl.exo1

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.technipixl.exo1.databinding.RowCharacterBinding

class CharacterAdapter(var characters: MutableList<Result>, val clickListener: OnItemClickedListener):RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    interface OnItemClickedListener{
        fun onItemClicked(character : Result)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position], clickListener)
    }

    class ViewHolder (var binding: RowCharacterBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(result : Result, clickListener: OnItemClickedListener){
            var url = result.thumbnail?.path +"."+ result.thumbnail?.extension
            Picasso.get()
                .load(url)
                .into(binding.characterImageView)

            binding.characterTextView.text = result.name
            itemView.setOnClickListener{
                clickListener.onItemClicked(result)
            }
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }


}
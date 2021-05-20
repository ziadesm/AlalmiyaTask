package com.task.alalmiyatask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.task.alalmiyatask.R
import com.task.alalmiyatask.pojo.CharacterModel
import kotlinx.android.synthetic.main.recycler_item_character_count.view.*

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.character_name == newItem.character_name
        }

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    // ListDiffer to efficiently deal with changes in the RecyclerView
    val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(list: List<CharacterModel>) = differ.submitList(list)

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_character_count, parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val char = differ.currentList[position]
        // set item data
        holder.itemView.apply {
            character_name.text = char.character_name
            character_count.text = "${char.character_count}"
        }
    }
}
package com.todaysquare.patterntasknote.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.FavoriteNotice
import com.todaysquare.patterntasknote.databinding.ItemFavoriteBinding

class FavoriteAdapter(private val itemClick: (FavoriteNotice) -> Unit,
    private val numClick: (FavoriteNotice) -> Unit) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val items = ArrayList<FavoriteNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemFavoriteBinding>(
            LayoutInflater.from(parent.context), R.layout.item_favorite, parent, false)
        val viewHolder = ViewHolder(binding)

        binding.root.setOnClickListener {
            itemClick(items[viewHolder.adapterPosition])

        }
        binding.favoriteTvNum.setOnClickListener {
            numClick(items[viewHolder.adapterPosition])

        }

        return viewHolder

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)

        }
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FavoriteNotice) {
            binding.item = item
            binding.executePendingBindings()

        }
    }

    fun addItem(items: List<FavoriteNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()

    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()

    }
}
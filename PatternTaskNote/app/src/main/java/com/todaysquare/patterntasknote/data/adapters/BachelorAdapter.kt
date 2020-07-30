package com.todaysquare.patterntasknote.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.BachelorNotice
import com.todaysquare.patterntasknote.databinding.ItemBachelorBinding

class BachelorAdapter(private val itemClick: (BachelorNotice) -> Unit,
    private val numClick: (BachelorNotice) -> Unit) : RecyclerView.Adapter<BachelorAdapter.ViewHolder>() {

    private val items = ArrayList<BachelorNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil
            .inflate<ItemBachelorBinding>(LayoutInflater.from(parent.context),
                R.layout.item_bachelor, parent, false)
        val viewHolder = ViewHolder(binding)

        binding.root.setOnLongClickListener {
            itemClick(items[viewHolder.adapterPosition])

        }
        binding.bachelorTvNum.setOnClickListener {
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

    inner class ViewHolder(private val binding: ItemBachelorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BachelorNotice) {
            binding.item = item
            binding.executePendingBindings()

        }
    }

    fun addItems(items: List<BachelorNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()

    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()

    }
}
package com.todaysquare.patterntasknote.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.GeneralNotice
import com.todaysquare.patterntasknote.databinding.ItemGeneralBinding

class GeneralAdapter(private val itemClick: (GeneralNotice) -> Unit,
    private val numClick: (GeneralNotice) -> Unit) : RecyclerView.Adapter<GeneralAdapter.ViewHolder>() {

    private val items = ArrayList<GeneralNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemGeneralBinding>(
            LayoutInflater.from(parent.context), R.layout.item_general, parent, false)
        val viewHolder = ViewHolder(binding)

        binding.root.setOnClickListener {
            itemClick(items[viewHolder.adapterPosition])

        }
        binding.generalTvNum.setOnClickListener {
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

    inner class ViewHolder(private val binding: ItemGeneralBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GeneralNotice) {
            binding.item = item
            binding.executePendingBindings()

        }
    }

    fun addItems(items: List<GeneralNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()

    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()

    }
}
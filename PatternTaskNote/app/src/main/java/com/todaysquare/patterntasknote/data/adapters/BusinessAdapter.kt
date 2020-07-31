package com.todaysquare.patterntasknote.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

import com.todaysquare.patterntasknote.R
import com.todaysquare.patterntasknote.data.databases.entities.BusinessNotice
import com.todaysquare.patterntasknote.databinding.ItemBusinessBinding

class BusinessAdapter(private val itemClick: (BusinessNotice) -> Unit,
    private val numClick: (BusinessNotice) -> Unit) : RecyclerView.Adapter<BusinessAdapter.ViewHolder>() {

    private val items = ArrayList<BusinessNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemBusinessBinding>(LayoutInflater.from(parent.context),
                R.layout.item_business, parent, false)
        val viewHolder = ViewHolder(binding)

        binding.root.setOnClickListener {
            itemClick(items[viewHolder.adapterPosition])

        }
        binding.businessTvNum.setOnClickListener {
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

    inner class ViewHolder(private val binding: ItemBusinessBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessNotice) {
            binding.item = item
            binding.executePendingBindings()

        }
    }

    fun addItems(items: List<BusinessNotice>) {
        this.items.addAll(items)
        notifyDataSetChanged()

    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()

    }
}
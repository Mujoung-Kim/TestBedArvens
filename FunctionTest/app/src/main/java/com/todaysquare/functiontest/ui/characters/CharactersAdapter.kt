package com.todaysquare.functiontest.ui.characters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.todaysquare.functiontest.data.databases.entites.Character
import com.todaysquare.functiontest.databinding.ItemCharacterBinding

class CharactersAdapter(private val listener: CharacterItemListener)
    : RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)

    }

    private val items = ArrayList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)

        return CharacterViewHolder(binding, listener)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(items[position])

    fun setItems(items: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()

    }
}

class CharacterViewHolder(private val itemBinding: ItemCharacterBinding,
                          private val listener: CharactersAdapter.CharacterItemListener)
    : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var character: Character

    init {
        itemBinding.root.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        listener.onClickedCharacter(character.id)

    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Character) {
        this.character = item
        itemBinding.name.text = item.name
        itemBinding.speciesAndStatus.text = """${item.species} - ${item.status}"""

        Glide.with(itemBinding.root)
            .load(item.image)
            .transform(CircleCrop())
            .into(itemBinding.image)

    }
}
package com.todaysquare.functiontest.ui.characters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.todaysquare.functiontest.data.databases.entites.Character

class CharactersAdapter(private val listener: CharacterItemListener)
    : RecyclerView.Adapter<CharacterViewHolder>() {

    interface CharacterItemListener {
        fun onClickedCharacter(characterId: Int)

    }

    private val items = ArrayList<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding

        return CharacterViewHolder(binding)

    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun setItems(items: ArrayList<Character>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()

    }
}

class CharacterViewHolder(private val itemBinding: ItemCharacterBinding,
    private val listener: CharactersAdapter.CharacterItemListener)
    : RecyclerView.ViewHolder(itemBinding.root) {


}
package com.omeghabit.rickandmortyapi.model.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.omeghabit.rickandmortyapi.R
import com.omeghabit.rickandmortyapi.databinding.ItemCharacterBinding
import com.omeghabit.rickandmortyapi.databinding.ItemCharacterBinding.inflate

class CharacterAdapter( private var characters: List<Character> = emptyList()
) : RecyclerView.Adapter<CharacterAdapter.CharHolder>() {

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): CharacterAdapter.CharHolder {
        val item = inflate(LayoutInflater.from(parent.context), parent, false)
            return CharHolder(item)
    }

    override fun onBindViewHolder(holder: CharacterAdapter.CharHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    class CharHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.tvName.text = character.name
            binding.tvStatus.text = character.status
            binding.ivCharacter.load(character.image) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)

                val (colorRes, bgRes) = when (character.status.lowercase()) {
                    "alive" -> Pair(R.color.green, R.drawable.status_alive_bg)
                    "dead" -> Pair(R.color.red, R.drawable.status_dead_bg)
                    else -> Pair(R.color.gray, R.drawable.status_unknown_bg)
                }
                binding.tvStatus.setTextColor(ContextCompat.getColor(binding.root.context, colorRes))
                binding.tvStatus.background = (ContextCompat.getDrawable(binding.root.context, bgRes))

            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newItems: List<Character>) {
        characters = newItems
        notifyDataSetChanged()
    }

}
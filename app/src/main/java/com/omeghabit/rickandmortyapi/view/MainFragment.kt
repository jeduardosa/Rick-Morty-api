package com.omeghabit.rickandmortyapi.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.omeghabit.rickandmortyapi.databinding.FragmentMainBinding
import com.omeghabit.rickandmortyapi.model.adapter.CharacterAdapter
import com.omeghabit.rickandmortyapi.viewmodel.CharacterViewModel


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel = CharacterViewModel()
    private lateinit var adapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        loadData()
    }

    private fun setupRecyclerView() {
        adapter = CharacterAdapter()
        binding.RecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = this@MainFragment.adapter
        }
    }

    private fun setupObservers() {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter.submitList(characters)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error.isNotEmpty()) {
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadData() {
        viewModel.fetchCharacters()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        false
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
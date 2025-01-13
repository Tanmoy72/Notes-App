package com.paul.notesapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.paul.notesapp.R
import com.paul.notesapp.ViewModel.NotesViewModel
import com.paul.notesapp.databinding.FragmentHomeBinding
import com.paul.notesapp.ui.Adapter.NotesAdapter


class HomeFragment : Fragment() {

    lateinit var binding:FragmentHomeBinding
    val viewModel : NotesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)


        viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
            binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
            binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
        })


        binding.filterHigh.setOnClickListener {

            viewModel.getHighNotes().observe(viewLifecycleOwner, { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })

        }

        binding.filterImage.setOnClickListener {

            viewModel.getNotes().observe(viewLifecycleOwner, { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })

        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumNotes().observe(viewLifecycleOwner, { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })

        }

        binding.filterLow.setOnClickListener {

            viewModel.getLowNotes().observe(viewLifecycleOwner, { notesList ->
                binding.rcvAllNotes.layoutManager = GridLayoutManager(requireContext(),2)
                binding.rcvAllNotes.adapter = NotesAdapter(requireContext(),notesList)
            })

        }


        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }




}
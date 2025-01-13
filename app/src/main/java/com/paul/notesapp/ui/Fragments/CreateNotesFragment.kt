package com.paul.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.paul.notesapp.Model.Notes
import com.paul.notesapp.R
import com.paul.notesapp.ViewModel.NotesViewModel
import com.paul.notesapp.databinding.FragmentCreateNotesBinding

import java.util.Date


class CreateNotesFragment : Fragment() {
    lateinit var binding:FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel : NotesViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentCreateNotesBinding.inflate(layoutInflater,container,false)

        binding.pGreen.setImageResource(R.drawable.baseline_done_24)


       binding.pGreen.setOnClickListener {
           priority = "1"
           binding.pGreen.setImageResource(R.drawable.baseline_done_24)
           binding.pYellow.setImageResource(0)
           binding.pRed.setImageResource(0)
       }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pRed.setImageResource(0)
        }
        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.btnSaveNotes.setOnClickListener{
            createNotes(it)
        }




        return binding.root
    }

    private fun createNotes(it: View?) {
       val title=binding.editTitle.text.toString()
       val subTitle=binding.editSubTitle.text.toString()
       val notes=binding.editTextNotes.text.toString()
        val d: Date = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())
        //Log.e("TAN","createNotes: $notesDate")
        val data=Notes(
            null,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(),"Notes Create Successfully",Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)



    }


}
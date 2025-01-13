package com.paul.notesapp.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.paul.notesapp.Model.Notes
import com.paul.notesapp.R
import com.paul.notesapp.ViewModel.NotesViewModel
import com.paul.notesapp.databinding.FragmentEditNotesBinding
import java.util.Date


class EditNotesFragment : Fragment() {

    val oldNotes by navArgs<EditNotesFragmentArgs>()

    var priority: String = "1"

    lateinit var binding:FragmentEditNotesBinding
    val viewModel : NotesViewModel by viewModels()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentEditNotesBinding.inflate(layoutInflater,container,false)
        setHasOptionsMenu(true)


        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubTitle.setText(oldNotes.data.subTitle)
        binding.editTextNotes.setText(oldNotes.data.notes)

        when(oldNotes.data.priority){
            "1" ->{
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pred.setImageResource(0)

            }
            "2" ->{
                priority = "2"
                binding.pYellow.setImageResource(R.drawable.baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pred.setImageResource(0)

            }
            "3" ->{
                priority = "3"
                binding.pred.setImageResource(R.drawable.baseline_done_24)
                binding.pYellow.setImageResource(0)
                binding.pGreen.setImageResource(0)

            }

        }
        binding.btnEditSaveNotes.setOnClickListener {

            updateNotes(it)
        }
        



        return binding.root
    }



    private fun updateNotes(it: View?) {

        val title=binding.editTitle.text.toString()
        val subTitle=binding.editSubTitle.text.toString()
        val notes=binding.editTextNotes.text.toString()
        val d: Date = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy ", d.getTime())
        Log.e("TAN","update:$title title: $subTitle abc:$notes" )
        val data= Notes(
            oldNotes.data.id,
            title = title,
            subTitle = subTitle,
            notes = notes,
            date = notesDate.toString(),
            priority )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(),"Notes update Successfully ", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_delete){
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext(),R.style.bottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialogYes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialogNo)

            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                Navigation.findNavController(it!!).navigate(R.id.action_editNotesFragment_to_homeFragment)
            }

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }


            bottomSheet.show()

            //Log.e("TRP","Delete massage: bhbhhhuhuhuhujjjji")
        }
        return super.onOptionsItemSelected(item)

    }







}
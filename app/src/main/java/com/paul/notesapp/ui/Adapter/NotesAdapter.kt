package com.paul.notesapp.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.paul.notesapp.Model.Notes
import com.paul.notesapp.R
import com.paul.notesapp.databinding.ItemsNotesBinding
import com.paul.notesapp.ui.Fragments.HomeFragment
import com.paul.notesapp.ui.Fragments.HomeFragmentDirections


class NotesAdapter(val requireContext: Context,val notesList: List<Notes>) :RecyclerView.Adapter<NotesAdapter.notesViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemsNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false))

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
        val data=notesList[position]
        holder.binding.notesTitle.text= data.title
        holder.binding.notesSubTitle.text= data.subTitle
        holder.binding.notesDate.text= data.date

        when(data.priority){
            "1" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3" ->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }

        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditNotesFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }


    class notesViewHolder(val binding:ItemsNotesBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
package com.paul.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.paul.notesapp.Database.NotesDatabase
import com.paul.notesapp.Model.Notes
import com.paul.notesapp.Repository.NotesRepository

class NotesViewModel(application: Application):AndroidViewModel(application) {
    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDataBaseInstance(application).myNotesDao()
        repository= NotesRepository(dao)
    }

    fun addNotes(notes: Notes){
        repository.insetNotes(notes)

    }

    fun getNotes():LiveData<List<Notes>> = repository.getAllNotes()

    fun getHighNotes(): LiveData<List<Notes>> = repository.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = repository.getMediumNotes()

    fun getLowNotes(): LiveData<List<Notes>> = repository.getLowNotes()


    fun deleteNotes(id:Int){
        repository.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        repository.updateNotes(notes)
    }

}
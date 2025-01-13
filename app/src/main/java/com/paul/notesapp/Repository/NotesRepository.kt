package com.paul.notesapp.Repository

import androidx.lifecycle.LiveData
import com.paul.notesapp.Dao.NotesDao
import com.paul.notesapp.Model.Notes

class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes>> = dao.getNotes()

    fun getHighNotes(): LiveData<List<Notes>> = dao.getHighNotes()

    fun getMediumNotes(): LiveData<List<Notes>> = dao.getMediumNotes()

    fun getLowNotes(): LiveData<List<Notes>> = dao.getLowNotes()


    fun insetNotes(notes: Notes)
    {
        dao.insertNotes(notes)
    }

    fun deleteNotes(id:Int)
    {
        dao.deleteNotes(id)
    }

    fun updateNotes(notes: Notes){
        dao.updateNotes(notes)
    }
}
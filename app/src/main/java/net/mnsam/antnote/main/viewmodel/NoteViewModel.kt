package net.mnsam.antnote.main.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import net.mnsam.antnote.datastorage.local.entity.Note
import net.mnsam.antnote.datastorage.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/2/18.
 */
class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository = NoteRepository()

    fun insert(note: Note) = noteRepository.insert(note)
    fun getAllNotes() = noteRepository.getAllNotes()
    fun getNoteDetail(id: Long) = noteRepository.getNoteDetail(id)
}
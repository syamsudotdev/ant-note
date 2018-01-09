package net.mnsam.antnote.data.repository

import android.os.AsyncTask
import net.mnsam.antnote.ApplicationBase
import net.mnsam.antnote.data.local.dao.NoteDao
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */
open class NoteRepository {
    private val noteDao: NoteDao = ApplicationBase.roomDatabase.noteDao

    fun fetchAllNotes(): MutableList<Note> = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    private class InsertAsyncTask(private val asyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note): Void? {
            asyncTaskDao.insert(params[0])
            return null
        }
    }

    fun getNoteDetail(id: Long): Note = noteDao.findById(id)
}
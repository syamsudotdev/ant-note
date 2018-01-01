package net.mnsam.antnote.main.database.repository

import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import net.mnsam.antnote.main.database.ApplicationDatabase
import net.mnsam.antnote.main.database.dao.NoteDao
import net.mnsam.antnote.main.database.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */
open class NoteRepository {
    private val noteDao: NoteDao = ApplicationDatabase.database.noteDao

    fun getAllNotes(): LiveData<MutableList<Note>> = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    private class InsertAsyncTask(private val asyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note): Void? {
            asyncTaskDao.insert(params[0])
            return null
        }
    }
}
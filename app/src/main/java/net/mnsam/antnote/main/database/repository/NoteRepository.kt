package net.mnsam.antnote.main.database.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import net.mnsam.antnote.main.database.NoteRoomDatabase
import net.mnsam.antnote.main.database.dao.NoteDao
import net.mnsam.antnote.main.database.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */
class NoteRepository(application: Application) {
    private val noteDao: NoteDao
    private val allNotes: LiveData<MutableList<Note>>

    init {
        val db: NoteRoomDatabase = NoteRoomDatabase.getDatabase(application.baseContext)
        noteDao = db.noteDao
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        insertAsyncTask(noteDao).execute(note)
    }

    private class insertAsyncTask(private val asyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {
        override fun doInBackground(vararg params: Note): Void? {
            asyncTaskDao.insert(params[0])
            return null
        }
    }
}
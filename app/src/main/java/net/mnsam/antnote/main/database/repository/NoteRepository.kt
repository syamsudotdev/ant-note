package net.mnsam.antnote.main.database.repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import net.mnsam.antnote.main.database.NoteRoomDatabase
import net.mnsam.antnote.main.database.dao.NoteDao
import net.mnsam.antnote.main.database.model.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */
class NoteRepository(val application: Application) {
    private val noteDao: NoteDao?
    private val allNotes: LiveData<MutableList<Note>>

    init {
        val db: NoteRoomDatabase? = NoteRoomDatabase(application) {}.getDatabase()
        noteDao = db.noteDao
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        insertAsyncTask(noteDao).execute(note)
    }

    private class insertAsyncTask constructor(private val mAsyncTaskDao: NoteDao) : AsyncTask<Note, Void, Void>() {

        override fun doInBackground(vararg params: Note): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}
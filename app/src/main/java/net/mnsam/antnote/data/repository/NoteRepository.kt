package net.mnsam.antnote.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.mnsam.antnote.data.local.dao.NoteDao
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.util.CreateObservable
import java.util.concurrent.Callable
import javax.inject.Inject

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */
class NoteRepository @Inject internal constructor(private val noteDao: NoteDao) {
    private fun fetchAllNotes(): MutableList<Note> = noteDao.getAllNotes()

    fun getObservableAllNotes(): Observable<MutableList<Note>> {
        return CreateObservable()
                .observable(Callable<MutableList<Note>> { fetchAllNotes() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun insert(note: Note) {
        Completable.fromRunnable { noteDao.insert(note) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    fun getNoteDetail(id: Long): Note = noteDao.findById(id)

    fun updateNoteDetail(note: Note) = noteDao.update(note)

    fun deleteNote(note: Note) = noteDao.delete(note)
}
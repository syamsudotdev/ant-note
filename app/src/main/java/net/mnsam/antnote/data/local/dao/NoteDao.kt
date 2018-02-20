package net.mnsam.antnote.data.local.dao

import android.arch.persistence.room.*
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Dao
interface NoteDao {
    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findById(id: Long): Note

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): MutableList<Note>

    @Insert
    fun insert(note: Note): Long

    @Update
    fun update(note: Note)
}
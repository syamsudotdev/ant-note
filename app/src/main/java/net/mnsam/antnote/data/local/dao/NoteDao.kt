package net.mnsam.antnote.data.local.dao

import android.arch.persistence.room.*
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(note: Note): Long

    @Query("SELECT * FROM notes ORDER BY id ASC")
    fun getAllNotes(): MutableList<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findById(id: Long): Note

    @Update
    fun update(vararg notes: Note)

    @Delete
    fun delete(vararg notes: Note)
}
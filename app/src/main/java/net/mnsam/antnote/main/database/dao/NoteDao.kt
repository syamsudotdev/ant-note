package net.mnsam.antnote.main.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import net.mnsam.antnote.main.database.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insert(note: Note): Long

    @Query("SELECT * from notes ORDER BY id ASC")
    fun getAllNotes(): LiveData<MutableList<Note>>
}
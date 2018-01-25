package net.mnsam.antnote.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.mnsam.antnote.data.local.dao.NoteDao
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Database(entities = [(Note::class)], version = 1, exportSchema = false)
abstract class ApplicationRoomDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}
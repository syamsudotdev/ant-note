package net.mnsam.antnote.datastorage.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.mnsam.antnote.datastorage.local.dao.NoteDao
import net.mnsam.antnote.datastorage.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Database(entities = [(Note::class)], version = 1)
abstract class ApplicationRoomDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}
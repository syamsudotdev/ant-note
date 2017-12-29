package net.mnsam.antnote.main.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import net.mnsam.antnote.main.database.dao.NoteDao
import net.mnsam.antnote.main.database.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Database(entities = [(Note::class)], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
}
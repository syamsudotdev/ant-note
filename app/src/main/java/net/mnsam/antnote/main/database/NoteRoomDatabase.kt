package net.mnsam.antnote.main.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import net.mnsam.antnote.main.database.dao.NoteDao
import net.mnsam.antnote.main.database.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/27/17.
 */

@Database(entities = [(Note::class)], version = 1)
abstract class NoteRoomDatabase : RoomDatabase() {
    abstract var noteDao: NoteDao

    private var INSTANCE: NoteRoomDatabase? = null

    fun getDatabase(context: Context): NoteRoomDatabase {
        if (INSTANCE === null)
            synchronized(NoteRoomDatabase::class) {
                if (INSTANCE === null)
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            NoteRoomDatabase::class.java,
                            "ant_note").build()
            }
        return INSTANCE!!
    }
}
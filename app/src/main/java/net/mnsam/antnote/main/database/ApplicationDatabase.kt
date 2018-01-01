package net.mnsam.antnote.main.database

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.support.multidex.MultiDex

/**
 * Created by Mochamad Noor Syamsu on 12/29/17.
 */
class ApplicationDatabase : Application() {
    companion object {
        lateinit var database: NoteRoomDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
                .databaseBuilder(this, NoteRoomDatabase::class.java, "ant_note")
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
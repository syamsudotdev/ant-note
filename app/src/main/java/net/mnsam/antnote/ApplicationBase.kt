package net.mnsam.antnote

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.support.multidex.MultiDex
import net.mnsam.antnote.main.database.ApplicationDatabase

/**
 * Created by Mochamad Noor Syamsu on 12/29/17.
 */
class ApplicationBase : Application() {
    companion object {
        lateinit var database: ApplicationDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room
                .databaseBuilder(this, ApplicationDatabase::class.java, "ant_note")
                .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}
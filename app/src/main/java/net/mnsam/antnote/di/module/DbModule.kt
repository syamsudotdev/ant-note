package net.mnsam.antnote.di.module

import android.app.Application
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import net.mnsam.antnote.data.local.ApplicationRoomDatabase
import net.mnsam.antnote.data.local.dao.NoteDao
import net.mnsam.antnote.data.repository.NoteRepository
import javax.inject.Singleton

/**
 * Created by Mochamad Noor Syamsu on 1/17/18.
 */
@Module
class DbModule {
    @Provides
    @Singleton
    fun provideDbInstance(application: Application): ApplicationRoomDatabase =
            Room.databaseBuilder(application,
                    ApplicationRoomDatabase::class.java,
                    "ant_note").build()

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteRepository = NoteRepository(noteDao)

    @Provides
    @Singleton
    fun provideNoteDao(applicationRoomDatabase: ApplicationRoomDatabase): NoteDao =
            applicationRoomDatabase.noteDao
}
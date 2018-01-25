package net.mnsam.antnote.di.module

import dagger.Module
import dagger.Provides
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.list.presentation.MainPresenter
import net.mnsam.antnote.feature.list.presentation.implementation.MainPresenterImpl

/**
 * Created by Mochamad Noor Syamsu on 1/18/18.
 */
@Module
class MainPresenterModule {
    @Provides
    fun provideMainPresenter(noteRepository: NoteRepository): MainPresenter =
            MainPresenterImpl(noteRepository = noteRepository)
}
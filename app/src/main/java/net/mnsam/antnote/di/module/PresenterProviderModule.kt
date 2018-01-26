package net.mnsam.antnote.di.module

import dagger.Module
import dagger.Provides
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.create.CreateContract
import net.mnsam.antnote.feature.create.CreatePresenterImpl
import net.mnsam.antnote.feature.list.MainContract
import net.mnsam.antnote.feature.list.MainPresenterImpl

/**
 * Created by Mochamad Noor Syamsu on 1/18/18.
 */
@Module
class PresenterProviderModule {
    @Provides
    fun provideMainPresenter(noteRepository: NoteRepository): MainContract.MainPresenter =
            MainPresenterImpl(noteRepository = noteRepository)

    @Provides
    fun provideCreatePresenter(noteRepository: NoteRepository): CreateContract.CreatePresenter =
            CreatePresenterImpl(noteRepository)

}
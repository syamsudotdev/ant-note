package net.mnsam.antnote.di.module

import dagger.Module
import dagger.Provides
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.create.CreateContract
import net.mnsam.antnote.feature.create.CreatePresenter
import net.mnsam.antnote.feature.detail.DetailContract
import net.mnsam.antnote.feature.detail.DetailPresenter
import net.mnsam.antnote.feature.list.MainContract
import net.mnsam.antnote.feature.list.MainPresenter

/**
 * Created by Mochamad Noor Syamsu on 1/18/18.
 */
@Module
class PresenterModule {
    @Provides
    fun provideMainPresenter(noteRepository: NoteRepository): MainContract.Presenter =
            MainPresenter(noteRepository = noteRepository)

    @Provides
    fun provideCreatePresenter(noteRepository: NoteRepository): CreateContract.Presenter =
            CreatePresenter(noteRepository)

    @Provides
    fun provideDetailPresenter(noteRepository: NoteRepository): DetailContract.Presenter =
            DetailPresenter(noteRepository)
}
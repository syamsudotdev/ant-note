package net.mnsam.antnote.feature.create

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/12/18.
 */
class CreatePresenter(private val noteRepository: NoteRepository) :
        BasePresenterImpl<CreateContract.View>(), CreateContract.Presenter {

    override fun onSave(note: Note) =
            noteRepository.insertWithCompletable(note)
}
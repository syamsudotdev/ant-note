package net.mnsam.antnote.feature.detail

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
class DetailPresenter(private val noteRepository: NoteRepository) : BasePresenterImpl<DetailContract.View>(), DetailContract.Presenter {

    lateinit var note: Note

    override fun onDetailLoaded(note: Note) = view!!.showDetail(note)

    override fun onErrorLoad(message: String) = view!!.toastMessage(message)

    override fun onEditMode(): Boolean {
        view!!.editMode()
        return true
    }

    override fun onResume(idNote: Long) {
        view!!.observeDetail(noteRepository.getObservableNoteDetail(idNote))
    }

    override fun onSave(note: Note) = noteRepository.update(note)
}
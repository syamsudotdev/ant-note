package net.mnsam.antnote.feature.detail

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
class DetailPresenter(private val noteRepository: NoteRepository) : BasePresenterImpl<DetailContract.View>(), DetailContract.Presenter {

    lateinit var note: Note

    override fun onCreateMode() {

    }

    override fun onDetailLoaded(note: Note) = view!!.showDetail(note)

    override fun onDiscard() {
    }

    override fun onErrorLoad(message: String) = view!!.toastMessage(message)

    override fun onEditMode(inputMode: Int): Boolean {
        view!!.editMode(inputMode)
        return true
    }

    override fun onBeginObserve(idNote: Long) {
        view!!.observeDetail(noteRepository.getObservableNoteDetail(idNote))
    }

    override fun onSave(note: Note) = noteRepository.update(note)

    override fun onSaveClick() = view!!.save()

    override fun onViewMode() = view!!.viewMode()
}
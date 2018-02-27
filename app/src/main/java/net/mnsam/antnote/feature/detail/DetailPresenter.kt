package net.mnsam.antnote.feature.detail

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.util.InputMode

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
class DetailPresenter(private val noteRepository: NoteRepository) : BasePresenterImpl<DetailContract.View>(), DetailContract.Presenter {

    override fun onBeginObserve(idNote: Long) {
        view!!.observeDetail(noteRepository.getObservableNoteDetail(idNote))
    }

    override fun onDetailLoaded(note: Note) = view!!.showDetail(note)

    override fun onDiscard(): Boolean {
        return when {
            view!!.isInputStateNotEquals() -> {
                view!!.promptDiscard()
                false
            }
            view!!.getInputMode() != InputMode.VIEW -> {
                view!!.viewMode()
                false
            }
            else -> true
        }
    }

    override fun onErrorLoad(message: String) = view!!.toastMessage(message)

    override fun onEditMode(inputMode: Int): Boolean {
        if (inputMode == InputMode.VIEW) {
            view!!.editMode(InputMode.EDIT)
        } else {
            view!!.editMode(InputMode.CREATE)
        }
        return true
    }

    override fun onResume() {
        val inputMode = view!!.getInputMode()
        val idNote = view!!.getIdNote()
        if (inputMode != InputMode.CREATE && idNote != 0L) onBeginObserve(idNote)
        if (inputMode != InputMode.VIEW) onEditMode(inputMode)
    }

    override fun onSave(title: String, content: String) {
        if (title.isNotEmpty() && content.isNotEmpty()) {
            val note = Note(title = title, content = content)
            val idNote = view!!.getIdNote()
            if (idNote != 0L) note.id = idNote
            noteRepository.insertOrUpdate(note)
        }
    }

    override fun onSaveClick() = view!!.save()

    override fun onViewMode() = view!!.viewMode()
}
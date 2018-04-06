package net.mnsam.antnote.feature.detail

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.util.InputMode

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
class DetailPresenter(private val noteRepository: NoteRepository) : BasePresenterImpl<DetailContract.View>(), DetailContract.Presenter {

    private var idNote: Long = -1
    private var inputMode: Int = 0
    private var initialTitle = ""
    private var initialContent = ""

    override fun isInputStateChanged(): Boolean {
        return (initialTitle != view!!.getTitleState() ||
                initialContent != view!!.getContentState())
    }

    override fun isEditMode(): Boolean = inputMode == InputMode.EDIT

    override fun onBackPressed() {
        onDiscard()
        onViewMode()
    }

    override fun onBackActionBarPressed() {
        onDiscard()
        onViewMode()
    }

    override fun onBeginObserve(idNote: Long) {
        view!!.observeDetail(noteRepository.getObservableNoteDetail(idNote))
    }

    override fun onCreateOptionsMenu() {
        if (inputMode != InputMode.VIEW) {
            view!!.createMenuSaveMode()
        } else {
            view!!.createArrowHomeButton()
        }
    }

    override fun onDetailLoaded(note: Note) = view!!.showDetail(note)

    override fun onDiscard() {
        if (isInputStateChanged() && inputMode != InputMode.VIEW) view!!.promptDiscard()
    }

    override fun onErrorLoad(message: String) = view!!.toastMessage(message)

    override fun onEditMode(): Boolean {
        inputMode = InputMode.EDIT
        view!!.editMode()
        initialTitle = view!!.getTitleState()
        initialContent = view!!.getContentState()
        return true
    }

    override fun onResume() {
        if (inputMode == 0) inputMode = view!!.getInputMode()
        idNote = view!!.getIdNote()
        if (inputMode == InputMode.VIEW) {
            onBeginObserve(idNote)
            return
        }
        onEditMode()
        initialTitle = view!!.getTitleState()
        initialContent = view!!.getContentState()
        view!!.getTitleState()
    }

    override fun onSave(title: String, content: String) {
        if (title.isNotEmpty() && content.isNotEmpty()) {
            val note = Note(title = title, content = content)
            if (idNote >= 0L) note.id = idNote
            noteRepository.insertOrUpdate(note)
        }
    }

    override fun onSaveClick() = view!!.save()

    override fun onViewMode() {
        inputMode = InputMode.VIEW
        view!!.viewMode()
    }
}
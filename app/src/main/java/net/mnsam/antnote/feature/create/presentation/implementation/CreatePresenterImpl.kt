package net.mnsam.antnote.feature.create.presentation.implementation

import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.create.presentation.CreatePresenter
import net.mnsam.antnote.feature.create.presentation.CreateView

/**
 * Created by Mochamad Noor Syamsu on 1/12/18.
 */
class CreatePresenterImpl(private val noteRepository: NoteRepository) : CreatePresenter {

    private lateinit var createView: CreateView
    override fun onAttach(createView: CreateView) {
        this.createView = createView
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onBackAction() =
        createView.saveAction()

    override fun onSaveAction(note: Note) =
        noteRepository.insert(note)
}
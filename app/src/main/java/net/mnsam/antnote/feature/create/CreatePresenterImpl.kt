package net.mnsam.antnote.feature.create

import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/12/18.
 */
class CreatePresenterImpl(private val noteRepository: NoteRepository) : CreateContract.CreatePresenter {

    private lateinit var createView: CreateContract.CreateView
    override fun onAttach(createView: CreateContract.CreateView) {
        this.createView = createView
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onBackAction() =
        createView.saveAction()

    override fun onSaveAction(note: Note) =
        noteRepository.insert(note)
}
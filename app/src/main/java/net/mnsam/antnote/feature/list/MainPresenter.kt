package net.mnsam.antnote.feature.list

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
class MainPresenter(private var list: MutableList<Note> = mutableListOf(),
                    private val noteRepository: NoteRepository) :
        BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

    private var archivedNote: Note? = null
    private var archivedPosition = -1

    override fun onArchiveNote(position: Int) {
        archivedNote = list[position]
        archivedPosition = position
        noteRepository.negate(list[position].id!!)
        list.removeAt(position)
        view!!.showSnackbar()
        if (!list.isEmpty()) view!!.showList(list) else view!!.showEmptyPage()
    }

    override fun onErrorLoad(message: String) = view!!.toastMessage(message)

    override fun onFabClick() = view!!.navigateToCreate()

    override fun onListItemClick(position: Int) = view!!.navigateToDetail(list[position].id!!)

    override fun onLoadedData(list: MutableList<Note>) {
        this.list = list
        if (!list.isEmpty()) view!!.showList(list) else view!!.showEmptyPage()
    }

    override fun onRestoreNote() {
        if (archivedPosition >= 0 && archivedNote != null) {
            noteRepository.negate(archivedNote!!.id!!)
            view!!.restoreNote(archivedPosition, archivedNote!!)
        }
    }

    override fun onResume() = view!!.observeData(noteRepository.getObservableAllNotes())
}

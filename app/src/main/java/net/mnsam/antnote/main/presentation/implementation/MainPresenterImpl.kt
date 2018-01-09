package net.mnsam.antnote.main.presentation.implementation

import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.main.presentation.MainPresenter
import net.mnsam.antnote.main.presentation.MainView

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
class MainPresenterImpl(private val mainView: MainView, private val list: MutableList<Note>, private val noteRepository: NoteRepository) : MainPresenter {
    override fun onCreate() {
        val list = noteRepository.fetchAllNotes()
        if (list.isEmpty())
            mainView.showEmptyList()
        else
            mainView.showList(list)
    }

    override fun onResume() {}

    override fun onPause() {}

    override fun onListItemClick(position: Int) {
        mainView.navigateToDetail(list[position].id!!)
    }
}
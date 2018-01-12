package net.mnsam.antnote.feature.list.presentation.implementation

import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.list.presentation.MainPresenter
import net.mnsam.antnote.feature.list.presentation.MainView

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
class MainPresenterImpl(private val mainView: MainView,
                        private val list: MutableList<Note>,
                        private val noteRepository: NoteRepository) : MainPresenter {
    override fun onCreate() {
        mainView.observeData(noteRepository.getObservableAllNotes())
    }

    override fun onErrorLoad(message: String) {
        mainView.toastMessage(message)
    }

    override fun onLoadedData(list: MutableList<Note>) =
            if (!list.isEmpty()) mainView.showList(list)
            else mainView.showEmptyPage()

    override fun onResume() {}

    override fun onPause() {}

    override fun onListItemClick(position: Int) {
        mainView.navigateToDetail(list[position].id!!)
    }

    override fun onFabClick() {
        mainView.navigateToCreate()
    }
}
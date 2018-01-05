package net.mnsam.antnote.main.presentation.implementation

import net.mnsam.antnote.datastorage.local.entity.Note
import net.mnsam.antnote.main.presentation.MainPresenter

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
class MainPresenterImpl(val list: MutableList<Note>) : MainPresenter {
    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {}

    override fun onItemClick(position: Int): Note {
        val note = list.get(position)
        return noteRepository.getNoteDetail(note.id!!)
    }
}
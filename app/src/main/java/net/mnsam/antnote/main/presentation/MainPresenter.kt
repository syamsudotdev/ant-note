package net.mnsam.antnote.main.presentation

import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.datastorage.local.entity.Note
import net.mnsam.antnote.datastorage.repository.NoteRepository

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface MainPresenter : BasePresenter {
    var noteRepository: NoteRepository()
    fun onItemClick(position: Int): Note
}
package net.mnsam.antnote.main.presentation

import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.datastorage.local.entity.Note
import net.mnsam.antnote.main.adapter.NoteAdapter

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface MainView : BaseView {
    fun showList(list: MutableList<Note>)
    fun showDetail(note: Note)
    fun setClickListener(noteAdapter: NoteAdapter)
}
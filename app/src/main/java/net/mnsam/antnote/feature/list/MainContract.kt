package net.mnsam.antnote.feature.list

import io.reactivex.Observable
import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.feature.list.recycler.NoteAdapter.AdapterClickListener

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
interface MainContract {

    interface Presenter : BasePresenter<View> {
        fun attachAdapter(adapter: View.NoteAdapter)
        fun getNoteCount(): Int
        fun onArchiveNote(position: Int)
        fun onBindNoteRowViewAtPosition(holder: View.NoteRowView, position: Int)
        fun onErrorLoad(message: String)
        fun onFabClick()
        fun onLoadedData(list: MutableList<Note>)
        fun onRestoreNote()
        fun onResume()
    }

    interface View : BaseView {
        fun navigateToDetail(id: Long)
        fun navigateToCreate()
        fun observeData(observable: Observable<MutableList<Note>>)
        fun showEmptyPage()
        fun showList(list: MutableList<Note>)
        fun showSnackbar()

        interface NoteRowView {
            fun setTitle(title: String)
            fun setContent(content: String)
            fun setClickListener(listener: AdapterClickListener)
        }

        interface NoteAdapter {
            fun addToPosition(position: Int)
            fun itemRangeInserted(positionStart: Int, count: Int)
            fun removeAtPosition(position: Int)
        }

    }

}

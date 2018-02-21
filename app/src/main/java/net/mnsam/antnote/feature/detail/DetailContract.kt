package net.mnsam.antnote.feature.detail

import io.reactivex.Observable
import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
interface DetailContract {

    interface Presenter : BasePresenter<View> {
        fun onBeginObserve(idNote: Long)
        fun onCreateMode()
        fun onDetailLoaded(note: Note)
        fun onDiscard()
        fun onErrorLoad(message: String)
        fun onEditMode(inputMode: Int): Boolean
        fun onSave(note: Note)
        fun onSaveClick()
        fun onViewMode()
    }

    interface View : BaseView {
        fun promptDiscard()
        fun editMode(inputMode: Int)
        fun observeDetail(observable: Observable<Note>)
        fun save()
        fun showDetail(note: Note)
        fun viewMode()
    }
}
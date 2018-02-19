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
        fun onResume(idNote: Long)
        fun onDetailLoaded(note: Note)
        fun onErrorLoad(message: String)
        fun onEditMode(): Boolean
        fun onSave(note: Note)
    }

    interface View : BaseView {
        fun editMode()
        fun observeDetail(observable: Observable<Note>)
        fun showDetail(note: Note)
        fun save()
    }
}
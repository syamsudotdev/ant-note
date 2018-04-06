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
        fun isInputStateChanged(): Boolean
        fun isEditMode(): Boolean
        fun onBackPressed()
        fun onBackActionBarPressed()
        fun onBeginObserve(idNote: Long)
        fun onCreateOptionsMenu()
        fun onDetailLoaded(note: Note)
        fun onDiscard()
        fun onErrorLoad(message: String)
        fun onEditMode(): Boolean
        fun onResume()
        fun onSave(title: String, content: String)
        fun onSaveClick()
        fun onViewMode()
    }

    interface View : BaseView {
        fun createMenuSaveMode()
        fun createArrowHomeButton()
        fun editMode()
        fun getContentState(): String
        fun getTitleState(): String
        fun getIdNote(): Long
        fun getInputMode(): Int
        fun observeDetail(observable: Observable<Note>)
        fun promptDiscard()
        fun save()
        fun showDetail(note: Note)
        fun viewMode()
    }
}
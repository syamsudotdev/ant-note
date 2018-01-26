package net.mnsam.antnote.feature.create

import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
interface CreateContract {

    interface CreatePresenter : BasePresenter {
        fun onAttach(createView: CreateView)
        fun onBackAction()
        fun onSaveAction(note: Note)
    }

    interface CreateView : BaseView {
        fun saveAction()
    }

}

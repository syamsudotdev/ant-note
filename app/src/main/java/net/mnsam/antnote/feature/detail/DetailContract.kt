package net.mnsam.antnote.feature.detail

import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
interface DetailContract {

    interface Presenter : BasePresenter {
        fun onAttach(view: View)
        fun onEditAction()
        fun onLongClickContent(): Boolean
        fun onSave()
    }

    interface View : BaseView {
        fun onLongTap()
    }
}
package net.mnsam.antnote.base.implementation

import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView

/**
 * Created by Mochamad Noor Syamsu on 1/29/18.
 */
open class BasePresenterImpl<V : BaseView> : BasePresenter<V> {

    var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }
}
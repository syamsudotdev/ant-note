package net.mnsam.antnote.base

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface BasePresenter<V : BaseView> {
    fun onAttach(view: V)
    fun onDetach()
}
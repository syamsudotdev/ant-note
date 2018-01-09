package net.mnsam.antnote.main.presentation

import net.mnsam.antnote.base.BasePresenter

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface MainPresenter : BasePresenter {
    fun onListItemClick(position: Int)
}
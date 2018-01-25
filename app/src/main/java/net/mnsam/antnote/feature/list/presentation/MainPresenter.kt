package net.mnsam.antnote.feature.list.presentation

import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface MainPresenter : BasePresenter {
    fun onAttach(view: MainView)
    fun onListItemClick(position: Int)
    fun onLoadedData(list: MutableList<Note>)
    fun onErrorLoad(message: String)
    fun onFabClick()
}
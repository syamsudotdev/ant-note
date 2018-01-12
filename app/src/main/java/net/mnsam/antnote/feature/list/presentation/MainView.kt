package net.mnsam.antnote.feature.list.presentation

import io.reactivex.Observable
import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
interface MainView : BaseView {
    fun showList(list: MutableList<Note>)
    fun showEmptyPage()
    fun observeData(observable: Observable<MutableList<Note>>)
    fun navigateToDetail(id: Long)
    fun navigateToCreate()
}
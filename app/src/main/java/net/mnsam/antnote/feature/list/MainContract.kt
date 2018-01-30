package net.mnsam.antnote.feature.list

import io.reactivex.Observable
import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
interface MainContract {

    interface Presenter : BasePresenter<View> {
        fun onErrorLoad(message: String)
        fun onFabClick()
        fun onListItemClick(position: Int)
        fun onLoadedData(list: MutableList<Note>)
        fun onResume()
    }

    interface View : BaseView {
        fun navigateToDetail(id: Long)
        fun navigateToCreate()
        fun observeData(observable: Observable<MutableList<Note>>)
        fun showList(list: MutableList<Note>)
        fun showEmptyPage()
    }

}

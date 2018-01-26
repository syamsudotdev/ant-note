package net.mnsam.antnote.feature.list

import io.reactivex.Observable
import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.base.BaseView
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/26/18.
 */
interface MainContract {

    interface MainPresenter : BasePresenter {
        fun onAttach(view: MainView)
        fun onListItemClick(position: Int)
        fun onLoadedData(list: MutableList<Note>)
        fun onErrorLoad(message: String)
        fun onFabClick()
    }

    interface MainView : BaseView {
        fun showList(list: MutableList<Note>)
        fun showEmptyPage()
        fun observeData(observable: Observable<MutableList<Note>>)
        fun navigateToDetail(id: Long)
        fun navigateToCreate()
    }

}

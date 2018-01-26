package net.mnsam.antnote.feature.create.presentation

import net.mnsam.antnote.base.BasePresenter
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 1/10/18.
 */
interface CreatePresenter : BasePresenter {
    fun onAttach(createView: CreateView)
    fun onBackAction()
    fun onSaveAction(note: Note)
}
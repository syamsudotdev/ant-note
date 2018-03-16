package net.mnsam.antnote.feature.list

import net.mnsam.antnote.base.implementation.BasePresenterImpl
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.list.recycler.NoteAdapter

/**
 * Created by Mochamad Noor Syamsu on 1/5/18.
 */
class MainPresenter(
        private var list: MutableList<Note> = mutableListOf(),
        private val noteRepository: NoteRepository) :
        BasePresenterImpl<MainContract.View>(), MainContract.Presenter {

    lateinit var adapter: MainContract.View.NoteAdapter
    private var archivedNote: Note? = null
    private var archivedPosition = -1

    override fun attachAdapter(adapter: MainContract.View.NoteAdapter) {
        this.adapter = adapter
    }

    override fun getNoteCount(): Int = list.size

    override fun onArchiveNote(position: Int) {
        archivedNote = list[position]
        list.removeAt(position)
        archivedPosition = position
        noteRepository.negate(list[position].id!!)
        if (!list.isEmpty()) adapter.removeAtPosition(position) else view!!.showEmptyPage()
        view!!.showSnackbar()
    }

    override fun onBindNoteRowViewAtPosition(holder: MainContract.View.NoteRowView, position: Int) {
        holder.setTitle(list[position].title)
        holder.setContent(list[position].content)

        val adapterClickListener = object : NoteAdapter.AdapterClickListener {
            override fun onItemClick(position: Int) {
                view!!.navigateToDetail(list[position].id!!)
            }
        }

        holder.setClickListener(adapterClickListener)
    }

    override fun onErrorLoad(message: String) = view!!.toastMessage(message)

    override fun onFabClick() = view!!.navigateToCreate()

    override fun onLoadedData(list: MutableList<Note>) {
        if (!list.isEmpty()) {
            this.list = list
            adapter.itemRangeInserted(0, getNoteCount())
        } else {
            view!!.showEmptyPage()
        }
    }

    override fun onRestoreNote() {
        if (archivedPosition >= 0 && archivedNote != null) {
            noteRepository.negate(archivedNote!!.id!!)
            list.add(archivedPosition, archivedNote!!)
            adapter.addToPosition(archivedPosition)
        }
    }

    override fun onResume() = view!!.observeData(noteRepository.getObservableAllNotes())
}

package net.mnsam.antnote.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*
import net.mnsam.antnote.R
import net.mnsam.antnote.datastorage.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/26/17.
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var noteList = mutableListOf<Note>()
    var adapterClickListener: AdapterClickListener? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) {
            itemView.noteTitle.text = note.title
            itemView.content.text = note.content
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(noteList.get(position))

    override fun getItemCount(): Int = noteList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.
                from(parent.context).
                inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    fun addList(noteList: MutableList<Note>?) = this.noteList.addAll(noteList!!)
}
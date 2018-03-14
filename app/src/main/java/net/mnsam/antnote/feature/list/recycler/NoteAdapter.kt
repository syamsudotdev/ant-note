package net.mnsam.antnote.feature.list.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note

/**
 * Created by Mochamad Noor Syamsu on 12/26/17.
 */
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    private var noteList = mutableListOf<Note>()
    var adapterClickListener: AdapterClickListener? = null

    interface AdapterClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note, listener: AdapterClickListener) {
            itemView.noteTitleEdit.text = note.title
            itemView.content.text = note.content
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteList[position], adapterClickListener!!)
    }

    override fun getItemCount(): Int = noteList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.
                from(parent.context).
                inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    fun addToPosition(position: Int, note: Note) {
        this.noteList.add(position, note)
        notifyItemInserted(position)
    }

    fun changeDataSet(noteList: MutableList<Note>) {
        this.noteList = noteList
        notifyDataSetChanged()
    }
}

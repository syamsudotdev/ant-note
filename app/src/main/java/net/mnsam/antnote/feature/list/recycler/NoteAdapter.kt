package net.mnsam.antnote.feature.list.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_note.view.*
import net.mnsam.antnote.R
import net.mnsam.antnote.feature.list.MainContract

/**
 * Created by Mochamad Noor Syamsu on 12/26/17.
 */
class NoteAdapter(val presenter: MainContract.Presenter) : RecyclerView.Adapter<NoteAdapter.ViewHolder>(), MainContract.View.NoteAdapter {

    interface AdapterClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), MainContract.View.NoteRowView {
        override fun setTitle(title: String) {
            itemView.noteTitleEdit.text = title
        }

        override fun setContent(content: String) {
            itemView.content.text = content
        }

        override fun setClickListener(listener: AdapterClickListener) {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.onBindNoteRowViewAtPosition(holder, position)
    }

    override fun getItemCount(): Int = presenter.getNoteCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.
                from(parent.context).
                inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun addToPosition(position: Int) = notifyItemInserted(position)

    override fun itemRangeChanged(positionStart: Int, count: Int) {
        notifyItemRangeChanged(positionStart, count)
    }

    override fun removeAtPosition(position: Int) = notifyItemRemoved(position)
}

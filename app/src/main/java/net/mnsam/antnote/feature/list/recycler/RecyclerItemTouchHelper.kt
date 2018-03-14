package net.mnsam.antnote.feature.list.recycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import net.mnsam.antnote.R

/**
 * Created by Mochamad Noor Syamsu on 3/9/18.
 */
class RecyclerItemTouchHelper(private val context: Context, private val listener: SwipeListener) :
        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

    interface SwipeListener {
        fun delete(position: Int)
    }

    override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
    ) {
        val itemView = viewHolder.itemView
        val itemHeight = itemView.height
        val background = ColorDrawable()

        background.color = Color.DKGRAY
        background.setBounds(itemView.left, itemView.top, itemView.right, itemView.bottom)
        background.draw(c)

        val archiveIcon = ContextCompat.getDrawable(context, R.drawable.ic_archive_white_24dp)
        val intrinsicWidth = archiveIcon.intrinsicWidth
        val intrinsicHeight = archiveIcon.intrinsicHeight
        val archiveIconTop = itemView.top + (itemHeight - intrinsicHeight) / 2
        val archiveIconMargin = (itemHeight - intrinsicHeight) / 2
        val archiveIconLeft = itemView.left + archiveIconMargin
        val archiveIconRight = itemView.left + archiveIconMargin - intrinsicWidth
        val archiveIconBottom = archiveIconTop + intrinsicHeight

        archiveIcon.setBounds(archiveIconLeft, archiveIconTop, archiveIconRight, archiveIconBottom)
        archiveIcon.draw(c)

        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun onMove(
            recyclerView: RecyclerView?,
            viewHolder: RecyclerView.ViewHolder?,
            target: RecyclerView.ViewHolder?
    ): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        this.listener.delete(viewHolder!!.adapterPosition)
    }
}
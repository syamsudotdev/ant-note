package net.mnsam.antnote.feature.detail.fragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog

/**
 * Created by Mochamad Noor Syamsu on 2/22/18.
 */
class DiscardDialogFragment : DialogFragment() {

    interface DiscardListener {
        fun discard()
    }

    var discardListener: DiscardListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val builder = AlertDialog.Builder(activity)
        builder.setMessage("Discard changes ?")
                .setPositiveButton(
                        "DISCARD",
                        { _: DialogInterface, _: Int -> discardListener!!.discard() }
                )
                .setNegativeButton(
                        "CANCEL",
                        { _: DialogInterface, _: Int -> dismiss() }
                )
        return builder.create()
    }
}
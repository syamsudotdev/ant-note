package net.mnsam.antnote.feature.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_detail_note.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.util.Constants
import net.mnsam.antnote.util.goGone
import net.mnsam.antnote.util.goVisible
import javax.inject.Inject

class DetailNoteActivity : AppCompatActivity(), DetailContract.View {

    @Inject
    lateinit var detailPresenter: DetailContract.Presenter
    @Inject
    lateinit var constants: Constants
    private var idNote: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        detailPresenter.onAttach(this)
        idNote = intent.extras.get(constants.idNoteKey) as Long
        noteContentView.setOnLongClickListener { detailPresenter.onLongClickContent() }
    }

    override fun onResume() {
        detailPresenter.onResume(idNote)
        super.onResume()
    }

    override fun toastMessage(message: String) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun observeDetail(observable: Observable<Note>) {
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<Note>() {
                    override fun onComplete() {}

                    override fun onNext(t: Note) {
                        detailPresenter.onDetailLoaded(t)
                    }

                    override fun onError(e: Throwable) {
                        detailPresenter.onErrorLoad("Failed to load data")
                    }
                })
    }

    override fun showDetail(note: Note) {
        noteTitleView.text = note.title
        noteContentView.text = note.content
        noteTitleEdit.setText(note.title, TextView.BufferType.EDITABLE)
        noteContentEdit.setText(note.content, TextView.BufferType.EDITABLE)
    }

    override fun editMode() {
        noteTitleView.goGone()
        noteContentView.goGone()
        linearGrey.goGone()
        tilNoteTitleEdit.goVisible()
        noteContentEdit.goVisible()
    }
}

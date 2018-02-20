package net.mnsam.antnote.feature.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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
    private var editMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        detailPresenter.onAttach(this)
        idNote = intent.extras.get(constants.idNoteKey) as Long
        noteTitleView.setOnLongClickListener { detailPresenter.onEditMode() }
        noteContentView.setOnLongClickListener { detailPresenter.onEditMode() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (editMode) {
            menuInflater.inflate(R.menu.save_menu, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == R.id.saveMenu -> {
                save()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        detailPresenter.onResume(idNote)
        super.onResume()
    }

    override fun editMode() {
        editMode = true
        invalidateOptionsMenu()
        noteTitleView.goGone()
        noteContentView.goGone()
        linearGrey.goGone()
        tilNoteTitleEdit.goVisible()
        noteContentEdit.goVisible()
    }

    override fun observeDetail(observable: Observable<Note>) {
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<Note>() {
                    override fun onComplete() {}

                    override fun onError(e: Throwable) {
                        detailPresenter.onErrorLoad("Failed to load data")
                    }

                    override fun onNext(t: Note) {
                        detailPresenter.onDetailLoaded(t)
                    }
                })
    }

    override fun save() {
        val title = noteTitleEdit.text.toString()
        val content = noteContentEdit.text.toString()
        if (title.isNotEmpty() && content.isNotEmpty()) {
            val note = Note(idNote, title, content)
            detailPresenter.onSave(note)
        }
    }

    override fun showDetail(note: Note) {
        noteTitleView.text = note.title
        noteContentView.text = note.content
        noteTitleEdit.setText(note.title, TextView.BufferType.EDITABLE)
        noteContentEdit.setText(note.content, TextView.BufferType.EDITABLE)
    }

    override fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

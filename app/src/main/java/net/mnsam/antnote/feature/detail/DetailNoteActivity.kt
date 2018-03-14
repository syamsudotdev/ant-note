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
import net.mnsam.antnote.feature.detail.fragment.DiscardDialogFragment
import net.mnsam.antnote.util.InputMode
import net.mnsam.antnote.util.IntentKeys
import net.mnsam.antnote.util.goGone
import net.mnsam.antnote.util.goVisible
import javax.inject.Inject

class DetailNoteActivity : AppCompatActivity(), DetailContract.View {

    private var idNote: Long = 0
    private var inputMode: Int = 0
    @Inject
    lateinit var presenter: DetailContract.Presenter
    private var initialTitle = ""
    private var initialContent = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        presenter.onAttach(this)
        idNote = intent.extras.get(IntentKeys.ID_NOTE_KEY) as Long
        inputMode = intent.extras.getInt(IntentKeys.INPUT_MODE)
        noteTitleView.setOnLongClickListener { presenter.onEditMode(inputMode) }
        noteContentView.setOnLongClickListener { presenter.onEditMode(inputMode) }

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (inputMode != InputMode.VIEW) {
            menuInflater.inflate(R.menu.save_menu, menu)
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_close_white_24dp)
        } else {
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (presenter.onDiscard()) finish()
                true
            }
            R.id.saveMenu -> {
                presenter.onSaveClick()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        if (presenter.onDiscard()) super.onBackPressed()
    }

    override fun onResume() {
        presenter.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun editMode(inputMode: Int) {
        this.inputMode = inputMode
        invalidateOptionsMenu()
        noteTitleView.goGone()
        noteContentView.goGone()
        linearGrey.goGone()
        tilNoteTitleEdit.goVisible()
        noteContentEdit.goVisible()
        initialTitle = noteTitleEdit.text.toString()
        initialContent = noteContentEdit.text.toString()
    }

    override fun getIdNote(): Long = this.idNote

    override fun getInputMode(): Int = this.inputMode

    override fun isInputStateNotEquals(): Boolean {
        return (initialTitle != noteTitleEdit.text.toString() ||
                initialContent != noteContentEdit.text.toString())
    }

    override fun observeDetail(observable: Observable<Note>) {
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<Note>() {
                    override fun onComplete() {
                    }

                    override fun onError(e: Throwable) {
                        presenter.onErrorLoad("Failed to load data")
                    }

                    override fun onNext(t: Note) {
                        presenter.onDetailLoaded(t)
                    }
                })
    }

    override fun promptDiscard() {
        val discardDialogFragment = DiscardDialogFragment()
        discardDialogFragment.discardListener = object : DiscardDialogFragment.DiscardListener {
            override fun discard() = finish()
        }
        discardDialogFragment.show(supportFragmentManager, "DiscardDialogFragment")
    }

    override fun save() {
        val title = noteTitleEdit.text.toString()
        val content = noteContentEdit.text.toString()
        presenter.onSave(title, content)
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

    override fun viewMode() {
        inputMode = InputMode.VIEW
        invalidateOptionsMenu()
        noteTitleView.goVisible()
        noteContentView.goVisible()
        linearGrey.goVisible()
        tilNoteTitleEdit.goGone()
        noteContentEdit.goGone()
    }
}

package net.mnsam.antnote.feature.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_note.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.create.presentation.CreatePresenter
import net.mnsam.antnote.feature.create.presentation.CreateView
import net.mnsam.antnote.feature.create.presentation.implementation.CreatePresenterImpl

class CreateNoteActivity : AppCompatActivity(), CreateView {

    private val createPresenter: CreatePresenter =
            CreatePresenterImpl(this, NoteRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.create_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.saveActionMenu) {
            saveAction()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigateUp(): Boolean {
        createPresenter.onBackAction()
        finish()
        return super.onNavigateUp()
    }

    override fun onBackPressed() {
        createPresenter.onBackAction()
        finish()
        super.onBackPressed()
    }

    override fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun backAction() {

    }

    override fun saveAction() {
        val note = Note(null, noteTitle.text.toString(), noteContent.text.toString())
        createPresenter.onSaveAction(note)
    }
}

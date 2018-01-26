package net.mnsam.antnote.feature.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_create_note.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.feature.create.presentation.CreatePresenter
import net.mnsam.antnote.feature.create.presentation.CreateView
import javax.inject.Inject

class CreateNoteActivity : AppCompatActivity(), CreateView {
    @Inject
    lateinit var createPresenter: CreatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        createPresenter.onAttach(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == android.R.id.home -> {
                createPresenter.onBackAction()
                finish()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        createPresenter.onBackAction()
        super.onBackPressed()
    }

    override fun toastMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun saveAction() {
        val note = Note(null, noteTitle.text.toString(), noteContent.text.toString())
        createPresenter.onSaveAction(note)
        finish()
    }
}

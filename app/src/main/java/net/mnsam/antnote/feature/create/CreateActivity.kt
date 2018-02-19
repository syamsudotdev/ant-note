package net.mnsam.antnote.feature.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_create_note.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import javax.inject.Inject

class CreateActivity : AppCompatActivity(), CreateContract.View {
    @Inject
    lateinit var presenter: CreateContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        presenter.onAttach(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == android.R.id.home -> {
                finish()
                super.onOptionsItemSelected(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun toastMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun save() {
        val title = noteTitleEdit.text.toString()
        val content = noteContentEdit.text.toString()
        if (title.isNotEmpty() && content.isNotEmpty()) {
            val note = Note(null, title, content)
            presenter.onSave(note)
        }
        finish()
    }
}

package net.mnsam.antnote.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.datastorage.local.entity.Note
import net.mnsam.antnote.main.adapter.AdapterClickListener
import net.mnsam.antnote.main.adapter.NoteAdapter
import net.mnsam.antnote.main.presentation.MainView
import net.mnsam.antnote.main.presentation.implementation.MainPresenterImpl
import net.mnsam.antnote.main.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), MainView {

    private val noteList = mutableListOf<Note>()
    val mainPresenter = MainPresenterImpl(noteList)
    val noteViewModel: NoteViewModel =
            ViewModelProviders.of(this).get(NoteViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        showList(noteList)
    }

    override fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showList(list: MutableList<Note>) {
        val noteAdapter = NoteAdapter()
        noteViewModel.getAllNotes().observe(this,
                Observer<MutableList<Note>> { noteList -> noteAdapter.addList(noteList) })
        setClickListener(noteAdapter)
        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)
    }

    override fun showDetail(note: Note) {}

    override fun setClickListener(noteAdapter: NoteAdapter) {
        noteAdapter.adapterClickListener = object : AdapterClickListener {
            override fun onItemClick(view: View, position: Int) {

            }
        }
    }
}

package net.mnsam.antnote.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.main.adapter.NoteAdapter
import net.mnsam.antnote.main.presentation.MainPresenter
import net.mnsam.antnote.main.presentation.MainView
import net.mnsam.antnote.main.presentation.implementation.MainPresenterImpl

class MainActivity : AppCompatActivity(), MainView {

    private val noteList = mutableListOf<Note>()
    private val noteRepository = object : NoteRepository()
    private val mainPresenter: MainPresenter = MainPresenterImpl(this, noteList, noteRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        mainPresenter.onCreate()
    }

    override fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun showList(list: MutableList<Note>) {
        val noteAdapter = NoteAdapter()
        setClickListener(noteAdapter)
        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)
    }

    override fun showEmptyList() {
        //TODO: show "Empty"
    }

    override fun navigateToDetail(id: Long) {
        val intent = Intent()//current act, next act
        intent.putExtra("idNote", id)
        startActivity(intent)
    }

    override fun setClickListener(noteAdapter: NoteAdapter) {
        noteAdapter.adapterClickListener = object : NoteAdapter.AdapterClickListener {
            override fun onItemClick(view: View, position: Int) {
                mainPresenter.onListItemClick(position)
            }
        }
    }
}

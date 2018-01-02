package net.mnsam.antnote.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.main.database.entity.Note
import net.mnsam.antnote.main.list.NoteAdapter
import net.mnsam.antnote.main.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        val noteViewModel: NoteViewModel =
                ViewModelProviders.of(this).get(NoteViewModel::class.java)

        val noteList = mutableListOf<Note>()

        val noteAdapter = NoteAdapter(noteList)
        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)

        noteViewModel.getAllNotes().observe(this,
                Observer<MutableList<Note>> { noteList -> noteAdapter.addList(noteList) })

        var note = Note(null, "eat", "eat straw cake on the morning with tea lemon is so nice. how would I not reject ?")
        noteViewModel.insert(note)
        noteList.add(note)
        note = Note(null, "run", "using red shoes")
        noteViewModel.insert(note)
        noteList.add(note)
    }
}

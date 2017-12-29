package net.mnsam.antnote.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.main.database.entity.Note
import net.mnsam.antnote.main.database.repository.NoteRepository
import net.mnsam.antnote.main.list.NoteAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteList = mutableListOf<Note>()
        val noteRepository = NoteRepository(this.application)
        var note = Note(null, "eat", "eat straw cake on the morning with tea lemon is so nice. how would I not reject ?")
        noteList.add(note)
        noteRepository.insert(note)
        note = Note(null, "run", "using red shoes")
        noteList.add(note)
        noteRepository.insert(note)

        listItem.adapter = NoteAdapter(noteList)
        listItem.layoutManager = LinearLayoutManager(this)
    }
}

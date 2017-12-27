package net.mnsam.antnote.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.main.list.NoteAdapter
import net.mnsam.antnote.model.Note

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteList = mutableListOf<Note>()
        var note : Note
        note = Note(null, "eat", "eat straw cake on the morning with tea lemon is so nice. how would I not reject ?")
        noteList.add(note)
        note = Note(null, "run", "using red shoes")
        noteList.add(note)

        listItem.adapter = NoteAdapter(noteList)
        listItem.layoutManager = LinearLayoutManager(this)
    }
}

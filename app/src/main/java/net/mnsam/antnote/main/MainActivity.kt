package net.mnsam.antnote.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.main.database.entity.Note
import net.mnsam.antnote.main.database.repository.NoteRepository
import net.mnsam.antnote.main.list.NoteAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        val noteList = mutableListOf<Note>()

        val noteAdapter = NoteAdapter(noteList)
        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)

        val noteRepository = NoteRepository()
        var note = Note(null, "eat", "eat straw cake on the morning with tea lemon is so nice. how would I not reject ?")
        noteRepository.insert(note)
        noteList.add(note)
        note = Note(null, "run", "using red shoes")
        noteRepository.insert(note)
        noteList.add(note)

        listItem.adapter.notifyDataSetChanged()
    }
}

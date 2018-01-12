package net.mnsam.antnote.feature.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.facebook.stetho.Stetho
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.data.repository.NoteRepository
import net.mnsam.antnote.feature.create.CreateNoteActivity
import net.mnsam.antnote.feature.detail.DetailNoteActivity
import net.mnsam.antnote.feature.list.adapter.NoteAdapter
import net.mnsam.antnote.feature.list.presentation.MainPresenter
import net.mnsam.antnote.feature.list.presentation.MainView
import net.mnsam.antnote.feature.list.presentation.implementation.MainPresenterImpl

class MainActivity : AppCompatActivity(), MainView {

    private val noteAdapter = NoteAdapter()
    private val mainPresenter: MainPresenter =
            MainPresenterImpl(this, mutableListOf(), NoteRepository())
    val ID_NOTE_KEY = "id_note_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)
        noteAdapter.adapterClickListener = object : NoteAdapter.AdapterClickListener {
            override fun onItemClick(view: View, position: Int) {
                mainPresenter.onListItemClick(position)
            }
        }

        mainPresenter.onCreate()
        fabNoteAdd.setOnClickListener {
            View.OnClickListener {
                Log.d("MainMain", "FAB clicked")
                mainPresenter.onFabClick()
            }
        }
    }

    override fun toastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showList(list: MutableList<Note>) {
        if (listItem.visibility == View.GONE || emptyList.visibility == View.VISIBLE) {
            listItem.visibility = View.VISIBLE
            emptyList.visibility = View.GONE
        }
        noteAdapter.changeDataSet(list)
    }

    override fun showEmptyPage() {
        if (listItem.visibility == View.VISIBLE || emptyList.visibility == View.GONE) {
            emptyList.visibility = View.VISIBLE
            listItem.visibility = View.GONE
        }
    }

    override fun observeData(observable: Observable<MutableList<Note>>) {
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableObserver<MutableList<Note>>() {
                    override fun onNext(t: MutableList<Note>) {
                        mainPresenter.onLoadedData(t)
                    }

                    override fun onError(e: Throwable) {
                        mainPresenter.onErrorLoad("Failed to load notes")
                    }

                    override fun onComplete() {}
                })
    }

    override fun navigateToDetail(id: Long) {
        val intent = Intent(this, DetailNoteActivity::class.java)
        intent.putExtra(ID_NOTE_KEY, id)
        startActivity(intent)
    }

    override fun navigateToCreate() {
        val intent = Intent(this, CreateNoteActivity::class.java)
        startActivity(intent)
    }
}

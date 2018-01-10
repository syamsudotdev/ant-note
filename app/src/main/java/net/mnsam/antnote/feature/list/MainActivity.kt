package net.mnsam.antnote.feature.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
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

    private val noteList = mutableListOf<Note>()
    private val noteAdapter = NoteAdapter()
    private val noteRepository = NoteRepository()
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
        if (listItem.visibility == View.GONE) {
            listItem.visibility = View.VISIBLE
            emptyList.visibility = View.GONE
        }
        noteAdapter.changeDataSet(list)
    }

    override fun showEmptyPage() {
        if (emptyList.visibility == View.GONE) {
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
        startActivity(intent)
    }

    override fun navigateToCreate() {
        val intent = Intent(this, CreateNoteActivity::class.java)
        startActivity(intent)
    }

    override fun setupRv() {
        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)
        noteAdapter.adapterClickListener = object : NoteAdapter.AdapterClickListener {
            override fun onItemClick(view: View, position: Int) {
                mainPresenter.onListItemClick(position)
            }
        }
    }

    override fun setFabListener() {
        fabNoteAdd.setOnClickListener {
            View.OnClickListener {
                mainPresenter.onFabClick()
            }
        }
    }
}

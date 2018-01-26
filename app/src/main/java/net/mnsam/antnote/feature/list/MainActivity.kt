package net.mnsam.antnote.feature.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import kotlinx.android.synthetic.main.activity_main.*
import net.mnsam.antnote.R
import net.mnsam.antnote.data.local.entity.Note
import net.mnsam.antnote.feature.create.CreateNoteActivity
import net.mnsam.antnote.feature.detail.DetailNoteActivity
import net.mnsam.antnote.feature.list.adapter.NoteAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.MainView {

    val idNoteKey = "id_note_key"
    private val noteAdapter = NoteAdapter()
    @Inject
    lateinit var mainPresenter: MainContract.MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mainPresenter.onAttach(this)
        Stetho.initializeWithDefaults(this)
        setContentView(R.layout.activity_main)

        listItem.adapter = noteAdapter
        listItem.layoutManager = LinearLayoutManager(this)
        noteAdapter.adapterClickListener = object : NoteAdapter.AdapterClickListener {
            override fun onItemClick(position: Int) {
                mainPresenter.onListItemClick(position)
            }
        }

        fabNoteAdd.setOnClickListener { mainPresenter.onFabClick() }
    }

    override fun onResume() {
        mainPresenter.onResume()
        super.onResume()
    }

    override fun toastMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

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
        intent.putExtra(idNoteKey, id)
        startActivity(intent)
    }

    override fun navigateToCreate() {
        val intent = Intent(this, CreateNoteActivity::class.java)
        startActivity(intent)
    }
}

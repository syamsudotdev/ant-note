package net.mnsam.antnote.feature.detail

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail_note.*
import net.mnsam.antnote.R
import javax.inject.Inject

class DetailNoteActivity : AppCompatActivity(), DetailContract.View {

    @Inject
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        AndroidInjection.inject(this)
        noteContentView.setOnLongClickListener { detailPresenter.onLongClickContent() }
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun toastMessage(message: String) {

    }

    override fun onLongTap() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)
    }
}

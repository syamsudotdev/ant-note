package net.mnsam.antnote.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Mochamad Noor Syamsu on 12/26/17.
 */
@Entity(tableName = "notes")
        data class Note(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String,
        @Ignore var shortContent: String,
        var content: String
)
package net.mnsam.antnote.main.database.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Mochamad Noor Syamsu on 12/26/17.
 */
@Entity(tableName = "notes")
        data class Note(
        @PrimaryKey(autoGenerate = true) var id: Long? = null,
        var title: String,
        var content: String
)
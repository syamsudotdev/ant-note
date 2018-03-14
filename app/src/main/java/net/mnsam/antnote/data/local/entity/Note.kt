package net.mnsam.antnote.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Mochamad Noor Syamsu on 12/26/17.
 */
@Entity(tableName = "notes")
data class Note(
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
        @ColumnInfo(name = "title") var title: String,
        @ColumnInfo(name = "content") var content: String,
        @ColumnInfo(name = "is_archived") var isArchived: Boolean = false
)
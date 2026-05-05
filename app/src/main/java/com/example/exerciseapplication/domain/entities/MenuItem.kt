package com.example.exerciseapplication.domain.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "table_menu")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var name: String,
    var price: Long,
    var type: String,
    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean = false,
    var description: String? = null
) : Parcelable  // cho phép truyền dữ liệu

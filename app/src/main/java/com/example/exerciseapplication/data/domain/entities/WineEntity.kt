package com.example.exerciseapplication.data.domain.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "collection_wine")
@Parcelize
data class WineEntity(
    @PrimaryKey val id: Int,
    val winery: String,
    val wine: String,
    val image: String,
    val location: String,
    @Embedded val rating: Rating, // @Embedded để nhúng object vào room
    val isCollected: Boolean = false
): Parcelable
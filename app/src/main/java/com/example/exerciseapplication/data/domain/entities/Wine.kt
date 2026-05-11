package com.example.exerciseapplication.data.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Wine(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val winery: String,
    val wine: String,
    val image: String,
    val location: String,
    val ratting: Ratting

): Parcelable {
}
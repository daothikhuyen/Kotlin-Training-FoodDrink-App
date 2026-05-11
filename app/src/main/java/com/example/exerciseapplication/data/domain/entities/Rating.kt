package com.example.exerciseapplication.data.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Rating")
data class Rating(
    val average : Double,
    val reviews : String
): Parcelable {
}
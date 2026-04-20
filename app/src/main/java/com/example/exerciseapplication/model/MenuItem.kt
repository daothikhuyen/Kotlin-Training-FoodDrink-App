package com.example.exerciseapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MenuItem(
    val id: Int,
    var name: String,
    var price: Int
    ): Parcelable // cho phép truyền dữ liệu

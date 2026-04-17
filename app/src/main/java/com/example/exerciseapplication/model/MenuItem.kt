package com.example.exerciseapplication.model

import java.io.Serializable

data class MenuItem(
    val id: Int,
    var name: String,
    var price: Int
): Serializable // cho phép truyền dữ liệu

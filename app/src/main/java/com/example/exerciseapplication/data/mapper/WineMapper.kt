package com.example.exerciseapplication.data.mapper

import com.example.exerciseapplication.data.domain.entities.Wine
import com.example.exerciseapplication.data.domain.entities.WineEntity

fun Wine.toEntity(): WineEntity {
    return WineEntity(
        id = id,
        winery = winery,
        wine = wine,
        image = image,
        location = location
    )
}
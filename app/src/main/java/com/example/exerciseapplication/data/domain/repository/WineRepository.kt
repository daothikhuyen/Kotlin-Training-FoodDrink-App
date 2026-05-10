package com.example.exerciseapplication.data.domain.repository

import com.example.exerciseapplication.data.domain.entities.Wine

interface WineRepository {
    suspend fun getWines(): List<Wine>
}
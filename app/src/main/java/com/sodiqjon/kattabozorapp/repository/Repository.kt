package com.sodiqjon.kattabozorapp.repository

import com.sodiqjon.kattabozorapp.common.Resource
import com.sodiqjon.kattabozorapp.response.Offer

interface Repository {
    suspend fun getAdviceCategories(): Resource<List<Offer>>
}
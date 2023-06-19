package com.sodiqjon.kattabozorapp.api

import com.sodiqjon.kattabozorapp.response.Offer
import com.sodiqjon.kattabozorapp.response.OfferResponse
import retrofit2.http.GET

interface ServiceApi {
    @GET("offers")
    suspend fun getOffers(): OfferResponse<List<Offer>?>
}
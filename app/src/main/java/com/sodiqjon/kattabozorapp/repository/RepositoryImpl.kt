package com.sodiqjon.kattabozorapp.repository

import com.sodiqjon.kattabozorapp.api.ServiceApi
import com.sodiqjon.kattabozorapp.common.Resource
import com.sodiqjon.kattabozorapp.response.Offer
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val retrofitApi: ServiceApi) : Repository {
    override suspend fun getAdviceCategories(): Resource<List<Offer>> {
        return try {
            val result = retrofitApi.getOffers()
            if (result.offers != null) {
                Resource.Success(result.offers)
            } else {
                Resource.Error(data = null, "Error data")
            }
        } catch (e: Exception) {
            Resource.Error(null, e.message)
        }
    }

}
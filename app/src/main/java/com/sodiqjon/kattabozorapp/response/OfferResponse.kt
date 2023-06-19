package com.sodiqjon.kattabozorapp.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class OfferResponse<T>(
    val offers: T
)

@Parcelize
data class Offer(
    val attributes: List<Attribute>,
    val brand: String,
    val category: String,
    val id: Int,
    val image: Image,
    val merchant: String,
    val name: String
) : Parcelable

@Parcelize
data class Attribute(
    val name: String,
    val value: String
) : Parcelable

@Parcelize
data class Image(
    val height: String,
    val url: String,
    val width: String
) : Parcelable

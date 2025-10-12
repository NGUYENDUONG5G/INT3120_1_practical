package com.example.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    @SerialName("kind") val kind: String? = null,
    @SerialName("totalItems") val totalItems: Int = 0,
    @SerialName("items") val items: List<BookItem>? = emptyList()
)

@Serializable
data class BookItem(
    @SerialName("id") val id: String = "",
    @SerialName("volumeInfo") val volumeInfo: VolumeInfo = VolumeInfo()
)

@Serializable
data class VolumeInfo(
    @SerialName("title") val title: String = "",
    @SerialName("authors") val authors: List<String>? = null,
    @SerialName("imageLinks") val imageLinks: ImageLinks? = null
)

@Serializable
data class BookDetail(
    @SerialName("id") val id: String = "",
    @SerialName("volumeInfo") val volumeInfo: VolumeInfoDetail = VolumeInfoDetail()
)

@Serializable
data class VolumeInfoDetail(
    @SerialName("title") val title: String = "",
    @SerialName("authors") val authors: List<String>? = null,
    @SerialName("publisher") val publisher: String? = null,
    @SerialName("publishedDate") val publishedDate: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("categories") val categories: List<String>? = null,
    @SerialName("imageLinks") val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("smallThumbnail") val smallThumbnail: String? = null
)

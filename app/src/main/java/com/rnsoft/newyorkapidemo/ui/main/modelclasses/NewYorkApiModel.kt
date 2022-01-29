package com.rnsoft.newyorkapidemo

import com.google.gson.annotations.SerializedName

data class NewYorkApiModel(
    val copyright: String,
    val num_results: Int,
    @SerializedName("results") val results:ArrayList<ResponseResult>?=null,
    //val results: ArrayList<Result<Any?>>?=null,
    val status: String
)

data class ResponseResult(
    //val `abstract`: String,
    @SerializedName("abstract") val abstractString :  String?=null,

    val adx_keywords: String,
    val asset_id: Long,
    val byline: String,
    val column: Any?,
    val des_facet: ArrayList<String>?=null,
    val eta_id: Int,
    val geo_facet: ArrayList<String>?=null,
    val id: Long,
    val media: ArrayList<Media>?=null,
    val nytdsection: String,
    val org_facet: ArrayList<String>?=null,
    val per_facet: ArrayList<String>?=null,
    val published_date: String,
    val section: String,
    val source: String,
    val subsection: String,
    val title: String,
    val type: String,
    val updated: String,
    val uri: String,
    val url: String
)

data class Media(
    val approved_for_syndication: Int,
    val caption: String,
    val copyright: String,
    //val `media-metadata`: ArrayList<MediaMetadata>?=null,
    @SerializedName("media-metadata") val meteDataList :  ArrayList<MediaMetadata>?=null,
    val subtype: String,
    val type: String
)

data class MediaMetadata(
    val format: String,
    val height: Int,
    val url: String,
    val width: Int
)
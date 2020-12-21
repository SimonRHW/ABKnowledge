package com.simon.news.model.bean

data class JHData<T>(
    val `data`: List<T>,
    val stat: String
)

data class News(
    val author_name: String,
    val category: String,
    val date: String,
    val thumbnail_pic_s: String,
    val thumbnail_pic_s02: String,
    val thumbnail_pic_s03: String,
    val title: String,
    val uniquekey: String,
    val url: String
)
package com.todaysquare.retrofitfunctiontestbed.data.model

data class SeoulPublicLibraryInfo(
    val RESULT: RESULT,
    val list_total_count: Int,
    val row: List<Row>
)
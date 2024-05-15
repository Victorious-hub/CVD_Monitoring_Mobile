package com.example.cvd_monitoring.common


data class TextFieldState(
    var text: String = "",
    val error: String? = null
)

data class MapFieldState(
    var text: Map<String, List<String>>? = null,
    val error: String? = null
)

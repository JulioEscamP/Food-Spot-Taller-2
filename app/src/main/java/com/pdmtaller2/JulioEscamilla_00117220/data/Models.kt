package com.pdmtaller2.JulioEscamilla_00117220.data

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int
)

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imageResId: Int,
    val categories: List<String>,
    val menu: List<Dish>
)
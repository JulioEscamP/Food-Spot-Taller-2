package com.pdmtaller2.JulioEscamilla_00117220.navigation


import androidx.navigation.NavType
import androidx.navigation.navArgument


object Routes {
    const val RESTAURANTS = "restaurants"
    const val MENU_ROUTE = "menu"
    const val SEARCH = "search"
    const val ORDERS = "orders"

    const val MENU_ARG_ID = "restaurantId"


    const val MENU = "$MENU_ROUTE/{$MENU_ARG_ID}"


    val menuArguments = listOf(
        navArgument(MENU_ARG_ID) { type = NavType.IntType }
    )


    fun menuWithArg(id: Int) = "$MENU_ROUTE/$id"
}


data class BottomNavItem(
    val label: String,
    val route: String
)


val bottomNavItems = listOf(
    BottomNavItem(
        label = "Listado",
        route = Routes.RESTAURANTS
    ),
    BottomNavItem(
        label = "Buscar",
        route = Routes.SEARCH
    ),
    BottomNavItem(
        label = "Mis ordenes",
        route = Routes.ORDERS
    )
)
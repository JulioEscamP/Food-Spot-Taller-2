package com.pdmtaller2.JulioEscamilla_00117220

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.pdmtaller2.JulioEscamilla_00117220.navigation.Routes
import com.pdmtaller2.JulioEscamilla_00117220.navigation.bottomNavItems
import com.pdmtaller2.JulioEscamilla_00117220.ui.
import com.pdmtaller2.JulioEscamilla_00117220.ui.features.restaurantlist.RestaurantListScreen
import com.pdmtaller2.JulioEscamilla_00117220.ui.features.restaurantmenu.RestaurantMenuScreen
import com.pdmtaller2.JulioEscamilla_00117220.ui.features.search.SearchScreen


@Composable
fun FoodAppScreen() {
    val navController = rememberNavController()


    Scaffold(
        bottomBar = {

            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.RESTAURANTS,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.RESTAURANTS) {
                RestaurantListScreen(
                    onRestaurantClick = { restaurantId ->
                        navController.navigate(Routes.menuWithArg(restaurantId))
                    }
                )
            }

            composable(
                route = Routes.MENU,
                arguments = Routes.menuArguments
            ) {
                RestaurantMenuScreen(
                    onNavigateBack = { navController.navigateUp() }
                )
            }

            composable(Routes.SEARCH) {
                SearchScreen()
            }

            composable(Routes.ORDERS) {
                OrdersScreen()
            }
        }
    }
}
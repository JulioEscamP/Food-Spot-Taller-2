package com.pdmtaller2.JulioEscamilla_00117220.ui.theme.screens.restaurantlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmtaller2.JulioEscamilla_00117220.data.Restaurant
import com.pdmtaller2.JulioEscamilla_00117220.data.Dish
import com.pdmtaller2.JulioEscamilla_00117220.R
import com.pdmtaller2.JulioEscamilla_00117220.ui.theme.FoodSpotByJulioEscamillaTheme

@Composable
fun RestaurantListContent(
    uiState: RestaurantListUi,
    onRestaurantClick: (restaurantId: Int) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.error != null -> {
                Text(
                    text = "Error: ${uiState.error}",
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(uiState.distinctCategories, key = { category -> category }) { category ->
                        val restaurantsInCategory = uiState.allRestaurants.filter { restaurant ->
                            val categoryList: List<String> = restaurant.categories
                            val currentCategory: String = category
                            categoryList.contains(currentCategory)
                        }

                        if (restaurantsInCategory.isNotEmpty()) {
                            CategoryHeader(title = category)
                            RestaurantCarousel(
                                restaurants = restaurantsInCategory,
                                onRestaurantClick = onRestaurantClick // Pasa la acción de clic
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun RestaurantListScreenPreview() {
    val sampleDish = Dish(id = 1, name = "Platillo Preview", description = "Desc", imageResId = R.drawable.ic_launcher_foreground)
    val sampleRestaurants = listOf(
        Restaurant(1, "Fulanitos", "Desc 1", R.drawable.ic_launcher_background, listOf("Preview", "Tipo A"), listOf(sampleDish)),
        Restaurant(2, "Menganitos", "Desc 2", R.drawable.ic_launcher_background, listOf("Preview", "Tipo B"), listOf(sampleDish)),
        Restaurant(3, "Sultanitos", "Desc 3", R.drawable.ic_launcher_background, listOf("Tipo A"), listOf(sampleDish))
    )
    val sampleCategories = sampleRestaurants.flatMap { it.categories }.distinct().sorted()
    val previewState = RestaurantListUi(
        allRestaurants = sampleRestaurants,
        distinctCategories = sampleCategories,
        isLoading = false,
        error = null
    )


    FoodSpotByJulioEscamillaTheme {
        RestaurantListContent(
            uiState = previewState,
            onRestaurantClick = {}
        )
    }
}

@Composable
fun CategoryHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    )
}

@Composable
fun RestaurantCarousel(
    restaurants: List<Restaurant>,
    onRestaurantClick: (restaurantId: Int) -> Unit
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(restaurants, key = { restaurant -> restaurant.id }) { restaurant ->
            RestaurantItem(
                restaurant = restaurant,
                onClick = { onRestaurantClick(restaurant.id) }
            )
        }
    }
}

@Composable
fun RestaurantItem(
    restaurant: Restaurant,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(150.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Image(
                painter = painterResource(id = restaurant.imageResId),
                contentDescription = "Imagen de ${restaurant.name}", // Descripción para accesibilidad
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = restaurant.name,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

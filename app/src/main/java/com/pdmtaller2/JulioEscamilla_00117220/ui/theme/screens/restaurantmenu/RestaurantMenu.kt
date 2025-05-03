package com.pdmtaller2.JulioEscamilla_00117220.ui.theme.screens.restaurantmenu

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdmtaller2.JulioEscamilla_00117220.data.Dish
import com.pdmtaller2.JulioEscamilla_00117220.data.Restaurant

@Composable
fun RestaurantMenuScreen(
    viewModel: RestaurantMenuViewModel = viewModel(),

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiState.toastMessage) {
        uiState.toastMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearToastMessage()
        }
    }


    Column(modifier = Modifier.fillMaxSize()) {

        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center).padding(16.dp)
                    )
                }
                uiState.restaurant != null -> {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = uiState.restaurant?.description ?: "",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))

                        SearchBar(
                            query = uiState.searchQuery,
                            onQueryChange = viewModel::updateSearchQuery,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(uiState.filteredMenuItems, key = { dish -> dish.id }) { dish ->
                                DishItem(
                                    dish = dish,
                                    onAddToCartClick = { /* No es necesario */ }
                                )
                            }
                        }
                    }
                }
                else -> {
                    Text(
                        text = "Restaurante no encontrado.",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center).padding(16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier,
        label = { Text("Buscar platillo...") },
        placeholder = { Text("Escribe nombre o descripción")},
        singleLine = true
    )
}

@Composable
fun DishItem(
    dish: Dish,
    onAddToCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium // Bordes
    ) {
        Row(
            modifier = Modifier.padding(12.dp), // Padding interno de la tarjeta
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = dish.imageResId),
                contentDescription = "Imagen de ${dish.name}",
                modifier = Modifier
                    .size(72.dp) // Tamaño de la imagen
                    .padding(end = 12.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(dish.name, style = MaterialTheme.typography.titleMedium)
                Text(
                    dish.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Button(
                onClick = onAddToCartClick,
                modifier = Modifier.padding(start = 12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text("Agregar")
            }
        }
    }
}
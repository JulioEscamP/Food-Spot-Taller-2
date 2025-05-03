package com.pdmtaller2.JulioEscamilla_00117220.ui.theme.screens.restaurantmenu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmtaller2.JulioEscamilla_00117220.data.Dish
import com.pdmtaller2.JulioEscamilla_00117220.data.Restaurant
import com.pdmtaller2.JulioEscamilla_00117220.data.RestaurantInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RestaurantMenuUiState(
    val restaurant: Restaurant? = null,
    val filteredMenuItems: List<Dish> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val toastMessage: String? = null
)

class RestaurantMenuViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val restaurantInfo = RestaurantInfo()


    private val restaurantId: Int = checkNotNull(savedStateHandle["restaurantId"])

    private val _uiState = MutableStateFlow(RestaurantMenuUiState(isLoading = true))
    val uiState: StateFlow<RestaurantMenuUiState> = _uiState.asStateFlow()

    init {
        loadRestaurantData() 
    }

    private fun loadRestaurantData() {
        _uiState.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val fetchedRestaurant = restaurantInfo.getRestaurantById(restaurantId)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        restaurant = fetchedRestaurant,
                        filteredMenuItems = fetchedRestaurant?.menu ?: emptyList()
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Error al cargar menú: ${e.message}")
                }
            }
        }
    }

    fun updateSearchQuery(query: String) {
        val currentRestaurantMenu = _uiState.value.restaurant?.menu ?: emptyList()

        val filteredList = if (query.isBlank()) {
            currentRestaurantMenu
        } else {
            currentRestaurantMenu.filter { dish ->
                dish.name.contains(query, ignoreCase = true) || dish.description.contains(query, ignoreCase = true)
            }
        }

        _uiState.update {
            it.copy(
                searchQuery = query,
                filteredMenuItems = filteredList
            )
        }
    }

    fun addToCart(dish: Dish) {

        val message = "${dish.name} agregado al carrito"

        _uiState.update {
            it.copy(toastMessage = message)
        }
    }


    fun clearToastMessage() {
        _uiState.update {
            it.copy(toastMessage = null)
        }
    }
}
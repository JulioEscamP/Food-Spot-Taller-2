package com.pdmtaller2.JulioEscamilla_00117220.ui.theme.screens.restaurantlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmtaller2.JulioEscamilla_00117220.data.Restaurant
import com.pdmtaller2.JulioEscamilla_00117220.data.RestaurantInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class RestaurantListUi(
    val allRestaurants: List<Restaurant> = emptyList(),
    val diffCategories: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class RestaurantListViewModel : ViewModel() {
    private val restaurantInfo = RestaurantInfo()
    private val _uiState = MutableStateFlow(RestaurantListUi(isLoading = true))
    val uiState: StateFlow<RestaurantListUi> = _uiState.asStateFlow() /* StateFlow Publico */

    fun loadRestaurants() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val fetchedRestaurants = /* restaurantRepository.getRestaurants() */ listOf<Restaurant>()
                _uiState.update {
                    it.copy(isLoading = false, allRestaurants = fetchedRestaurants)
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = "Error al cargar restaurantes")
                }
            }
        }
    }

    init{

        loadRestaurants()

    }

}
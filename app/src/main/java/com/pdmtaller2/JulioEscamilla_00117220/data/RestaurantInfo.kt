package com.pdmtaller2.JulioEscamilla_00117220.data


class RestaurantInfo {

    private val mockRestaurants = listOf(
        Restaurant(
            id = 1,
            name = "Chicharrones El Jon",
            description = "Los mejores Chicharrones de la ciudad.",
            imageUrl = "url_de_imagen_pupuseria", // Reemplaza con URLs reales o drawables
            categories = listOf("Comida Salvadoreña", "Típico"),
            menu = listOf(
                Dish(101, "Pupusa Revuelta", "Queso, frijol y chicharrón", "url_pupusa_revuelta"),
                Dish(102, "Pupusa de Queso con Loroco", "Queso y flor de loroco", "url_pupusa_queso_loroco"),
                Dish(103, "Yuca Frita con Chicharrón", "Trozos de yuca frita con chicharrón", "url_yuca")
            )
        ),
        Restaurant(
            id = 2,
            name = "Pizza El Jose",
            description = "Pizza artesanal con ingredientes frescos.",
            imageUrl = "url_de_imagen_pizza",
            categories = listOf("Italiana", "Pizza", "Rápida"),
            menu = listOf(
                Dish(201, "Pizza Margherita", "Salsa de tomate, mozzarella y albahaca", "url_pizza_margherita"),
                Dish(202, "Pizza Pepperoni", "Clásica pizza de pepperoni", "url_pizza_pepperoni"),
                Dish(203, "Ensalada César", "Lechuga, crutones, queso parmesano y aderezo césar", "url_ensalada")
            )
        ),
        Restaurant(
            id = 3,
            name = "Sushi el Pedro",
            description = "Variedad de sushi y comida japonesa.",
            imageUrl = "url_de_imagen_sushi",
            categories = listOf("Japonesa", "Sushi"),
            menu = listOf(
                Dish(301, "California Roll", "Rollo con cangrejo, aguacate y pepino", "url_california_roll"),
                Dish(302, "Nigiri de Salmón", "Arroz con una lámina de salmón fresco", "url_nigiri_salmon"),
                Dish(303, "Sopa Miso", "Sopa tradicional japonesa", "url_sopa_miso")
            )
        ),
        Restaurant(
            id = 4,
            name = "Ensaladas la Andrea",
            description = "Ensaladas y comida ligera.",
            imageUrl = "url_de_imagen_cafe",
            categories = listOf("Cafetería", "Postres", "Típico"), // Pertenece a Típico también
            menu = listOf(
                Dish(401, "Ensalada de coliflor", "Ensalada ", "url_pastel_choco"),
                Dish(402, "Ensalada de frutas", "Ensalada hecha con frutas de temporada, pregunta por nuestra salsa de chocolate", "url_empanada"),
                Dish(403, "Ensalada Cesar", "Una ensalada Cesar deliciosa, con strips de pollo", "url_latte")
            )
        ),

        Restaurant(
            id = 5,
            name = "Restaurante Italiano",
            description = "Auténtica cocina italiana con ingredientes importados.",
            imageUrl = "url_imagen_trattoria",
            categories = listOf("Italiana", "Pasta", "Pizza"),
            menu = listOf(
                Dish(501, "Spaghetti Carbonara", "Pasta con salsa de huevo, queso pecorino y panceta", "url_carbonara"),
                Dish(502, "Pizza Margherita", "Clásica pizza con tomate, mozzarella y albahaca", "url_margherita"),
                Dish(503, "Tiramisú", "Postre italiano con café y mascarpone", "url_tiramisu")
            )
    ),
        Restaurant(
            id = 6,
            name = "Dulceria El Chato",
            description = "Paraíso de postres artesanales y delicias dulces.",
            imageUrl = "url_imagen_sweetheaven",
            categories = listOf("Postres", "Dulces", "Repostería"),
            menu = listOf(
                Dish(601, "Cheesecake de Frutos Rojos", "Tarta de queso con coulis de frutas", "url_cheesecake"),
                Dish(602, "Cupcakes de Vainilla", "Cupcakes esponjosos con frosting", "url_cupcakes"),
                Dish(603, "Brownie con Helado", "Brownie caliente acompañado de helado de vainilla", "url_brownie")
            )
        ),

        Restaurant(
            id = 7,
            name = "Bebidas El Nico",
            description = "Bar especializado en cócteles y bebidas gourmet.",
            imageUrl = "url_imagen_drinkslab",
            categories = listOf("Bebidas", "Cócteles", "Cafés"),
            menu = listOf(
                Dish(701, "Mojito Clásico", "Ron, menta, lima, azúcar y soda", "url_mojito"),
                Dish(702, "Café Cold Brew", "Café infusionado en frío por 24 horas", "url_coldbrew"),
                Dish(703, "Smoothie Tropical", "Mezcla de mango, piña y coco", "url_smoothie")
            )
        )

    )


    suspend fun getAllRestaurants(): List<Restaurant> {
        kotlinx.coroutines.delay(500)
        return mockRestaurants
    }

    suspend fun getRestaurantById(id: Int): Restaurant? {
        kotlinx.coroutines.delay(200)
        return mockRestaurants.find { it.id == id }
    }
}
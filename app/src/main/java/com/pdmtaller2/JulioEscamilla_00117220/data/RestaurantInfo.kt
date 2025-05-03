package com.pdmtaller2.JulioEscamilla_00117220.data

import com.pdmtaller2.JulioEscamilla_00117220.R

class RestaurantInfo {

    private val mockRestaurants = listOf(
        Restaurant(
            id = 1,
            name = "Chicharrones El Jon",
            description = "Los mejores Chicharrones de la ciudad.",
            imageResId = R.drawable.chicharrones,
            categories = listOf("Comida Salvadoreña", "Típico"),
            menu = listOf(
                Dish(101, "Chicharrón Frito Clásico", "Trozos crujientes de chicharrón frito con yuca", R.drawable.chicharrones),
                Dish(102, "Chicharrón con Salsa", "Chicharrón bañado en salsa picante de tomate y cebolla", R.drawable.chicharrones),
                Dish(103, "Chicharrón con Curtido", "Chicharrón acompañado de repollo curtido y salsa", R.drawable.chicharrones)
            )
        ),
        Restaurant(
            id = 2,
            name = "Pizza El Jose",
            description = "Pizza artesanal con ingredientes frescos.",
            imageResId = R.drawable.pizza,
            categories = listOf("Italiana", "Pizza", "Rápida"),
            menu = listOf(
                Dish(201, "Pizza Margherita", "Salsa de tomate, mozzarella y albahaca", R.drawable.pizza),
                Dish(202, "Pizza Pepperoni", "Clásica pizza de pepperoni", R.drawable.pizza),
                Dish(203, "Ensalada César", "Lechuga, crutones, queso parmesano y aderezo césar", R.drawable.pizza)
            )
        ),
        Restaurant(
            id = 3,
            name = "Sushi el Pedro",
            description = "Variedad de sushi y comida japonesa.",
            imageResId = R.drawable.plate_sushi,
            categories = listOf("Japonesa", "Sushi"),
            menu = listOf(
                Dish(301, "California Roll", "Rollo con cangrejo, aguacate y pepino", R.drawable.plate_sushi),
                Dish(302, "Nigiri de Salmón", "Arroz con una lámina de salmón fresco", R.drawable.plate_sushi),
                Dish(303, "Sopa Miso", "Sopa tradicional japonesa", R.drawable.plate_sushi)
            )
        ),
        Restaurant(
            id = 4,
            name = "Ensaladas la Andrea",
            description = "Ensaladas y comida ligera.",
            imageResId = R.drawable.butternut_salad,
            categories = listOf("Cafetería", "Postres", "Típico"), // Pertenece a Típico también
            menu = listOf(
                Dish(401, "Ensalada de coliflor", "Ensalada ", R.drawable.butternut_salad),
                Dish(402, "Ensalada de frutas", "Ensalada hecha con frutas de temporada, pregunta por nuestra salsa de chocolate", R.drawable.butternut_salad),
                Dish(403, "Ensalada Cesar", "Una ensalada Cesar deliciosa, con strips de pollo", R.drawable.butternut_salad)
            )
        ),

        Restaurant(
            id = 5,
            name = "Restaurante Italiano",
            description = "Auténtica cocina italiana con ingredientes importados.",
            imageResId = R.drawable.italiano_res,
            categories = listOf("Italiana", "Pasta", "Pizza"),
            menu = listOf(
                Dish(501, "Spaghetti Carbonara", "Pasta con salsa de huevo, queso pecorino y panceta", R.drawable.italiano_res),
                Dish(502, "Pizza Margherita", "Clásica pizza con tomate, mozzarella y albahaca", R.drawable.italiano_res),
                Dish(503, "Tiramisú", "Postre italiano con café y mascarpone", R.drawable.italiano_res)
            )
    ),
        Restaurant(
            id = 6,
            name = "Dulceria El Chato",
            description = "Paraíso de postres artesanales y delicias dulces.",
            imageResId = R.drawable.candy_dispensary,
            categories = listOf("Postres", "Dulces", "Repostería"),
            menu = listOf(
                Dish(601, "Cheesecake de Frutos Rojos", "Tarta de queso con coulis de frutas", R.drawable.candy_dispensary),
                Dish(602, "Cupcakes de Vainilla", "Cupcakes esponjosos con frosting", R.drawable.candy_dispensary),
                Dish(603, "Brownie con Helado", "Brownie caliente acompañado de helado de vainilla", R.drawable.candy_dispensary)
            )
        ),

        Restaurant(
            id = 7,
            name = "Bebidas El Nico",
            description = "Bar especializado en cócteles y bebidas gourmet.",
            imageResId = R.drawable.bar_sign,
            categories = listOf("Bebidas", "Cócteles", "Cafés"),
            menu = listOf(
                Dish(701, "Mojito Clásico", "Ron, menta, lima, azúcar y soda", R.drawable.candy_dispensary),
                Dish(702, "Café Cold Brew", "Café infusionado en frío por 24 horas", R.drawable.candy_dispensary),
                Dish(703, "Smoothie Tropical", "Mezcla de mango, piña y coco", R.drawable.candy_dispensary)
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
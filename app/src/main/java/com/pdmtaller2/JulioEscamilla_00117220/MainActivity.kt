package com.pdmtaller2.JulioEscamilla_00117220

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pdmtaller2.JulioEscamilla_00117220.ui.theme.FoodSpotByJulioEscamillaTheme
import com.pdmtaller2.JulioEscamilla_00117220.FoodAppScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodSpotByJulioEscamillaTheme {
                    FoodAppScreen()
                }
            }
        }
    }



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodSpotByJulioEscamillaTheme {
        FoodAppScreen()
    }
}
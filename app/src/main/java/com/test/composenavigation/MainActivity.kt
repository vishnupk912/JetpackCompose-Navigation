package com.test.composenavigation


import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.composenavigation.ui.theme.ComposeNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationTheme() {
                UserApplication()
                // A surface container using the 'background' color from the theme
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreen(navController: NavHostController) {
    val userid=123
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                navController.navigate("black_screen/$userid")
            },
        color = Color.Blue
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen(userId: Int) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Log.d("User_id", userId.toString())
    }
}

@Composable
fun UserApplication() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController)
        }

        composable(route = "black_screen/{user_id}", arguments = listOf(navArgument("user_id") {
            type = NavType.IntType
        })) {
            HomeScreen(it.arguments!!.getInt("user_id"))
        }
    }
}

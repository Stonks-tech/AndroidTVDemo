package tech.stonks.tvdemo

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import tech.stonks.tvdemo.di.appModule
import tech.stonks.tvdemo.page.player.PlayerPage
import tech.stonks.tvdemo.page.selection.SelectionPage

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "selection") {
                composable("selection") {
                    SelectionPage(navController)
                }
                composable("player/{id}") {
                    val id = it.arguments?.getString("id")!!
                    PlayerPage(id = id)
                }
            }
        }
    }
}

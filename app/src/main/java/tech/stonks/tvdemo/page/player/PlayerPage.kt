package tech.stonks.tvdemo.page.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import tech.stonks.tvdemo.component.VideoPlayer

@Composable
fun PlayerPage(id: String) {
    val viewModel = koinViewModel<PlayerViewModel>()
    val state = viewModel.state.observeAsState()
    LaunchedEffect(id) {
        viewModel.load(id)
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        if (state.value?.show != null) {
            VideoPlayer(url = state.value?.show?.videoUrl ?: "")
        } else {
            Box(modifier = Modifier.align(Alignment.Center)) {
                CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Color.White)
            }
        }
    }
}

package tech.stonks.tvdemo.page.selection

import android.util.Log
import android.util.Log.*
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import tech.stonks.tvdemo.model.ShowModel
import tech.stonks.tvdemo.R


@Composable()
fun SelectionPage(navController: NavController) {
    val viewModel = koinViewModel<SelectionViewModel>()
    val state = viewModel.state.observeAsState()
    Column(modifier = Modifier.fillMaxSize()) {
        SelectionRow(
            title = stringResource(id = R.string.shows), list = state.value?.shows ?: emptyList(),
            onShowSelected = { show ->
                navController.navigate("player/${show.id}")
            }
        )
        SelectionRow(
            title = stringResource(id = R.string.movies), list = state.value?.movies ?: emptyList(),
            onShowSelected = { show ->
                navController.navigate("player/${show.id}")
            }
        )
    }
}


@Composable()
private fun SelectionRow(title: String, list: List<ShowModel>, onShowSelected: (ShowModel) -> Unit = {}) {
    val horizontalScrollState = remember { androidx.compose.foundation.lazy.LazyListState() }
    Column(modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .scrollable(horizontalScrollState, Orientation.Horizontal)
                .padding(top = 16.dp)
        ) {
            items(list.size) { index ->
                ShowItem(show = list[index], onShowSelected = onShowSelected)
            }
        }
    }
}


@Composable
private fun ShowItem(show: ShowModel, onShowSelected: (ShowModel) -> Unit = {}) {
    val focusRequester = remember { FocusRequester() }
    val isSelected = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .width(300.dp)
            .height(150.dp)
            .shadow(if (isSelected.value) 0.dp else 16.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, if (isSelected.value) Color.White else Color.Transparent, RoundedCornerShape(16.dp))
            .focusRequester(focusRequester)
            .onFocusChanged { focusState ->
                isSelected.value = focusState.isFocused
            }
            .clickable { onShowSelected(show) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(show.imageUrl)
                    .build()
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(alignment = Alignment.BottomCenter)
                .fillMaxWidth()
                .background(
                    androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        )
                    ),
                )
                .padding(16.dp)
                .clickable { }
        ) {
            Text(text = show.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

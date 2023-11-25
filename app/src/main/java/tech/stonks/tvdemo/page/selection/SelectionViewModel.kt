package tech.stonks.tvdemo.page.selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.stonks.tvdemo.model.ShowModel
import tech.stonks.tvdemo.service.ShowsService

class SelectionViewModel(private val _showService: ShowsService) : ViewModel() {
    private val _state: MutableLiveData<SelectionState> = MutableLiveData(SelectionState(
        shows = _showService.getShows(),
        movies = _showService.getMovies(),
    ))
    val state: LiveData<SelectionState>
        get() = _state
}

data class SelectionState(
    val shows: List<ShowModel>,
    val movies: List<ShowModel>,
)

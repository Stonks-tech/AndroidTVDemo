package tech.stonks.tvdemo.page.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.stonks.tvdemo.model.ShowModel
import tech.stonks.tvdemo.service.ShowsService

class PlayerViewModel(private val _showsService: ShowsService) : ViewModel() {
    private val _state = MutableLiveData(PlayerState())
    val state: LiveData<PlayerState> = _state

    fun load(id: String) {
        _state.value = PlayerState(
            show = _showsService.get(id)
        )
    }
}

data class PlayerState(
    val show: ShowModel? = null,
)

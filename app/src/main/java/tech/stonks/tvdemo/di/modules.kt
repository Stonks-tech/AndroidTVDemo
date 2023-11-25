package tech.stonks.tvdemo.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tech.stonks.tvdemo.page.player.PlayerViewModel
import tech.stonks.tvdemo.page.selection.SelectionViewModel
import tech.stonks.tvdemo.service.ShowsService

val appModule = module {
    single { ShowsService() }
    viewModel { SelectionViewModel(get()) }
    viewModel { PlayerViewModel(get()) }
}

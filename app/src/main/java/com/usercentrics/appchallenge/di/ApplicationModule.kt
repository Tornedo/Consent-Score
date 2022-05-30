package com.usercentrics.appchallenge.di

import com.usercentrics.appchallenge.view.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * For Application (View Model)
 * */
object ApplicationModule {
    val applicationModule : Module = module {
        viewModel {
            provideMainViewModel()
        }

    }
    private fun provideMainViewModel() : MainViewModel = MainViewModel()
}

package com.fernandohbrasil.stackexchange.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fernandohbrasil.stackexchange.di.ViewModelKey
import com.fernandohbrasil.stackexchange.ui.detail.UserDetailViewModel
import com.fernandohbrasil.stackexchange.ui.factory.ViewModelFactory
import com.fernandohbrasil.stackexchange.ui.main.ListUsersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    abstract fun userDetailViewModel(userDetailViewModel: UserDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListUsersViewModel::class)
    abstract fun listUsersViewModel(listUsersViewModel: ListUsersViewModel): ViewModel
}
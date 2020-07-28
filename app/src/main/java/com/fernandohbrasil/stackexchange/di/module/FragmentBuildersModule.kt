package com.fernandohbrasil.stackexchange.di.module

import com.fernandohbrasil.stackexchange.ui.detail.UserDetailFragment
import com.fernandohbrasil.stackexchange.ui.main.ListUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): ListUsersFragment

    @ContributesAndroidInjector
    abstract fun userDetailFragment(): UserDetailFragment
}
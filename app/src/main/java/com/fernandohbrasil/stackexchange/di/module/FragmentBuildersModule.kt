package com.fernandohbrasil.stackexchange.di.module

import com.fernandohbrasil.stackexchange.ui.detail.DetailFragment
import com.fernandohbrasil.stackexchange.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun mainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun detailFragment(): DetailFragment
}
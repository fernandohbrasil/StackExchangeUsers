package com.fernandohbrasil.stackexchange.di.module


import com.fernandohbrasil.stackexchange.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun mainActivity(): MainActivity
}
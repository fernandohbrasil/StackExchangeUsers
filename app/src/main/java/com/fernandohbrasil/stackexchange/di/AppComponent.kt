package com.fernandohbrasil.stackexchange.di

import android.app.Application
import com.fernandohbrasil.stackexchange.App
import com.fernandohbrasil.stackexchange.di.module.ActivityModule
import com.fernandohbrasil.stackexchange.di.module.NetworkModule
import com.fernandohbrasil.stackexchange.repository.UsersRepository
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ActivityModule::class]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
    fun inject(usersRepository: UsersRepository)
}
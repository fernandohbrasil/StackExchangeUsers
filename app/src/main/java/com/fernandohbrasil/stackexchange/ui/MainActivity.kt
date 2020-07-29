package com.fernandohbrasil.stackexchange.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fernandohbrasil.stackexchange.R
import com.fernandohbrasil.stackexchange.ui.viewmodel.AppBar
import com.fernandohbrasil.stackexchange.ui.viewmodel.SharedViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() = dispatchingAndroidInjector

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val sharedViewModel: SharedViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        sharedViewModel.appBar.observe(this, (appBarObserver()))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun appBarObserver(): Observer<AppBar> = Observer {
        when (it.title.isBlank()) {
            false -> {supportActionBar?.title = it.title}
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(it.hasUpButton)
    }
}
package com.fernandohbrasil.stackexchange.ui.main

import androidx.lifecycle.ViewModel
import com.fernandohbrasil.stackexchange.repository.UsersRepository
import com.fernandohbrasil.stackexchange.ui.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseViewModel() {}
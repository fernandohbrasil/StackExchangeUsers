package com.fernandohbrasil.stackexchange.ui.detail

import com.fernandohbrasil.stackexchange.repository.UsersRepository
import com.fernandohbrasil.stackexchange.ui.BaseViewModel
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : BaseViewModel() {}
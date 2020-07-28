package com.fernandohbrasil.stackexchange.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fernandohbrasil.stackexchange.network.model.Users
import com.fernandohbrasil.stackexchange.repository.UsersRepository
import com.fernandohbrasil.stackexchange.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListUsersViewModel @Inject constructor(private val usersRepository: UsersRepository) : BaseViewModel() {

    private val _usersState = MutableLiveData<UsersState>()
    val usersState: LiveData<UsersState>
        get() = _usersState

    fun start(){
        _usersState.postValue(UsersStarted)
    }

    fun findMySquad(name: String) {
        disposable.add(
            usersRepository.getUsers(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { _usersState.postValue(UsersSuccess(it) ) },
                    { _usersState.postValue(UsersError(it.message)) }
                )
        )
    }

    fun finish(){
        _usersState.postValue(UsersFinished)
    }
}

sealed class UsersState
object UsersStarted : UsersState()
object UsersFinished : UsersState()
data class UsersSuccess(val users: Users) : UsersState()
data class UsersError(val error: String?) : UsersState()

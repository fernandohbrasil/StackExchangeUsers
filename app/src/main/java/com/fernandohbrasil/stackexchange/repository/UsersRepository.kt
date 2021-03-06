package com.fernandohbrasil.stackexchange.repository

import com.fernandohbrasil.stackexchange.network.StackExchangeApi
import com.fernandohbrasil.stackexchange.network.model.Users
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersRepository @Inject constructor(private val api: StackExchangeApi) {

    fun getUsers(name: String): Flowable<Users> = api.getUsers(name)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getUserById(userId: Int): Single<Users> = api.getUserById(userId.toString())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
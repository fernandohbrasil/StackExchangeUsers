package com.fernandohbrasil.stackexchange.network

import com.fernandohbrasil.stackexchange.network.model.Users
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {

    @GET("/2.2/users")
    fun getUsers(
        @Query("inname") inName: String,

        @Query("sort") sort: String = "name",
        @Query("order") order: String = "asc",
        @Query("site") site: String = "stackoverflow",
        @Query("pagesize") pagesize: Int = 20,
        @Query("filter") filter: String = "!40D.p(ALD9XjxPJmP"
    ): Flowable<Users>
}
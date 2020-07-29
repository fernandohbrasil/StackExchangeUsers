package com.fernandohbrasil.stackexchange.network

import com.fernandohbrasil.stackexchange.network.model.Users
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/** This filter bring userId, display_name **/
const val GET_USERS_ENDPOINT =
    "/2.2/users?site=stackoverflow&order=asc&sort=name&pagesize=20&filter=!40D.p)TeRz*_jvlxT"

/** This filter bring: badge_counts, reputation, creation_date, user_id, profile_image,
 * display_name **/
const val GET_USERS_BY_ID_ENDPOINT =
    "/2.2/users/{ids}?site=stackoverflow&filter=!40D.p(1aX3S_*UrsH"

interface StackExchangeApi {

    @GET(GET_USERS_ENDPOINT)
    fun getUsers(@Query("inname") inName: String): Flowable<Users>

    @GET(GET_USERS_BY_ID_ENDPOINT)
    fun getUserById(@Path("ids") id: String): Single<Users>
}
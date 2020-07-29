# StackExchangeUsers

## Description

Application that explores a little of the [StackExchange API](https://api.stackexchange.com/). With two screens:

* One with the details of the User.
* And other with a list of 20 users in alphabetical order, search results above the screen.

## Libs and Techniques

* [Dagger](https://dagger.dev/)
* [Retrofit](https://square.github.io/retrofit/) 
* [Moshi](https://github.com/square/moshi)
* [JetPack Navigation](https://developer.android.com/guide/navigation/)
* [Glide](https://bumptech.github.io/glide/)
* [Live Data](https://developer.android.com/topic/libraries/architecture/livedata)
* [RXJava](https://github.com/ReactiveX/RxJava)
* [MVVM](https://developer.android.com/jetpack/docs/guide)
* [Mockito](https://site.mockito.org/)
* [Espresso](https://developer.android.com/training/testing/espresso)

## App architecture

![App architecture](screenshots/apparchitecture.png)

In the architecture I chose to use ViewModel with MVI, which in my opinion works very well with RXJava and makes the App very easy to test. Within MVI, I create sealed classes to represent my states and thus, I can transport this data for display.

So to test this, I just to need cover all states x who changes them.

## Screenshots

![Screenshot1](screenshots/Screenshot1.png)
![Screenshot2](screenshots/Screenshot2.png)
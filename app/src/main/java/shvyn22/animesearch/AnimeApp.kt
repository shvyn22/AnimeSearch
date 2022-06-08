package shvyn22.animesearch

import android.app.Application
import shvyn22.animesearch.di.component.DaggerSingletonComponent
import shvyn22.animesearch.di.component.SingletonComponent

class AnimeApp : Application() {

    lateinit var singletonComponent: SingletonComponent

    override fun onCreate() {
        super.onCreate()

        singletonComponent = DaggerSingletonComponent.factory().create(this)
    }
}
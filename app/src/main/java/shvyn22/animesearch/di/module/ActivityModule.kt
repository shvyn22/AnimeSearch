package shvyn22.animesearch.di.module

import android.content.Context
import androidx.activity.result.ActivityResultRegistry
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import shvyn22.animesearch.di.component.SearchScope

@Module
object ActivityModule {

    @SearchScope
    @Provides
    fun provideRegistry(activity: Context): ActivityResultRegistry =
        (activity as AppCompatActivity).activityResultRegistry
}
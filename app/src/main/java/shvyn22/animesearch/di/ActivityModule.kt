package shvyn22.animesearch.di

import android.content.Context
import androidx.activity.result.ActivityResultRegistry
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

	@Provides
	fun provideRegistry(@ActivityContext activity: Context): ActivityResultRegistry =
		(activity as AppCompatActivity).activityResultRegistry
}

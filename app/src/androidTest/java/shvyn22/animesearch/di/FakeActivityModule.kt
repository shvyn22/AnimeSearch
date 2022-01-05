package shvyn22.animesearch.di

import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.app.ActivityOptionsCompat
import androidx.core.net.toUri
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [ActivityComponent::class],
    replaces = [ActivityModule::class]
)
object FakeActivityModule {

    @Provides
    fun provideRegistry(): ActivityResultRegistry = object : ActivityResultRegistry() {

        override fun <I : Any?, O : Any?> onLaunch(
            requestCode: Int,
            contract: ActivityResultContract<I, O>,
            input: I,
            options: ActivityOptionsCompat?
        ) {
            dispatchResult(requestCode, "".toUri())
        }
    }
}
package shvyn22.animesearch.di.module

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import shvyn22.animesearch.api.ApiInterface
import shvyn22.animesearch.util.BASE_URL
import javax.inject.Singleton

@Module
object NetworkModule {

	@Singleton
	@Provides
	fun provideRetrofit(): Retrofit =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.addCallAdapterFactory(RxJava3CallAdapterFactory.create())
			.addConverterFactory(GsonConverterFactory.create())
			.build()

	@Singleton
	@Provides
	fun provideApiInterface(retrofit: Retrofit): ApiInterface =
		retrofit.create(ApiInterface::class.java)
}
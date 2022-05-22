package com.hridoydas.hridoyapp.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hridoydas.hridoyapp.Data.Dao.DogsDao
import com.hridoydas.hridoyapp.Data.database.DogsDatabase
import com.hridoydas.hridoyapp.Network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()!!

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi)=
        Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)!!

    @Provides
    @Singleton
    fun providesDatabase(context:Application)=
        Room.databaseBuilder(context,DogsDatabase::class.java,"DogsDatabase")
            .build()

    @Provides
    @Singleton
    fun providesDogsDao(db:DogsDatabase) =
        db.getDogSDao()

    @Provides
    @Singleton
    fun providesRemoteDao(db: DogsDatabase) =
        db.getRemoteKeysDao()


}
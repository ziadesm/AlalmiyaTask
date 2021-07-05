package com.task.alalmiyatask.di
import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.alalmiyatask.db.CharacterDao
import com.task.alalmiyatask.db.CharacterDatabase
import com.task.alalmiyatask.network.CharacterNetworkRequest
import com.task.alalmiyatask.remote.NetworkResponseAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideNetworkCall(gson: Gson): CharacterNetworkRequest {
        return Retrofit.Builder()
            .baseUrl("https://www.alalmiyalhura.com")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CharacterNetworkRequest::class.java)
    }

    @Singleton
    @Provides
    fun provideCharacterDatabase(@ApplicationContext context: Context): CharacterDatabase{
        return Room
            .databaseBuilder(context, CharacterDatabase::class.java, "site_characters")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideCharacterDao(characterDB: CharacterDatabase): CharacterDao{
        return characterDB.characterDao()
    }
}
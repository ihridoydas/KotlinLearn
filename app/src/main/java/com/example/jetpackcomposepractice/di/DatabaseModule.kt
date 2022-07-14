package com.example.jetpackcomposepractice.di

import android.app.Application
import androidx.room.Room
import com.example.jetpackcomposepractice.retrofitAPI.network.Apiservice
import com.example.jetpackcomposepractice.retrofitAPI.repository.DataRepository
import com.example.jetpackcomposepractice.feature_note.data.data_source.NoteDatabase
import com.example.jetpackcomposepractice.feature_note.data.repository.NoteRepositoryImpl
import com.example.jetpackcomposepractice.feature_note.domain.repository.NoteRepository
import com.example.jetpackcomposepractice.feature_note.domain.use_case.*
import com.example.jetpackcomposepractice.todo.data.dao.TodoDao
import com.example.jetpackcomposepractice.todo.data.database.TodoDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    //Provide Database Room
    @Provides
    @Singleton
    fun providesDatabase(application: Application): TodoDatabase =
        Room.databaseBuilder(application, TodoDatabase::class.java, "TodoDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesDao(db: TodoDatabase): TodoDao = db.getDao()

    //Provide API Retrofit
    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesApiService(moshi: Moshi): Apiservice =
        Retrofit.Builder()
            .run {
                baseUrl(Apiservice.BASE_URL)
                addConverterFactory(MoshiConverterFactory.create(moshi))
                build()
            }.create(Apiservice::class.java)


    //Feature Note Database Room
    @Provides
    @Singleton
    fun providesNoteDatabase(application: Application): NoteDatabase =
        Room.databaseBuilder(application, NoteDatabase::class.java, NoteDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesNoteRepository(db: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(db.noteDao)
    }

    @Provides
    fun providesDataRepository(apiservice: Apiservice): DataRepository {
        return DataRepository(apiservice)
    }

    @Provides
    @Singleton
    fun providesNotesUseCases(noteRepository: NoteRepository): NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }



}
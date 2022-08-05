package com.example.jetpackcomposepractice.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposepractice.feature_note.data.data_source.NoteDatabase
import com.example.jetpackcomposepractice.feature_note.data.repository.NoteRepositoryImpl
import com.example.jetpackcomposepractice.feature_note.domain.repository.NoteRepository
import com.example.jetpackcomposepractice.feature_note.domain.use_case.*
import com.example.jetpackcomposepractice.onboardingCompose.data.DataStoreRepository
import com.example.jetpackcomposepractice.paging3.data.local.UnsplashDatabase
import com.example.jetpackcomposepractice.paging3.util.Constants.UNSPLASH_DATABASE
import com.example.jetpackcomposepractice.retrofitAPI.network.Apiservice
import com.example.jetpackcomposepractice.retrofitAPI.repository.DataRepository
import com.example.jetpackcomposepractice.todo.data.dao.TodoDao
import com.example.jetpackcomposepractice.todo.data.database.TodoDatabase
import com.example.jetpackcomposepractice.todoMVVM.data.database.ToDoDatabase
import com.example.jetpackcomposepractice.todoMVVM.repository.TodoRepository
import com.example.jetpackcomposepractice.todoMVVM.repository.TodoRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesNotesUseCases(noteRepository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }

    //Todo MVVM Project

    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application): ToDoDatabase {
        return Room.databaseBuilder(
            application,
            ToDoDatabase::class.java,
            "todo_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db: ToDoDatabase): TodoRepository {
        return TodoRepositoryImpl(db.todoDao)
    }

    //Unsplash Api Learn
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): UnsplashDatabase {
        return Room.databaseBuilder(
            context,
            UnsplashDatabase::class.java,
            UNSPLASH_DATABASE
        ).build()
    }

    //OnBoarding Data Store --> provideDataStoreRepository 
    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)
}
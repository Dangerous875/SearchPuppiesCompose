package com.example.searchdogspracticecompose.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.searchdogspracticecompose.data.database.dao.DogsDao
import com.example.searchdogspracticecompose.data.database.entities.DogEntity

@Database(entities = [DogEntity::class], version = 1)
abstract class DogsDataBase : RoomDatabase() {
    abstract fun getDogDao(): DogsDao
}
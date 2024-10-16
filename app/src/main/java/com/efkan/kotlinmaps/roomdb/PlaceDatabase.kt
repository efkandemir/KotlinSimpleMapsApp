package com.efkan.kotlinmaps.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efkan.kotlinmaps.model.place

class PlaceDatabase {
    @Database(entities = arrayOf(place::class), version = 1)
    abstract class PlaceDatabase :RoomDatabase(){
        abstract fun placeDao():PlaceDao
    }
}
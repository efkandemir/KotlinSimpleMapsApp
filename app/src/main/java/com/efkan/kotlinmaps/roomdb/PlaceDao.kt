package com.efkan.kotlinmaps.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.efkan.kotlinmaps.model.place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao //data access object
interface PlaceDao {
    @Query("Select * From PLACE")
    fun getAll() :Flowable<List<place>>   //Flowable'ı asenkron çalışması için sonradan ekledim .

    @Insert
    fun insert(place:place) :Completable

    @Delete
    fun delete(place: place):Completable
}
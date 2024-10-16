package com.efkan.kotlinmaps.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class place(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double    //id yi constructor'da vermek zorunda olmadığım için id'yi bu kısımda değil de body kısmında tanıjmlayacağım . mantıklı olan odur

):Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
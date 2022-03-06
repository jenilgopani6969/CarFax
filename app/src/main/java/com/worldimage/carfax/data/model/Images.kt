package com.worldimage.carfax.data.model

import androidx.room.TypeConverter

data class Images(
    val large: List<String>,
    val medium: List<String>,
    val small: List<String>
)

class TypeConverterImages {

    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }

}
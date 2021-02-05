package com.np.kisanthapa.guideforpubgmobile.ui.backpack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_backpack")
data class BackPackModelClass(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val backPackId: Int,
    @ColumnInfo(name = "name") val backPackName: String,
    @ColumnInfo(name = "full_name") val backPackFullName: String,
    @ColumnInfo(name = "level") val backPackLevel: String,
    @ColumnInfo(name = "description") val backPackDescription: String,
    @ColumnInfo(name = "capacity") val backPackCapacity: Int,
    @ColumnInfo(name = "image_name") val backPackImageName: String,
    @ColumnInfo(name = "variants") val backPackVariants: String
)
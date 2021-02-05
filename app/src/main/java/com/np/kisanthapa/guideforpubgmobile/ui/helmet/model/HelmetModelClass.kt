package com.np.kisanthapa.guideforpubgmobile.ui.helmet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_helmet")
data class HelmetModelClass(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val helmetId: Int,
    @ColumnInfo(name = "name") val helmetName: String,
    @ColumnInfo(name = "full_name") val helmetFullName: String,
    @ColumnInfo(name = "level") val helmetLevel: String,
    @ColumnInfo(name = "description") val helmetDescription: String,
    @ColumnInfo(name = "durability") val helmetDurability: Int,
    @ColumnInfo(name = "damage_reduction") val helmetDamageReduction: Int,
    @ColumnInfo(name = "image_name") val helmetImageName: String
)
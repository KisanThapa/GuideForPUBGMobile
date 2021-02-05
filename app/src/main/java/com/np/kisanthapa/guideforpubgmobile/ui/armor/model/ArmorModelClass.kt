package com.np.kisanthapa.guideforpubgmobile.ui.armor.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_armor")
data class ArmorModelClass(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val armorId: Int,
    @ColumnInfo(name = "name") val armorName: String,
    @ColumnInfo(name = "full_name") val armorFullName: String,
    @ColumnInfo(name = "level") val armorLevel: String,
    @ColumnInfo(name = "description") val armorDescription: String,
    @ColumnInfo(name = "capacity_description") val armorCapacityDescription: String,
    @ColumnInfo(name = "capacity_extension") val armorCapacityExtension: Int,
    @ColumnInfo(name = "damage_reduction") val armorDamageReduction: Int,
    @ColumnInfo(name = "durability") val armorDurability: Int,
    @ColumnInfo(name = "image_name") val armorImageName: String
)

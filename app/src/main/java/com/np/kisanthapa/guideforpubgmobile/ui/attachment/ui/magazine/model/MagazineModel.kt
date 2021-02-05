package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_magazine")
data class MagazineModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val magId: Int,
    @ColumnInfo(name = "name") val magName: String,
    @ColumnInfo(name = "summary") val magSummary: String,
    @ColumnInfo(name = "feature") val magFeature: String,
    @ColumnInfo(name = "capacity") val magCapacity: String,
    @ColumnInfo(name = "supported_weapon_names") val magSupportedWeaponName: String,
    @ColumnInfo(name = "supported_weapon_images") val magSupportedWeaponImage: String,
    @ColumnInfo(name = "supported_weapon_def_cap") val magSupportedWeaponDefCap: String,
    @ColumnInfo(name = "supported_weapon_ext_cap") val magSupportedWeaponExtCap: String,
    @ColumnInfo(name = "image") val magImage: String
)
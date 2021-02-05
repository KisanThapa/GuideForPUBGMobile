package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_muzzle")
data class MuzzleModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val muzzleId: Int,
    @ColumnInfo(name = "name") val muzzleName: String,
    @ColumnInfo(name = "summary") val muzzleSummary: String,
    @ColumnInfo(name = "feature") val muzzleFeature: String,
    @ColumnInfo(name = "supported_weapon_names") val muzzleSupportedWeaponName: String,
    @ColumnInfo(name = "supported_weapon_images") val muzzleSupportedWeaponImage: String,
    @ColumnInfo(name = "image") val muzzleImage: String
)
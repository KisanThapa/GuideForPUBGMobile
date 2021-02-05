package com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_ammunition")
data class AmmunitionModel(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val amId: Int,
    @ColumnInfo(name = "name") val amName: String,
    @ColumnInfo(name = "summary") val amSummary: String,
    @ColumnInfo(name = "capacity") val amCapacity: String,
    @ColumnInfo(name = "feature") val amFeature: String,
    @ColumnInfo(name = "supported_weapon_names") val amSupportedWeaponName: String,
    @ColumnInfo(name = "supported_weapon_images") val amSupportedWeaponImages: String,
    @ColumnInfo(name = "image") val amImageName: String
)
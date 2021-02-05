package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_scope")
data class ScopeModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val scopeId: Int,
    @ColumnInfo(name = "name") val scopeName: String,
    @ColumnInfo(name = "summary") val scopeSummary: String,
    @ColumnInfo(name = "capacity") val scopeCapacity: String,
    @ColumnInfo(name = "supported_weapon_names") val scopeSupportedWeaponName: String,
    @ColumnInfo(name = "supported_weapon_images") val scopeSupportedWeaponImage: String,
    @ColumnInfo(name = "image") val scopeImage: String
)
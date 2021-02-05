package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_stock")
data class StockModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val stockId: Int,
    @ColumnInfo(name = "name") val stockName: String,
    @ColumnInfo(name = "summary") val stockSummary: String,
    @ColumnInfo(name = "feature") val stockFeature: String,
    @ColumnInfo(name = "supported_weapon_names") val stockSupportedWeaponName: String,
    @ColumnInfo(name = "supported_weapon_images") val stockSupportedWeaponImage: String,
    @ColumnInfo(name = "image") val stockImage: String
)
package com.np.kisanthapa.guideforpubgmobile.ui.consumable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_consumable")
data class ConsumableModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val consumableId: Int,
    @ColumnInfo(name = "name") val consumableName: String,
    @ColumnInfo(name = "type") val consumableType: String,
    @ColumnInfo(name = "description") val consumableDescription: String,
    @ColumnInfo(name = "feature") val consumableFeature: String,
    @ColumnInfo(name = "image_name") val consumableImageName: String

)
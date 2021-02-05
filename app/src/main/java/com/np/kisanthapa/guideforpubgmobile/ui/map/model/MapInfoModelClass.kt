package com.np.kisanthapa.guideforpubgmobile.ui.map.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_map_info")
data class MapInfoModelClass(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val mapId: Int,
    @ColumnInfo(name = "name") val mapName: String,
    @ColumnInfo(name = "general_info") val mapGeneralInfo: String,
    @ColumnInfo(name = "geography") val mapGeography: String,
    @ColumnInfo(name = "transport") val mapTransport: String,
    @ColumnInfo(name = "looting") val mapLooting: String,
    @ColumnInfo(name = "summary") val mapSummary: String,
    @ColumnInfo(name = "background") val mapBackground: String,
    @ColumnInfo(name = "description") val mapDescription: String,
    @ColumnInfo(name = "behind_story") val mapBehindStory: String,
    @ColumnInfo(name = "strategy") val mapStrategy: String,
    @ColumnInfo(name = "map_image") val mapImage: String
)

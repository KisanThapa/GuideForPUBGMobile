package com.np.kisanthapa.guideforpubgmobile.ui.throwable.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_throwable")
data class ThrowableModelClass(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val throwableId: Int,
    @ColumnInfo(name = "name") val throwableName: String,
    @ColumnInfo(name = "full_name") val throwableFullName: String,
    @ColumnInfo(name = "description") val throwableDescription: String,
    @ColumnInfo(name = "usage_instruction") val throwableUsageInstruction: String,
    @ColumnInfo(name = "capacity") val throwableCapacity: Int,
    @ColumnInfo(name = "pickup_delay") val throwablePickupDelay: Int,
    @ColumnInfo(name = "ready_delay") val throwableReadyDelay: Int,
    @ColumnInfo(name = "image_name") val throwableImageName: String

)
package com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VehicleModelClassParcelable(
    val vehicleId: Int,
    val vehicleName: String,
    val vehicleSummary: String,
    val vehicleFeature: String,
    val vehicleOccupants: Int,
    val vehicleHealth: Int,
    val vehicleTopSpeed: String,
    val vehicleType: String,
    val vehicleAppearsIn: String,
    val vehicleImageName: String,
    val vehicleIconImageName: String

) : Parcelable {}
package com.np.kisanthapa.guideforpubgmobile.ui.vehicle.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_vehicle.view.*
import kotlinx.android.synthetic.main.item_single_vehicle_state.view.*


class VehicleRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_VEHICLE_STAT = 4
    private val TYPE_OTHER_ITEM = 5


    // Click handling for items
    private lateinit var vehicleItemClickCallback: VehicleItemClickCallback

    interface VehicleItemClickCallback {
        fun onItemClick(vehicleModel: VehicleModelClass)
        fun onStatItemClick(vehicleDataList: List<VehicleModelClass>)
    }

    fun onSetVehicleItemClickListener(vehicleItemClickCallback: VehicleItemClickCallback) {
        this.vehicleItemClickCallback = vehicleItemClickCallback
    }

    private var vehicleList = emptyList<VehicleModelClass>()

    internal fun setVehicleData(backPack: List<VehicleModelClass>) {
        vehicleList = backPack
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_VEHICLE_STAT) {
            VehicleStatViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_single_vehicle_state,
                        parent,
                        false
                    )
            )
        } else {
            VehicleViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(
                        R.layout.item_single_vehicle,
                        parent,
                        false
                    )
            )
        }
    }

    override fun getItemCount() = vehicleList.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (UtilityObject.isRecyclerViewHeader(position - 1))
            TYPE_VEHICLE_STAT
        else
            TYPE_OTHER_ITEM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (UtilityObject.isRecyclerViewHeader(position - 1)) {
            (holder as VehicleStatViewHolder).bindView(
                vehicleItemClickCallback,
                vehicleList
            )
        } else {
            (holder as VehicleViewHolder).bindView(
                context,
                vehicleList[position - 1],
                vehicleItemClickCallback
            )
        }
    }

    class VehicleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mSingleVehicleLayout: ConstraintLayout = view.vehicleConstraintLayout!!

        private val mVehicleName = view.vehicleName
        private val mVehicleImage = view.vehicleImageView

        private val mVehicleSeat = view.vehicleSeat
        private val mVehicleTopSpeed = view.vehicleTopSpeed

        fun bindView(
            context: Context,
            vehicle: VehicleModelClass,
            vehicleItemClickCallback: VehicleItemClickCallback
        ) {

            mVehicleName.text = vehicle.vehicleName

            mVehicleSeat.text = "Seat Counts: ${vehicle.vehicleOccupants}"
            mVehicleTopSpeed.text = "Top Speed: ${vehicle.vehicleTopSpeed}"

            mSingleVehicleLayout.setOnClickListener {
                vehicleItemClickCallback.onItemClick(vehicle)
            }

            val drawableId = UtilityObject.getDrawableIdByName(context, vehicle.vehicleImageName)

            Picasso.get()
                .load(drawableId)
                .error(R.drawable.ic_image_error_placeholder)
                .into(mVehicleImage)

        }
    }

    class VehicleStatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val vehicleStatLayout = view.vehicleStatLayout
//        private val vehicleStatImageView = view.vehicleStatImageView

        fun bindView(
            vehicleItemClickCallback: VehicleItemClickCallback,
            vehicleList: List<VehicleModelClass>
        ) {
            vehicleStatLayout.setOnClickListener {
                vehicleItemClickCallback.onStatItemClick(vehicleList)
            }

//            vehicleStatImageView.setOnClickListener {
//                vehicleItemClickCallback.onStatClick(vehicleList)
//            }
        }
    }
}
package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapDropLocationModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_drop_location.view.*

class DropLocationRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<DropLocationRecyclerAdapter.DropLocationViewHolder>() {

    private var mapDropLocationData: List<MapDropLocationModelClass> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropLocationViewHolder {
        return DropLocationViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_single_drop_location, parent, false)
        )

    }

    fun setMapDropData(
        mapDropLocationData: List<MapDropLocationModelClass>
    ) {
        this.mapDropLocationData = mapDropLocationData
        notifyDataSetChanged()
    }

    override fun getItemCount() = mapDropLocationData.size

    override fun onBindViewHolder(holder: DropLocationViewHolder, position: Int) {
        holder.bindView(context, mapDropLocationData[position])
    }


    class DropLocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mMapLocationName = view.mapLocationName
        private val mMapLocationDescription = view.mapLocationDescriptionText
        private val mMapLocationPopularity = view.mapPopularityText

        //private val mMapExpectedEquipment = view.mapExpectedEquipmentText
        private val mMapVehicle = view.mapVehiclesText

        private val mMapDropLocationImageView = view.mapLocationImageView

        fun bindView(context: Context, dropLocation: MapDropLocationModelClass) {

            Picasso.get()
                .load(UtilityObject.getDrawableIdByName(context, dropLocation.mapImageName))
                .into(mMapDropLocationImageView)


            mMapLocationName.text = dropLocation.mapLocationName
            mMapLocationDescription.text = dropLocation.mapDescription

            mMapLocationPopularity.text = "Popularity: ${dropLocation.mapPopularity}"
            //mMapExpectedEquipment.text = "Expected Equipment: ${dropLocation.mapExpectedEquipment}"
            mMapVehicle.text = "Available Vehicles: ${dropLocation.mapVehicle}"

        }

    }

}
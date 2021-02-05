package com.np.kisanthapa.guideforpubgmobile.ui.map.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapModelClass
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_single_map.view.*
import kotlin.math.sin


class MapRecyclerAdapter(val context: Context) :
    RecyclerView.Adapter<MapRecyclerAdapter.MapViewHolder>() {

    //Interface for listening click
    private var mapItemClickCallback: MapItemClickCallback? = null

    interface MapItemClickCallback {
        fun onItemClick(mapId: Int, mapName: String)

        fun onMapInformationClick(mapId: Int, mapName: String)
        fun onMapDropLocationClick(mapId: Int, mapName: String)
        fun onMapOtherDetailClick(mapId: Int, mapName: String)
    }

    fun setOnMapItemClickListener(mapItemClickCallback: MapItemClickCallback) {
        this.mapItemClickCallback = mapItemClickCallback
    }

    private var mapDataList = emptyList<MapModelClass>()
    internal fun setMapData(mapData: List<MapModelClass>) {
        mapDataList = mapData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MapViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_single_map,
                parent,
                false
            )
        return MapViewHolder(view)

    }

    override fun getItemCount() = mapDataList.size

    override fun onBindViewHolder(holder: MapViewHolder, position: Int) {
        holder.bindView(
            context,
            mapDataList[position],
            mapItemClickCallback!!
        )
    }

    class MapViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var mMapViewLayout: ConstraintLayout = view.mapConstraintLayout!!

        private val mMapName = view.mapName

        //Other Details
        private var mMapInformationLayout: CardView = view.layoutMapInformation
        private val mMapInformationImageView: ImageView = view.mapDetailsMapInformation

        private var mMapPopularDropLocationLayout: CardView = view.layoutMapPopularDropLocation
        private val mMapPopularDropLocationImageView: ImageView = view.mapDetailsPopularDropLocation

        private var mMapOtherDetailsLayout: CardView = view.layoutMapOtherDetails
        private val mMapOtherDetailsImageView: ImageView = view.mapDetailsMapOtherDetails


        private val mMapImage = view.mapImageView

        fun bindView(
            context: Context,
            singleMapData: MapModelClass,
            mapItemClickCallback: MapItemClickCallback
        ) {

            mMapName.text = singleMapData.mapName

            val drawableId =
                UtilityObject.getDrawableIdByName(context, singleMapData.mapImageName)

            //Set image in main thumbnail
            Picasso.get()
                .load(drawableId)
                .error(R.drawable.ic_image_error_placeholder)
                .into(mMapImage)


            if (singleMapData.id == 5) {
                // Check visibility and make invisible
                if (mMapInformationLayout.isVisible)
                    mMapInformationLayout.visibility = View.GONE
                if (mMapPopularDropLocationLayout.isVisible)
                    mMapPopularDropLocationLayout.visibility = View.GONE
                if (mMapOtherDetailsLayout.isVisible)
                    mMapOtherDetailsLayout.visibility = View.GONE
            } else {
                //check invisibility and make visible
                if (!mMapInformationLayout.isVisible) mMapOtherDetailsLayout.visibility =
                    View.VISIBLE
                if (!mMapPopularDropLocationLayout.isVisible) mMapPopularDropLocationLayout.visibility =
                    View.VISIBLE
                if (!mMapOtherDetailsLayout.isVisible) mMapOtherDetailsLayout.visibility =
                    View.VISIBLE

                //Set image in map information thumbnail
                Picasso.get()
                    .load(
                        UtilityObject.getDrawableIdByName(
                            context,
                            singleMapData.mapImageDetailMapInformation
                        )
                    )
                    .error(R.drawable.ic_image_error_placeholder)
                    .into(mMapInformationImageView)

                //Set images in popular drop location
                Picasso.get()
                    .load(
                        UtilityObject.getDrawableIdByName(
                            context,
                            singleMapData.mapImageDetailPopularDropLocation
                        )
                    )
                    .error(R.drawable.ic_image_error_placeholder)
                    .into(mMapPopularDropLocationImageView)

                //Set image in other details information
                Picasso.get()
                    .load(
                        UtilityObject.getDrawableIdByName(
                            context,
                            singleMapData.mapImageDetailOtherDetails
                        )
                    )
                    .error(R.drawable.ic_image_error_placeholder)
                    .into(mMapOtherDetailsImageView)
            }

            mMapViewLayout.setOnClickListener {
                mapItemClickCallback.onItemClick(singleMapData.id, singleMapData.mapName)
            }


            //Map Details Click handled here
            mMapInformationLayout.setOnClickListener {
                mapItemClickCallback.onMapInformationClick(singleMapData.id, singleMapData.mapName)
            }

            mMapPopularDropLocationLayout.setOnClickListener {
                mapItemClickCallback.onMapDropLocationClick(singleMapData.id, singleMapData.mapName)
            }

            mMapOtherDetailsLayout.setOnClickListener {
                mapItemClickCallback.onMapOtherDetailClick(singleMapData.id, singleMapData.mapName)
            }

        }

    }

}
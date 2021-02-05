package com.np.kisanthapa.guideforpubgmobile.ui.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject
import com.squareup.picasso.Picasso
import com.stfalcon.imageviewer.StfalconImageViewer
import ir.androidexception.datatable.DataTable
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow

class DetailsVehicleFragment : Fragment() {

    private lateinit var mVehicleImageCardView: CardView
    private lateinit var mVehicleImageView: ImageView
    private lateinit var mVehicleName: TextView
    private lateinit var mVehicleFeature: TextView
    private lateinit var mVehicleSummary: TextView
    private lateinit var mVehicleStatTable: DataTable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_details_vehicle, container, false)

        mVehicleImageCardView = root.findViewById(R.id.mVehicleImageCardView)
        mVehicleImageView = root.findViewById(R.id.mVehicleImageView)
        mVehicleName = root.findViewById(R.id.mVehicleName)
        mVehicleFeature = root.findViewById(R.id.mVehicleFeature)
        mVehicleSummary = root.findViewById(R.id.mVehicleSummary)
        mVehicleStatTable = root.findViewById(R.id.mVehicleStatTable)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting argument
        val vehicleData = DetailsVehicleFragmentArgs.fromBundle(requireArguments())
            .vehicleDataArgs

        mVehicleName.text = vehicleData.vehicleName
        mVehicleSummary.text = vehicleData.vehicleSummary
        mVehicleFeature.text = vehicleData.vehicleFeature

        //Set Data in Views
        val drawableId = UtilityObject.getDrawableIdByName(
            requireContext(),
            vehicleData.vehicleImageName
        )
        Picasso.get()
            .load(drawableId)
            .error(drawableId)
            .into(mVehicleImageView)

        // Image Preview
        mVehicleImageCardView.setOnClickListener {
            StfalconImageViewer.Builder(
                context,
                listOf(drawableId)
            ) { view, image ->
                Picasso.get().load(image).into(view)
            }.withTransitionFrom(mVehicleImageView)
                .withHiddenStatusBar(true)
                .build()
                .show(true)
        }


        //Stat Data table builder
        setStatsInDataTable(vehicleData)

    }

    private fun setStatsInDataTable(vehicleData: VehicleModelClassParcelable) {
        // Set table header here
        val tableHeader: DataTableHeader = DataTableHeader.Builder()
            .item("Occupants (Seats)", 8)
            .item("Health", 8)
            .item("Top Speed", 8)
            .item("Type", 8)
            .item("Appears In", 8)
            .build()

        val row1 = DataTableRow.Builder()
            .value(vehicleData.vehicleOccupants.toString())
            .value(vehicleData.vehicleHealth.toString())
            .value(vehicleData.vehicleTopSpeed)
            .value(vehicleData.vehicleType)
            .value(vehicleData.vehicleAppearsIn)

            .build()

        val dataRow = arrayListOf<DataTableRow>()
        dataRow.add(row1)

        mVehicleStatTable.header = tableHeader
        mVehicleStatTable.rows = dataRow
        mVehicleStatTable.inflate(requireContext())
    }
}

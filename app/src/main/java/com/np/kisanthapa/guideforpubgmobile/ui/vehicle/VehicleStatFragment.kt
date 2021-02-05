package com.np.kisanthapa.guideforpubgmobile.ui.vehicle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClassParcelable
import ir.androidexception.datatable.DataTable
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow


class VehicleStatFragment : Fragment() {


    private lateinit var mAllVehicleStatTable: DataTable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_vehicle_stat, container, false)

        mAllVehicleStatTable = root.findViewById(R.id.mAllVehicleStatTable)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val vehicleListData =
            VehicleStatFragmentArgs.fromBundle(requireArguments()).vehicleDataListArgs

        // Data Table
        setStatsInDataTable(vehicleListData)
    }


    private fun setStatsInDataTable(vehicleDataList: Array<VehicleModelClassParcelable>) {

        // Set table header here
        val tableHeader: DataTableHeader = DataTableHeader.Builder()
            .item("Vehicle Name", 8)
            .item("Occupants (Seats)", 8)
            .item("Top Speed", 8)
            .item("Type", 8)
            .item("Health", 8)
            .item("Appears In", 8)
            .build()

        val dataRow = arrayListOf<DataTableRow>()

        for (vehicleData in vehicleDataList) {

            val row = DataTableRow.Builder()
                .value(vehicleData.vehicleName)
                .value(vehicleData.vehicleOccupants.toString())
                .value(vehicleData.vehicleTopSpeed)
                .value(vehicleData.vehicleType)
                .value(vehicleData.vehicleHealth.toString())
                .value(vehicleData.vehicleAppearsIn)
                .build()

            dataRow.add(row)
        }

        mAllVehicleStatTable.header = tableHeader
        mAllVehicleStatTable.rows = dataRow
        mAllVehicleStatTable.inflate(requireContext())
    }


}

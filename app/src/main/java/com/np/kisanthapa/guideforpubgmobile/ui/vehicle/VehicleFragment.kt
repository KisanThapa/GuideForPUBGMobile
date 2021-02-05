package com.np.kisanthapa.guideforpubgmobile.ui.vehicle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.adapter.VehicleRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClassParcelable
import com.np.kisanthapa.guideforpubgmobile.utility.UtilityObject

class VehicleFragment : Fragment() {

    companion object {
        fun newInstance() = VehicleFragment()
    }

    private lateinit var vehicleViewModel: VehicleViewModel

    //Recycler View
    private lateinit var mVehicleRecyclerView: RecyclerView
    private lateinit var mVehicleRecyclerAdapter: VehicleRecyclerAdapter

    private lateinit var mNavController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.vehicle_fragment, container, false)

        vehicleViewModel = ViewModelProvider(this).get(VehicleViewModel::class.java)

        // Init recycler view
        mVehicleRecyclerView = root.findViewById(R.id.mVehicleRecyclerView)

        //Initialize recycler view and its design here
        initRecyclerView()


        //Observed data here
        vehicleViewModel.vehicleList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mVehicleRecyclerAdapter.setVehicleData(it)
            }
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)
    }

    private fun initRecyclerView() {

        //Init Adapter here
        mVehicleRecyclerAdapter = VehicleRecyclerAdapter(requireContext())

        // Define view layout of recycler view here
        val viewManager = GridLayoutManager(requireContext(), 2)
        viewManager.spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (UtilityObject.isRecyclerViewHeader(position - 1)) 2
                    else 1
                }
            }

        //Set other parameters of recycler view here
        mVehicleRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager which is defined above
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mVehicleRecyclerAdapter
        }

        // Handle actions after user clicked the single item here
        mVehicleRecyclerAdapter.onSetVehicleItemClickListener(object :
            VehicleRecyclerAdapter.VehicleItemClickCallback {
            override fun onItemClick(vehicleModelClass: VehicleModelClass) {

                val vehicleModelParcelable = VehicleModelClassParcelable(
                    vehicleModelClass.vehicleId,
                    vehicleModelClass.vehicleName,
                    vehicleModelClass.vehicleSummary,
                    vehicleModelClass.vehicleFeature,
                    vehicleModelClass.vehicleOccupants,
                    vehicleModelClass.vehicleHealth,
                    vehicleModelClass.vehicleTopSpeed,
                    vehicleModelClass.vehicleType,
                    vehicleModelClass.vehicleAppearsIn,
                    vehicleModelClass.vehicleImageName,
                    vehicleModelClass.vehicleIconImageName
                )

                val action = VehicleFragmentDirections.actionNavVehicleToDetailsVehicleFragment(
                    vehicleModelParcelable,
                    vehicleModelClass.vehicleName
                )
                mNavController.navigate(action)

            }

            override fun onStatItemClick(vehicleDataList: List<VehicleModelClass>) {
                val listVehicleDataList = mutableListOf<VehicleModelClassParcelable>()

                for (vehicleData in vehicleDataList) {
                    val vData = VehicleModelClassParcelable(
                        vehicleData.vehicleId,
                        vehicleData.vehicleName,
                        vehicleData.vehicleSummary,
                        vehicleData.vehicleFeature,
                        vehicleData.vehicleOccupants,
                        vehicleData.vehicleHealth,
                        vehicleData.vehicleTopSpeed,
                        vehicleData.vehicleType,
                        vehicleData.vehicleAppearsIn,
                        vehicleData.vehicleImageName,
                        vehicleData.vehicleIconImageName
                    )
                    listVehicleDataList.add(vData)
                }

                val action = VehicleFragmentDirections.actionNavVehicleToVehicleStatFragment(
                    listVehicleDataList.toTypedArray(),
                    "Vehicle Statistics"
                )
                mNavController.navigate(action)

            }

        })

    }

}

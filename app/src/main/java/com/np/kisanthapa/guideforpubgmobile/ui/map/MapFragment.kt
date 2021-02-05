package com.np.kisanthapa.guideforpubgmobile.ui.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.map.adapter.MapRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapModelClass

class MapFragment : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var mapViewModel: MapViewModel

    //Recycler view
    private lateinit var mMapRecyclerView: RecyclerView

    private lateinit var mMapRecyclerAdapter: MapRecyclerAdapter

    // Nav Controller for navigation to another activity
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.map_fragment, container, false)

        mapViewModel = ViewModelProvider(this).get(MapViewModel::class.java)

        //Recycler View
        mMapRecyclerView = root.findViewById(R.id.mMapRecyclerView)

        // Init Recycler view
        initRecyclerView()


        mapViewModel.mapDataList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mMapRecyclerAdapter.setMapData(it)
            }
        })

        return root
    }

    private fun initRecyclerView() {
        // Define view layout of recycler view here
        val viewManager = LinearLayoutManager(requireContext())
        //Init Adapter here
        mMapRecyclerAdapter = MapRecyclerAdapter(requireContext())
        //Set other parameters of recycler view here
        mMapRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager which is defined above
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mMapRecyclerAdapter
        }


        // Handle actions after user clicked the single item here
        mMapRecyclerAdapter.setOnMapItemClickListener(object :
            MapRecyclerAdapter.MapItemClickCallback {
            override fun onItemClick(mapId: Int, mapName: String) {
                mNavController.navigate(
                    MapFragmentDirections.actionNavMapToMapDetailsFragment(
                        mapId,
                        mapName
                    )
                )

            }

            override fun onMapInformationClick(mapId: Int, mapName: String) {
                mNavController.navigate(
                    MapFragmentDirections
                        .actionNavMapToMapInformationFragment(mapId, "$mapName - Map Information")
                )
            }

            override fun onMapDropLocationClick(mapId: Int, mapName: String) {
                mNavController.navigate(
                    MapFragmentDirections.actionNavMapToPopularDropFragment(
                        mapId,
                        "$mapName - Popular Hot Drops"
                    )
                )
            }

            override fun onMapOtherDetailClick(mapId: Int, mapName: String) {
                Toast.makeText(
                    requireContext(),
                    "Map Id: $mapId  Map Name: $mapName",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Init mNavController
        mNavController = Navigation.findNavController(view)

    }

}

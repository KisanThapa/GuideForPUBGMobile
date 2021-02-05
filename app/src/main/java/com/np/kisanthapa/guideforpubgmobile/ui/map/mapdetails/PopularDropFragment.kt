package com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.adapter.DropLocationRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel.PopularDropViewModel
import com.np.kisanthapa.guideforpubgmobile.ui.map.mapdetails.viewmodel.PopularDropViewModelFactory

class PopularDropFragment : Fragment() {

    companion object {
        fun newInstance() = PopularDropFragment()
    }

    private lateinit var viewModel: PopularDropViewModel

    //Recycler View
    private lateinit var mMapDropLocationRecyclerView: RecyclerView

    //Adapter
    private lateinit var dropLocationAdapter: DropLocationRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.popular_drop_fragment, container, false)

        // Init Recycler view
        mMapDropLocationRecyclerView = root.findViewById(R.id.mMapDropLocationRecyclerView)

        initRecyclerView()

        return root

    }

    private fun initRecyclerView() {

        // View manager
        val viewManager = LinearLayoutManager(requireContext())

        // Adapter
        dropLocationAdapter = DropLocationRecyclerAdapter(requireContext())

        mMapDropLocationRecyclerView.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = dropLocationAdapter

        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val mapId = PopularDropFragmentArgs.fromBundle(requireArguments()).mapIdArgs

        // View model init here
        viewModel = ViewModelProvider(
            this,
            PopularDropViewModelFactory(
                requireActivity().application,
                mapId
            )
        ).get(PopularDropViewModel::class.java)


        // Observe data here
        viewModel.dropLocation.observe(viewLifecycleOwner, Observer {
            it?.let {
                dropLocationAdapter.setMapDropData(it)
            }
        })

    }

}

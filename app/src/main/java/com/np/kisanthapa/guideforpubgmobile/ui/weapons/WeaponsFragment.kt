package com.np.kisanthapa.guideforpubgmobile.ui.weapons


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.weapons.adapter.WeaponRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.weapons.model.WeaponModelClass

class WeaponsFragment : Fragment() {

    private lateinit var weaponsViewModel: WeaponsViewModel

    // Recycler View
    private lateinit var mWeaponRecyclerView: RecyclerView

    // Recycler View adapter
    private lateinit var weaponRecyclerAdapter: WeaponRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.weapons_fragment, container, false)

        mWeaponRecyclerView = root.findViewById(R.id.mWeaponRecyclerView)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Init View Model here
        weaponsViewModel = ViewModelProvider(this).get(WeaponsViewModel::class.java)

        // Init Recycler view adapter
        weaponRecyclerAdapter = WeaponRecyclerAdapter(requireContext())

        initRecyclerView()

        // Observe view model data here
        weaponsViewModel.weaponTypeList.observe(viewLifecycleOwner, Observer {
            it?.let {
                weaponRecyclerAdapter.setWeaponData(it)
            }
        })

    }

    private fun initRecyclerView() {

        val viewManager = GridLayoutManager(requireContext(), 2)

        mWeaponRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = weaponRecyclerAdapter
        }

        // Click listener
        weaponRecyclerAdapter.setOnWeaponItemClickListener(object :
            WeaponRecyclerAdapter.WeaponItemClickCallback {

            override fun onWeaponItemClick(weaponData: WeaponModelClass) {
                Log.i("RRR", "Data: $weaponData")
            }

        })

    }

}

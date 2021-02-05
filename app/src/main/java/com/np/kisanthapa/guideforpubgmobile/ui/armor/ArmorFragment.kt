package com.np.kisanthapa.guideforpubgmobile.ui.armor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.np.kisanthapa.guideforpubgmobile.R
import com.np.kisanthapa.guideforpubgmobile.ui.armor.adapter.ArmorRecyclerAdapter
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClassParcelable

class ArmorFragment : Fragment() {

    companion object {
        fun newInstance() = ArmorFragment()
    }

    //Declare View Model
    private lateinit var armorViewModel: ArmorViewModel

    private lateinit var mArmorRecyclerAdapter: ArmorRecyclerAdapter

    //Recycler view for Armor list
    private lateinit var mArmorRecyclerView: RecyclerView

    // Navigation Controller for further Navigation
    // and passing arguments to further
    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.armor_fragment, container, false)

        //Init view Model
        armorViewModel = ViewModelProvider(this).get(ArmorViewModel::class.java)


        //Init recycler view here
        mArmorRecyclerView = root.findViewById(R.id.mArmorRecyclerView)

        //Initialize recycler view and its design here
        initRecyclerView()

        //Observe armorList in View Model
        armorViewModel.armorList.observe(viewLifecycleOwner, Observer {
            it?.let {
                mArmorRecyclerAdapter.setArmorData(it)
            }
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavController = Navigation.findNavController(view)

    }

    private fun initRecyclerView() {

        // Define view layout of recycler view here
        val viewManager = GridLayoutManager(requireContext(), 2)

        //Init Adapter here
        mArmorRecyclerAdapter = ArmorRecyclerAdapter(requireContext())

        //Set other parameters of recycler view here
        mArmorRecyclerView.apply {
            setHasFixedSize(true)
            // use a grid layout manager which is defined above
            layoutManager = viewManager
            // specify an viewAdapter
            adapter = mArmorRecyclerAdapter
        }

        // Handle actions after user clicked the single item here
        mArmorRecyclerAdapter.setOnArmorItemClickListener(object :
            ArmorRecyclerAdapter.ArmorItemClickCallback {
            override fun onItemClick(armorModelClass: ArmorModelClass) {

                val armorMParcelable = ArmorModelClassParcelable(
                    armorModelClass.armorId,
                    armorModelClass.armorName,
                    armorModelClass.armorFullName,
                    armorModelClass.armorLevel,
                    armorModelClass.armorDescription,
                    armorModelClass.armorCapacityDescription,
                    armorModelClass.armorCapacityExtension,
                    armorModelClass.armorDamageReduction,
                    armorModelClass.armorDurability,
                    armorModelClass.armorImageName
                )

                val action = ArmorFragmentDirections.actionNavArmorToDetailsArmorFragment(
                    armorMParcelable,
                    armorModelClass.armorName
                )

                mNavController.navigate(action)
            }

        })
    }

}

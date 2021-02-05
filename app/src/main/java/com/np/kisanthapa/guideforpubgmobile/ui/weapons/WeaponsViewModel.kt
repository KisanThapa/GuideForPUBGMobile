package com.np.kisanthapa.guideforpubgmobile.ui.weapons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.np.kisanthapa.guideforpubgmobile.ui.weapons.model.WeaponModelClass

class WeaponsViewModel : ViewModel() {

    private val _weaponTypeList = MutableLiveData<List<WeaponModelClass>>().apply {
        value = getWeaponTypeData()
    }

    val weaponTypeList: LiveData<List<WeaponModelClass>> = _weaponTypeList

    private fun getWeaponTypeData(): List<WeaponModelClass>? {

        return listOf(
            WeaponModelClass(
                1,
                "Assault Rifle(AR)",
                "M416, AKM, AUG3, Groza, SCARL, etc.",
                "item_weapon_ar"
            ), WeaponModelClass(
                2,
                "Sniper(Bolt Action)",
                "AWM, KAR 98K, M24, etc.",
                "item_weapon_sniper"
            ), WeaponModelClass(
                3,
                "Designated Marksman Rifle(DMR)",
                "Mk14, Mini14, VSS, etc.",
                "item_weapon_dmr"
            ), WeaponModelClass(
                4,
                "Light Machine Gun(LMG)",
                "M249 and DP-28",
                "item_weapon_lmg"
            ), WeaponModelClass(
                5,
                "Submachine Gun(SMG)",
                "UMP45, Vector, UZI, etc.",
                "item_weapon_smg"
            ), WeaponModelClass(
                6,
                "Shot Gun",
                "S12K, S1897, S686, etc.",
                "item_weapon_shotgun"
            ), WeaponModelClass(
                7,
                "Pistol",
                "p18C, P92, Flare Gun, etc.",
                "item_weapon_pistol"
            ), WeaponModelClass(
                8,
                "Melee",
                "Pan, Sickle, Crowbar, etc.",
                "item_weapon_melee"
            ), WeaponModelClass(
                9,
                "Miscellaneous",
                "Crossbow",
                "item_weapon_crossbow"
            )
        )

    }

}

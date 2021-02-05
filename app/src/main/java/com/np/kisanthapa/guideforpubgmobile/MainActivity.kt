package com.np.kisanthapa.guideforpubgmobile

import android.os.Bundle
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.np.kisanthapa.guideforpubgmobile.animate.ViewAnimation

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    //Fab rotation
    var isRotate: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //Floating action button
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->

            isRotate = ViewAnimation.rotateFab(view, !isRotate)

        }

        //Drawer Layout
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //This is applicable for ktx artifacts
        appBarConfiguration =
            AppBarConfiguration.Builder(
                mutableSetOf(
                    R.id.nav_home,
                    R.id.nav_weapons,
                    R.id.nav_map,
                    R.id.nav_vehicle,
                    R.id.nav_attachment,
                    R.id.nav_ammunition,
                    R.id.nav_consumable,
                    R.id.nav_throwable,
                    R.id.nav_armor,
                    R.id.nav_helmet,
                    R.id.nav_backpack
                    /*,R.id.nav_gallery,
                    * R.id.nav_slideshow*/
                )
            )
                .setDrawerLayout(drawerLayout)
                .build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

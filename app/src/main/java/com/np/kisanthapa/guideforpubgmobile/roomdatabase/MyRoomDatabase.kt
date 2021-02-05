package com.np.kisanthapa.guideforpubgmobile.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.database.AmmunitionDao
import com.np.kisanthapa.guideforpubgmobile.ui.ammunition.model.AmmunitionModel
import com.np.kisanthapa.guideforpubgmobile.ui.armor.database.ArmorDao
import com.np.kisanthapa.guideforpubgmobile.ui.armor.model.ArmorModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.database.GripDao
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.grip.model.GripModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.database.MagazineDao
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.magazine.model.MagazineModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.database.MuzzleDao
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.muzzle.model.MuzzleModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.database.ScopeDao
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.scope.model.ScopeModel
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.database.StockDao
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModel
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.database.BackPackDao
import com.np.kisanthapa.guideforpubgmobile.ui.backpack.model.BackPackModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.database.ConsumableDao
import com.np.kisanthapa.guideforpubgmobile.ui.consumable.model.ConsumableModel
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.database.HelmetDao
import com.np.kisanthapa.guideforpubgmobile.ui.helmet.model.HelmetModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.map.database.MapDao
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapDropLocationModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.map.model.MapInfoModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.database.ThrowableDao
import com.np.kisanthapa.guideforpubgmobile.ui.throwable.model.ThrowableModelClass
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.database.VehicleDao
import com.np.kisanthapa.guideforpubgmobile.ui.vehicle.model.VehicleModelClass

@Database(
    entities = [
        BackPackModelClass::class,
        HelmetModelClass::class,
        ArmorModelClass::class,
        ThrowableModelClass::class,
        ConsumableModel::class,
        VehicleModelClass::class,
        MapInfoModelClass::class,
        MapDropLocationModelClass::class,
        AmmunitionModel::class,
        ScopeModel::class,
        MagazineModel::class,
        MuzzleModel::class,
        GripModel::class,
        StockModel::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun backPackDao(): BackPackDao
    abstract fun helmetDao(): HelmetDao
    abstract fun armorDao(): ArmorDao
    abstract fun throwableDao(): ThrowableDao
    abstract fun consumableDao(): ConsumableDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun mapDao(): MapDao
    abstract fun ammunitionDao(): AmmunitionDao
    abstract fun scopeDao(): ScopeDao
    abstract fun magazineDao(): MagazineDao
    abstract fun muzzleDao(): MuzzleDao
    abstract fun gripDao(): GripDao
    abstract fun stockDao(): StockDao

    // Singleton prevents multiple instances of database opening at the
    // same time.
    companion object {

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null

        fun getDatabase(context: Context): MyRoomDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "pubg_guide_database"
                )
                    .createFromAsset("database/pubg_guide_database.db")
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
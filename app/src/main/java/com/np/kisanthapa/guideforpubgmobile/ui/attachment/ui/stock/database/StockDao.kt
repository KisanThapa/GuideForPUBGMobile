package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModel

@Dao
interface StockDao {

    @Query("SELECT * FROM table_stock")
    fun getAllStockData(): LiveData<List<StockModel>>

}
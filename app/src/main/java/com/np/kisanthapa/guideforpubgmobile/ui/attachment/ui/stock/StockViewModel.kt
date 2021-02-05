package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.roomdatabase.MyRoomDatabase
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.database.StockRepository
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModel

class StockViewModel(application: Application) : AndroidViewModel(application) {

    // This value will be observed
    val stockList: LiveData<List<StockModel>>

    //Declare Repository
    private val mStockRepository: StockRepository

    init {
        val stockDao = MyRoomDatabase.getDatabase(application).stockDao()
        mStockRepository = StockRepository(stockDao)
        stockList = mStockRepository.stockDataList
    }

}
package com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.database

import androidx.lifecycle.LiveData
import com.np.kisanthapa.guideforpubgmobile.ui.attachment.ui.stock.model.StockModel

class StockRepository(private val stockDao: StockDao) {
    val stockDataList: LiveData<List<StockModel>> = stockDao.getAllStockData()
}

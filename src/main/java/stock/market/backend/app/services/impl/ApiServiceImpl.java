package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.dto.StockDto;

import java.util.ArrayList;

public interface ApiServiceImpl {

    StockDto findStockInApi(String nameStock);

    ArrayList<String> findHistoryInApi(String secId, String from, String till);
}

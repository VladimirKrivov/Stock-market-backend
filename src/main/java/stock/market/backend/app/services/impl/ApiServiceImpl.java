package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.Stocks;

import java.util.ArrayList;

public interface ApiServiceImpl {

    StockDto findStock(String nameStock);

    StockDto findStockInApi(String nameStock);

    Stocks findStockEntity(String nameStock);

    HistoryDto getHistory(String company, String from, String till);

    ArrayList<String> findHistoryInApi(String secId, String from, String till);
}

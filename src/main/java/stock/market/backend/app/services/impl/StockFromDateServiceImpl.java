package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.entity.StockFromDate;

import java.util.List;

public interface StockFromDateServiceImpl {
    List<StockFromDate> getHistory(String company, String username, String from, String till);
}

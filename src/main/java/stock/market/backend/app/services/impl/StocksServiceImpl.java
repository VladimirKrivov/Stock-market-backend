package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.Stocks;

public interface StocksServiceImpl {
    StockDto findStock(String nameStock);
    Stocks findStockEntity(String nameStock);

}

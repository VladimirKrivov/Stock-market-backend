package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.Stocks;

import java.util.List;

public interface StocksServiceImpl {
    StockDto findStock(String nameStock, String nameUser);
    Stocks findStockEntity(String nameStock, String username);
    void deleteForUser(String name, String secid);
    List<StockDto> findAllStockForUser(String username);

}

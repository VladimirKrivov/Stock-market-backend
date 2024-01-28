package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.dto.ShortHistoryDto;
import stock.market.backend.app.models.dto.StockFromDateListDto;

public interface HistoryServiceImpl {
    StockFromDateListDto calcPricesStocks(ShortHistoryDto dto);
}

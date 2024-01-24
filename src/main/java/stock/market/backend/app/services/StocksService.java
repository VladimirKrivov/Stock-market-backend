package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.Stocks;
import stock.market.backend.app.repositories.StockRepositories;
import stock.market.backend.app.services.impl.StocksServiceImpl;
import stock.market.backend.app.util.Mapper;

@Service
@AllArgsConstructor
public class StocksService implements StocksServiceImpl {

    private final ApiService apiService;
    private final StockRepositories stockRepositories;
    private final Mapper mapper;


    @Override
    public StockDto findStock(String nameStock) {
        StockDto stockDto = apiService.findStockInApi(nameStock);
        Stocks stock = stockRepositories.findBySecId(stockDto.getSecId());
        StockDto newDto;
        if (stock == null) {
            return mapper.stockToStockDto(stockRepositories
                    .save(mapper.stocksDtoToStock(stockDto)));
        } else {
            newDto = mapper.stockToStockDto(stock);
        }
        return newDto;
    }

    @Override
    public Stocks findStockEntity(String nameStock) {
        StockDto stockDto = apiService.findStockInApi(nameStock);
        Stocks stock = stockRepositories.findBySecId(stockDto.getSecId());
        if (stock == null) {
            return stockRepositories
                    .save(mapper.stocksDtoToStock(stockDto));
        } else {
            return stock;
        }
    }

}

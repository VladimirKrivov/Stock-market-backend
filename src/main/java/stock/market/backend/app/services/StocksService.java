package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.Stocks;
import stock.market.backend.app.models.entity.User;
import stock.market.backend.app.repositories.StockRepositories;
import stock.market.backend.app.repositories.UserRepositories;
import stock.market.backend.app.services.impl.StocksServiceImpl;
import stock.market.backend.app.util.Mapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StocksService implements StocksServiceImpl {

    private final ApiService apiService;
    private final UserRepositories userRepositories;
    private final StockRepositories stockRepositories;
    private final Mapper mapper;


    // Найти акцию по имени пользователя
    @Override
    public StockDto findStock(String nameStock, String nameUser) {
        StockDto stockDto = apiService.findStockInApi(nameStock);

        User user = userRepositories.findUsersByName(nameUser);

        try {
            if (user == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            log.info("User name: {} not found", nameUser);
        }

        Stocks stock = stockRepositories.findBySecIdAndUsers(stockDto.getSecId(), user);
        StockDto newDto;

        if (stock == null) {
            Stocks newStock = stockRepositories.save(mapper.stocksDtoToStock(stockDto));

            newStock.getUsers().add(user);

            user.getStocks().add(newStock);

            userRepositories.save(user);
            Stocks stocks2 = stockRepositories.save(newStock);
            return mapper.stockToStockDto(stocks2);
        } else {
            newDto = mapper.stockToStockDto(stock);
        }
        return newDto;
    }


    // Найти в базе или обратится к api и найти акцию по имени
    @Override
    public Stocks findStockEntity(String nameStock, String username) {
        StockDto stockDto = apiService.findStockInApi(nameStock);

        User user = userRepositories.findUsersByName(username);

        try {
            if (user == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            log.info("User name: {} not found", username);
        }

        Stocks stock = stockRepositories.findBySecIdAndUsers(stockDto.getSecId(), user);


        // Вот тут не работает
        if (stock == null) {
            return stockRepositories
                    .save(mapper.stocksDtoToStock(stockDto));
        } else {
            return stock;
        }
    }

    // Получить все акции из портфеля конкретного пользователя
    @Override
    public List<StockDto> findAllStockForUser(String username) {
        List<Stocks> stocks = new ArrayList<>();
        List<StockDto> stockDtoList = new ArrayList<>();

        User user = userRepositories.findUsersByName(username);

        try {
            if (user == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            log.info("User name: {} not found", username);
        }

        stocks = stockRepositories.findAllByUsers(user);

        for (Stocks elem : stocks) {
            StockDto dto = new StockDto();

            stockDtoList.add(mapper.stockToStockDto(elem));
        }


        return stockDtoList;
    }

    // Удалить акцию из портфеля у конкретного пользователя
    @Override
    @Transactional
    public void deleteForUser(String name, String secid) {
//        Поиск пользователя
        User user = userRepositories.findUsersByName(name);

        try {
            if (user == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            log.info("User name: {} not found", name);
        }

//        Stocks stock = user.getStocks().stream()
//                .filter(s -> s.getId().equals(secid))
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("Stock not found"));

        List<Stocks> stocks = user.getStocks();
        Stocks stock = null;

        for (Stocks elem : stocks) {
            if (elem.getSecId().equals(secid)) {
                stock = elem;
            }
        }



        user.getStocks().remove(stock);

        userRepositories.save(user);
//        stockRepositories.deleteBySecIdAndUsers(secid, user);

    }
}

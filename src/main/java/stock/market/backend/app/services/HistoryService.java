package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.repositories.HistoryRepository;
import stock.market.backend.app.repositories.StockRepositories;
import stock.market.backend.app.services.impl.HistoryServiceImpl;
import stock.market.backend.app.util.Mapper;
import stock.market.backend.app.util.Parser;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService implements HistoryServiceImpl {

    private final StockRepositories stockRepositories;
    private final HistoryRepository historyRepository;
    private final Parser parser;
    private final Mapper mapper;




}

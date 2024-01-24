package stock.market.backend.app.services.impl;

import stock.market.backend.app.models.dto.HistoryDto;

import java.util.List;

public interface HistoryServiceImpl {
    List<HistoryDto> getHistory(String company, String from, String till);
}

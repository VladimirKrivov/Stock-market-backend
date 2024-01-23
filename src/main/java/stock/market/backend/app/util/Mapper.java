package stock.market.backend.app.util;

import org.springframework.stereotype.Component;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.History;
import stock.market.backend.app.models.entity.Stocks;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    public Stocks stocksDtoToStock(StockDto dto) {
        Stocks entity = new Stocks();

        entity.setSecId(dto.getSecId());
        entity.setShortname(dto.getShortname());
        entity.setRegNumber(dto.getRegNumber());
        entity.setName(dto.getName());
        entity.setIsin(dto.getIsin());
        entity.setEmitEntTitle(dto.getEmitEntTitle());

        return entity;
    }

    public StockDto stockToStockDto(Stocks entity) {
        StockDto dto = new StockDto();

        dto.setSecId(entity.getSecId());
        dto.setShortname(entity.getShortname());
        dto.setRegNumber(entity.getRegNumber());
        dto.setName(entity.getName());
        dto.setIsin(entity.getIsin());
        dto.setEmitEntTitle(entity.getEmitEntTitle());

        return dto;
    }


    public History historyDtoToHistory(HistoryDto dto) {
        History entity = new History();

        entity.setTradeDate(dto.getTradeDate());
        entity.setShortName(dto.getShortName());
        entity.setSecId(dto.getSecId());
        entity.setValue(dto.getValue());
        entity.setOpen(dto.getOpen());
        entity.setLow(dto.getLow());
        entity.setHigh(dto.getHigh());
        entity.setClose(dto.getClose());
        entity.setVolume(dto.getVolume());

        return entity;
    }


    public List<History> listHistoryDtoToListHistory(List<HistoryDto> listDto) {

        List<History> histories = new ArrayList<>();

        for (HistoryDto dto : listDto) {
            History entity = new History();

            entity.setTradeDate(dto.getTradeDate());
            entity.setShortName(dto.getShortName());
            entity.setSecId(dto.getSecId());
            entity.setValue(dto.getValue());
            entity.setOpen(dto.getOpen());
            entity.setLow(dto.getLow());
            entity.setHigh(dto.getHigh());
            entity.setClose(dto.getClose());
            entity.setVolume(dto.getVolume());

            histories.add(entity);
        }

        return histories;
    }

    public List<HistoryDto> listHistoryToHistoryDto(List<History> listHistory) {

        List<HistoryDto> dtoHistory = new ArrayList<>();

        for (History history : listHistory) {
            HistoryDto dto = new HistoryDto();

            dto.setTradeDate(history.getTradeDate());
            dto.setShortName(history.getShortName());
            dto.setSecId(history.getSecId());
            dto.setValue(history.getValue());
            dto.setOpen(history.getOpen());
            dto.setLow(history.getLow());
            dto.setHigh(history.getHigh());
            dto.setClose(history.getClose());
            dto.setVolume(history.getVolume());
            dtoHistory.add(dto);
        }

        return dtoHistory;
    }
}

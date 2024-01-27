package stock.market.backend.app.util;

import org.springframework.stereotype.Component;
import stock.market.backend.app.models.dto.*;
import stock.market.backend.app.models.entity.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    // Stocks
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

    // StockFromDateDtoToStockFromDate
    public StockFromDate StockFromDateDtoToStockFromDate(StockFromDateDto dto) {
        StockFromDate entity = new StockFromDate();

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

    // Не забыть удалить
    public List<StockFromDate> listHistoryDtoToListHistory(List<StockFromDateDto> listDto) {

        List<StockFromDate> histories = new ArrayList<>();

        for (StockFromDateDto dto : listDto) {
            StockFromDate entity = new StockFromDate();

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


    public List<StockFromDateDto> listHistoryToHistoryDto(List<StockFromDate> listHistory) {

        List<StockFromDateDto> dtoHistory = new ArrayList<>();

        for (StockFromDate history : listHistory) {
            StockFromDateDto dto = new StockFromDateDto();

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

    // Users
    public User userDtoToUser(UserDto dto) {
        User entity = new User();

        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());

        return entity;
    }

    public UserDto userToUserDto(User entity) {
        UserDto dto = new UserDto();

        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());

        return dto;
    }

    public HistoryElemDto historyElemToHistoryElemDto(HistoryElem entity) {
        HistoryElemDto dto = new HistoryElemDto();

        dto.setDate(String.valueOf(entity.getDate()));
        dto.setShortName(entity.getShortName());
        dto.setGrowth(entity.getGrowth());
        return dto;
    }



    public HistoryDto historyToHistoryDto(History entity) {
        HistoryDto dto = new HistoryDto();

        dto.setUserName(entity.getUser().getName());
        dto.setFrom(String.valueOf(entity.getFrom()));
        dto.setTill(String.valueOf(entity.getTill()));

        List<HistoryElem> elemList = entity.getHistoryElem();



    }
}

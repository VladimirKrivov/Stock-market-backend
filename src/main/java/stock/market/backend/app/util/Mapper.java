package stock.market.backend.app.util;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import stock.market.backend.app.models.dto.*;
import stock.market.backend.app.models.entity.*;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Mapper {

    private final Parser parser;
    private final PasswordEncoder passwordEncoder;

    /**
     Метод преобразует Dto акции в сущность.
     @param dto, dto акции.
     @return объект Stocks, является сущностью */
    public Stocks stocksDtoToStock(StockDto dto) {
        Stocks entity = new Stocks();

        entity.setSecId(dto.getSecId());
        entity.setShortname(dto.getShortname());
        entity.setRegNumber(dto.getRegNumber());
        entity.setName(dto.getName());
        entity.setIsin(dto.getIsin());
        entity.setEmitEntTitle(dto.getEmitEntTitle());
        entity.setUsers(new ArrayList<>());

        return entity;
    }

    /**
     Метод преобразует сущность акции в dto.
     @param entity, сущность акции.
     @return объект StockDto, является dto */
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

    /**
     Метод преобразует Dto акции за конкретный день торгов в сущность.
     @param dto, dto акции.
     @return объект StockFromDate, является сущностью */
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

    /**
     Метод преобразует Dto пользователя в сущность.
     @param dto, dto акции.
     @return объект User, является сущностью */
    public User userDtoToUser(UserDto dto) {
        User entity = new User();

        entity.setName(dto.getName());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setStocks(new ArrayList<>());

        return entity;
    }

    /**
     Метод преобразует сущность пользователя в dto.
     @param entity, сущность пользователя.
     @return объект ShortUserDto, является dto */
    public ShortUserDto userToShortUserDto(User entity) {
        ShortUserDto dto = new ShortUserDto();
        dto.setName(entity.getName());

        return dto;
    }

    /**
     Метод преобразует сущность элемента запроса расчета темпа роста в dto.
     @param entity, сущность элемента запроса.
     @return объект HistoryElemDto, является dto */
    public HistoryElemDto historyElemToHistoryElemDto(HistoryElem entity) {
        HistoryElemDto dto = new HistoryElemDto();
        dto.setDate(String.valueOf(entity.getDate()));
        dto.setShortName(entity.getShortName());
        String result = String.format("%.5f", entity.getGrowth());
        dto.setGrowth(result);
        return dto;
    }

    /**
     Метод преобразует сущность запроса расчета темпа роста в dto.
     @param entity, сущность запроса.
     @return объект HistoryDto, является dto */
    public HistoryDto historyToHistoryDto(History entity) {
        HistoryDto dto = new HistoryDto();

        dto.setUserName(entity.getUser().getName());
        dto.setFrom(String.valueOf(entity.getFrom()));
        dto.setTill(String.valueOf(entity.getTill()));

        int calendarDays = (int) ChronoUnit.DAYS.between(entity.getFrom(), entity.getTill()) + 1;

        dto.setDaysCalendar(calendarDays);

        String result = String.format("%.2f", entity.getResult());
        dto.setResult(result);

        dto.setCreate(parser.formatOffsetDateTime(entity.getCreate()));

        List<HistoryElem> elemList = entity.getHistoryElem();
        List<HistoryElemDto> elemDto = new ArrayList<>();

        if (elemList == null) {
            elemDto.add(new HistoryElemDto());
        } else {
            for (HistoryElem elem : elemList) {
                elemDto.add(historyElemToHistoryElemDto(elem));
            }
        }

        dto.setHistoryElemDto(elemDto);

        return dto;
    }
}

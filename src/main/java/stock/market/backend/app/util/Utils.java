package stock.market.backend.app.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Component
@Slf4j
public class Utils {

    /**
     Метод производит расчет рабочих дней за определенный промежуток дат.
     @param startDate, дата начала периода.
     @param endDate, дата конца периода.
     @return Integer количество рабочих дней */
    public Integer getDayOn(LocalDate startDate, LocalDate endDate) {
        Integer workingDays = 0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays++;
            }
            date = date.plusDays(1);
        }
        return workingDays;
    }
}

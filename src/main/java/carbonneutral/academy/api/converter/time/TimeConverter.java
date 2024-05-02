package carbonneutral.academy.api.converter.time;

import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeConverter {

    public static String toFormattedDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return localDateTime.format(formatter);
    }

    public static List<LocalDateTime> toLocalDateTime(String useAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(useAt, formatter);
        LocalDateTime startRange = dateTime;
        LocalDateTime endRange = dateTime.withNano(999999999);
        return List.of(startRange, endRange);
    }
}

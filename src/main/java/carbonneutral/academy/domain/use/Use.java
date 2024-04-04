package carbonneutral.academy.domain.use;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "uses")
public class Use {

    @Id
    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt = setCreatedAt();


    private boolean isInUse = true;
    private int point;
    private int userId;
    private int cafeId;

    private Long setCreatedAt() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String formattedDate = now.format(formatter);
        return Long.parseLong(formattedDate);
    }

}

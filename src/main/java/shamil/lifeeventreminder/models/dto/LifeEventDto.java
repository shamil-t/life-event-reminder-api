package shamil.lifeeventreminder.models.dto;

import lombok.*;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LifeEventDto {
    private long Id;
    private String eventName;
    private Date eventDate;
    private long userId;
}

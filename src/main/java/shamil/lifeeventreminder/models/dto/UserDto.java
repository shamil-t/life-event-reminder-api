package shamil.lifeeventreminder.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class UserDto {
    private long id;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String designation;
    private String profileImg;
    private long mangerId;
    private UserDto manager;
}

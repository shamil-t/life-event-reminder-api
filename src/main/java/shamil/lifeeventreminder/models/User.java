package shamil.lifeeventreminder.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fName;
    private String lName;
    private String email;
    private String phone;
    private String password;
    private String designation;
    private String profileImg;
    private long mangerId;
}

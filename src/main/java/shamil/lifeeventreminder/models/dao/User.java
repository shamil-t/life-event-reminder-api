package shamil.lifeeventreminder.models.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fName;
    private String lName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phone;
    private String password;
    private String designation;
    private String profileImg;
    private long mangerId;

}

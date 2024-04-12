package giuliochiarenza.progettoU5W2D5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private UUID employeeId;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String picProfile;


    public Employee( String username, String name, String surname, String email, String picProfile) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.picProfile = picProfile;
    }
}

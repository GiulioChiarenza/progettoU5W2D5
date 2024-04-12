package giuliochiarenza.progettoU5W2D5.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Device {
    @Id
    @GeneratedValue
    private UUID deviceId;
    private String type;
    @ManyToOne
    private Employee employee;
    private String deviceStatus;

    public Device( String type, Employee employee, String deviceStatus) {
        this.type = type;
        this.employee = employee;
        this.deviceStatus = deviceStatus;
    }
}

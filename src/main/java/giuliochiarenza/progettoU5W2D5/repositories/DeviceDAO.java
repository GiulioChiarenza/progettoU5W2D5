package giuliochiarenza.progettoU5W2D5.repositories;

import giuliochiarenza.progettoU5W2D5.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceDAO extends JpaRepository<Device, UUID> {
}

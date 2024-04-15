package giuliochiarenza.progettoU5W2D5.repositories;

import giuliochiarenza.progettoU5W2D5.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeDAO extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);
    Optional<Employee> findByEmail(String email);
}

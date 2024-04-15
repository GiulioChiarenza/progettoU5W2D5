package giuliochiarenza.progettoU5W2D5.services;

import giuliochiarenza.progettoU5W2D5.entities.Employee;
import giuliochiarenza.progettoU5W2D5.exceptions.UnauthorizedException;
import giuliochiarenza.progettoU5W2D5.payloads.EmployeeLoginDTO;
import giuliochiarenza.progettoU5W2D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateEmployeeAndGenereteToken(EmployeeLoginDTO payload) {
        Employee employee = this.employeeService.findByEmail(payload.email());
        if (employee.getPassword().equals(payload.password())) {
            return jwtTools.createToken(employee);
        } else {
            throw new UnauthorizedException("credenziali non valide, effettua di nuovo il login");
        }
    }
}

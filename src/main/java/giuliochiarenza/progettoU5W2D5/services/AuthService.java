package giuliochiarenza.progettoU5W2D5.services;

import giuliochiarenza.progettoU5W2D5.entities.Employee;
import giuliochiarenza.progettoU5W2D5.exceptions.UnauthorizedException;
import giuliochiarenza.progettoU5W2D5.payloads.EmployeeLoginDTO;
import giuliochiarenza.progettoU5W2D5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateEmployeeAndGenereteToken(EmployeeLoginDTO payload) {
        Employee employee = this.employeeService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), employee.getPassword())) {
        return jwtTools.createToken(employee);
        } else {
            throw new UnauthorizedException("credenziali non valide, effettua di nuovo il login");
        }
    }
}
//eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTMyNzkwMzgsImV4cCI6MTcxMzg4MzgzOCwic3ViIjoiZDZlNmQ5MzMtM2Q4Yi00Njc5LTk5NmQtYzU2MGQxYmI3ODY5In0.fFjQ5WbwguAs7PYwmEcnpQLpwhWL-F96cyYcfwSRRkAw3QhFJ9aQkKOgugvVee6c
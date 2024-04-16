package giuliochiarenza.progettoU5W2D5.controllers;

import giuliochiarenza.progettoU5W2D5.exceptions.BadRequestException;
import giuliochiarenza.progettoU5W2D5.payloads.EmployeeLoginDTO;
import giuliochiarenza.progettoU5W2D5.payloads.EmployeeLoginResponseDTO;
import giuliochiarenza.progettoU5W2D5.payloads.NewEmployeeDTO;
import giuliochiarenza.progettoU5W2D5.payloads.NewEmployeeRespDTO;
import giuliochiarenza.progettoU5W2D5.services.AuthService;
import giuliochiarenza.progettoU5W2D5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public EmployeeLoginResponseDTO login(@RequestBody EmployeeLoginDTO payload){
        return new EmployeeLoginResponseDTO(this.authService.authenticateEmployeeAndGenereteToken(payload));
    }
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEmployeeRespDTO saveEmployee(@RequestBody @Validated NewEmployeeDTO body, BindingResult validation){
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }

        return new NewEmployeeRespDTO(this.employeeService.saveEmployee(body).getEmployeeId());
    }


}

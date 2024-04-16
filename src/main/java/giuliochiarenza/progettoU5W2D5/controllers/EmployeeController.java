package giuliochiarenza.progettoU5W2D5.controllers;

import giuliochiarenza.progettoU5W2D5.entities.Employee;
import giuliochiarenza.progettoU5W2D5.exceptions.BadRequestException;
import giuliochiarenza.progettoU5W2D5.payloads.NewEmployeeDTO;
import giuliochiarenza.progettoU5W2D5.payloads.NewEmployeeRespDTO;
import giuliochiarenza.progettoU5W2D5.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService es;
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    private Page<Employee> getAllEmployee(@RequestParam(defaultValue = "0")int page,
                                        @RequestParam(defaultValue = "10")int size,
                                        @RequestParam(defaultValue = "name")String sortBy){
        return this.es.getEmployeeList(page, size, sortBy);
    }
    @GetMapping("/{employeeId}")
    private Employee findByEmployeeId(@PathVariable UUID employeeId) {
        return this.es.findById(employeeId);
    }
    @PostMapping
    private NewEmployeeRespDTO saveEmployee(@RequestBody @Validated NewEmployeeDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewEmployeeRespDTO(this.es.saveEmployee(body).getEmployeeId());
    }
    @PutMapping("/{employeeId}")
    private Employee findByIdAndUpdate(@PathVariable UUID employeeId, @RequestBody Employee body){
        return  this.es.findByIdAndUpdate(employeeId, body);
    }
    @DeleteMapping("/{employeeId}")
    private void findEmployeeByIdAndDelete(@PathVariable UUID employeeId){
        this.es.findByIdAndDelete(employeeId);
    }
    @PostMapping("/upload")
    public String uploadPicProfile(@RequestParam("picProfile") MultipartFile image) throws IOException {
        return this.es.uploadImage(image);
    }
    @GetMapping("/me")
    public Employee getProfile(@AuthenticationPrincipal Employee currentAuthenticatedEmployee){
        return currentAuthenticatedEmployee;
    }
    @PutMapping("/me")
    public Employee updateProfile(@AuthenticationPrincipal Employee currentAuthenticatedEmployee, @RequestBody Employee updatedEmployee){
        return this.es.findByIdAndUpdate(currentAuthenticatedEmployee.getEmployeeId(), updatedEmployee);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Employee currentAuthenticatedEmployee){
        this.es.findByIdAndDelete(currentAuthenticatedEmployee.getEmployeeId());
    }

}

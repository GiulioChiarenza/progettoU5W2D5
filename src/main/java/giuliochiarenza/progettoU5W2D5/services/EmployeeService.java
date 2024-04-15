package giuliochiarenza.progettoU5W2D5.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giuliochiarenza.progettoU5W2D5.entities.Employee;
import giuliochiarenza.progettoU5W2D5.exceptions.NotFoundException;
import giuliochiarenza.progettoU5W2D5.payloads.NewEmployeeDTO;
import giuliochiarenza.progettoU5W2D5.repositories.EmployeeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeDAO ed;
    @Autowired
    private Cloudinary cloudinaryUploader;

    public Page<Employee> getEmployeeList(int page, int size, String sortBy){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return this.ed.findAll(pageable);
    }
    public Employee saveEmployee(NewEmployeeDTO body){
        Employee newEmployee= new Employee(body.username(),body.name(),body.surname(),body.email(),body.password(),"https://ui-avatars.com/api/?name="+ body.name() + "+" + body.surname());
        return ed.save(newEmployee);
    }
    public Employee findById(UUID employeeId){
        return this.ed.findById(employeeId).orElseThrow(()-> new NotFoundException(employeeId));
    }
    public Employee findByIdAndUpdate(UUID employeeId, Employee updatedEmployee) {
        Employee found = this.findById(employeeId);
        found.setUsername(updatedEmployee.getUsername());
        found.setName(updatedEmployee.getName());
        found.setSurname(updatedEmployee.getSurname());
        found.setEmail(updatedEmployee.getEmail());
        found.setPicProfile("https://ui-avatars.com/api/?name="+ updatedEmployee.getName() + "+" + updatedEmployee.getSurname());
        return this.ed.save(found);
    }
    public void findByIdAndDelete(UUID employeeId){
        Employee found = this.findById(employeeId);
        this.ed.delete(found);
    }
    public String uploadImage(MultipartFile image) throws IOException {
        String url = (String) cloudinaryUploader.uploader().upload(image.getBytes(), ObjectUtils.emptyMap()).get("url");
        return url;
    }
    public Employee findByEmail(String email) {
        return ed.findByEmail(email).orElseThrow(()-> new NotFoundException("l'employee con email" + email + " non trovato"));
    }
}

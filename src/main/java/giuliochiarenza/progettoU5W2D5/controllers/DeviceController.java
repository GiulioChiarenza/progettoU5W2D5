package giuliochiarenza.progettoU5W2D5.controllers;

import giuliochiarenza.progettoU5W2D5.entities.Device;
import giuliochiarenza.progettoU5W2D5.exceptions.BadRequestException;
import giuliochiarenza.progettoU5W2D5.payloads.NewDeviceDTO;
import giuliochiarenza.progettoU5W2D5.payloads.NewDeviceRespDTO;
import giuliochiarenza.progettoU5W2D5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService ds;
    @GetMapping
    private Page<Device> getAllDevice(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "deviceId") String sortBy){
        return this.ds.getDeviceList(page, size, sortBy);
    }
    @GetMapping("/{deviceId}")
    private Device findByDeviceId(@PathVariable UUID deviceId) {
        return this.ds.findById(deviceId);
    }
    @PostMapping
    private NewDeviceRespDTO saveDevice(@RequestBody @Validated NewDeviceDTO body, BindingResult validation){
        if(validation.hasErrors()){
            System.out.println(validation.getAllErrors());
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewDeviceRespDTO(this.ds.saveDevice(body).getDeviceId());
    }
    @PutMapping("/{deviceId}")
    private Device findByIdAndUpdate(@PathVariable UUID deviceId, @RequestBody Device body){
        return  this.ds.findByIdAndUpdate(deviceId, body);
    }
    @DeleteMapping("/{deviceId}")
    private void findDeviceByIdAndDelete(@PathVariable UUID deviceId){
        this.ds.findByIdAndDelete(deviceId);
    }
}

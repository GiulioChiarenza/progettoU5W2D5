package giuliochiarenza.progettoU5W2D5.services;

import giuliochiarenza.progettoU5W2D5.entities.Device;
import giuliochiarenza.progettoU5W2D5.exceptions.NotFoundException;
import giuliochiarenza.progettoU5W2D5.payloads.NewDeviceDTO;
import giuliochiarenza.progettoU5W2D5.repositories.DeviceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeviceService {
@Autowired
    private DeviceDAO dd;
@Autowired
private EmployeeService es;
    public Page<Device> getDeviceList(int page, int size, String sortBy){
        if(size > 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dd.findAll(pageable);
    }
    public Device saveDevice(NewDeviceDTO body){
        Device newDevice = new Device(body.type(),es.findById(body.employeeId()),body.deviceStatus());
        return dd.save(newDevice);
    }
    public Device findById(UUID deviceId){
        return this.dd.findById(deviceId).orElseThrow(()-> new NotFoundException(deviceId));
    }
    public Device findByIdAndUpdate(UUID deviceId, Device updatedDevice){
        Device found = this.findById(deviceId);
        found.setType(updatedDevice.getType());
        found.setEmployee(updatedDevice.getEmployee());
        found.setDeviceStatus(updatedDevice.getDeviceStatus());
        return this.dd.save(found);
    }
    public void findByIdAndDelete(UUID deviceId){
        Device found = this.findById(deviceId);
        this.dd.delete(found);
    }
}

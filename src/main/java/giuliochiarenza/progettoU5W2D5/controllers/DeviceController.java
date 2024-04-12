package giuliochiarenza.progettoU5W2D5.controllers;

import giuliochiarenza.progettoU5W2D5.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService ds;









}

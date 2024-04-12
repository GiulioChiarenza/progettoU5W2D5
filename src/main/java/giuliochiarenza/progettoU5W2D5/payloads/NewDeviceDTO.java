package giuliochiarenza.progettoU5W2D5.payloads;

import jakarta.validation.constraints.NotEmpty;
import java.util.UUID;

public record NewDeviceDTO(@NotEmpty(message = "device type required")
                            String type,
                           @NotEmpty(message = "required device status")
                            String deviceStatus,
                            UUID employeeId) {
    @Override
    public String type() {
        return type;
    }
    @Override
    public String deviceStatus() {
        return deviceStatus;
    }
    @Override
    public UUID employeeId() {
        return employeeId;
    }
}
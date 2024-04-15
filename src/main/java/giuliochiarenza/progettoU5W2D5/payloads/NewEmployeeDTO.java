package giuliochiarenza.progettoU5W2D5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewEmployeeDTO(@NotEmpty(message = "username required")
                             @Size(max = 20, message = "the username cannot contain more than 20 characters")
                             String username,
                             @NotEmpty(message = "name required")
                             String name,
                             @NotEmpty(message = "surname required")
                             String surname,
                             @NotEmpty(message = "email required")
                             @Email(message = "the email entered is invalid")
                             String email,
                             @NotEmpty(message = "password required")
                             String password) {
}
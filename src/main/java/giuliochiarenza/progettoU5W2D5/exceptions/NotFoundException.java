package giuliochiarenza.progettoU5W2D5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id){super("The record with id: " + id + " was not found");}
}

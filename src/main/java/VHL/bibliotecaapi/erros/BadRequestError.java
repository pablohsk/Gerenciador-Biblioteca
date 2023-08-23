package VHL.bibliotecaapi.erros;

import org.springframework.http.HttpStatus;

import java.net.http.HttpRequest;

public class BadRequestError extends RuntimeException {

    public BadRequestError(String message){
        super(message);
    }

}

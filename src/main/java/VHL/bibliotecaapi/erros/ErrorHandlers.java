package VHL.bibliotecaapi.erros;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ErrorHandlers {

    @ExceptionHandler(BadRequestError.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestError erro){

        Map<String, String> m = new HashMap<>();
        m.put("mensagem", erro.getMessage());
        return ResponseEntity.badRequest().body(m);
    }

}


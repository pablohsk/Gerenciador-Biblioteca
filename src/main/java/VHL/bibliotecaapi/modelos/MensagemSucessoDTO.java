package VHL.bibliotecaapi.modelos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class MensagemSucessoDTO {

    private String mensagemSucesso;
    private HttpStatus httpStatus;
    private Object object;

    public MensagemSucessoDTO(String mensagemSucesso, HttpStatus httpStatus, Object object) {
        this.mensagemSucesso = mensagemSucesso;
        this.httpStatus = httpStatus;
        this.object = object;
    }
}
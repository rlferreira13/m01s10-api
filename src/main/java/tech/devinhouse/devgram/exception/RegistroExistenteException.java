package tech.devinhouse.devgram.exception;

public class RegistroExistenteException extends RuntimeException {

    public RegistroExistenteException(String nomeRecurso, String idRecurso) {
        super(nomeRecurso + " com identificador " + idRecurso + " já existente!");
    }

}

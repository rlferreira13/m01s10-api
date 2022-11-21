package tech.devinhouse.devgram.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException(String nomeRecurso, String idRecurso) {
        super(nomeRecurso + " com identificador " + idRecurso + " não encontrado!");
    }

    public RegistroNaoEncontradoException(String nomeRecurso, Long idRecurso) {
        this(nomeRecurso, String.valueOf(idRecurso));
    }

}

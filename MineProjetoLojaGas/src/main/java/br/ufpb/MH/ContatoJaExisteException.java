package br.ufpb.MH;

public class ContatoJaExisteException extends RuntimeException {
    public ContatoJaExisteException(String message) {
        super(message);
    }
}

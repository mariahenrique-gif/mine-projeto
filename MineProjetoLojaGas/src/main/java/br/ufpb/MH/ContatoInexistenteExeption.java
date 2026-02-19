package br.ufpb.MH;

public class ContatoInexistenteExeption extends RuntimeException {
    public ContatoInexistenteExeption(String message) {
        super(message);
    }
}

package br.ufpb.MH;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LojaGasTest {

    @Test
    void testAdicionarProduto() {
        LojaGas loja = new LojaGas(1000.0, "teste.txt");
        loja.adicionarProduto("Botijão", 5, 80.0);

        assertTrue(loja.getEstoques().containsKey("Botijão"));
        assertEquals(5, loja.getEstoques().get("Botijão").getQuantidade());
    }

    @Test
    void testRegistrarCompraInvalida() {
        LojaGas loja = new LojaGas(1000.0, "teste.txt");
        Estoque produto = new Estoque("Botijão", 5, 80.0);

        assertThrows(QuantidadeInvalidaException.class, () -> {
            loja.registrarCompra(produto, 0);
        });
    }

    @Test
    void testRegistrarVendaInsuficiente() {
        LojaGas loja = new LojaGas(1000.0, "teste.txt");
        Estoque produto = new Estoque("Botijão", 2, 80.0);

        assertThrows(EstoqueInsuficienteException.class, () -> {
            loja.registrarVenda(produto, 5);
        });
    }
}
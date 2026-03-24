package br.ufpb.MH;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CaixaTest {

    @Test
    void testRegistrarCompra() {
        Estoque produto = new Estoque("Gás", 0, 50.0);
        Caixa caixa = new Caixa(500.0);

        caixa.registrarCompra(produto, 2);

        assertEquals(400.0, caixa.getSaldoAtual());
        assertEquals(2, produto.getQuantidade());
        assertEquals(100.0, caixa.getTotalDespesas());
    }

    @Test
    void testRegistrarVenda() {
        Estoque produto = new Estoque("Gás", 10, 50.0);
        produto.setPrecoVendaUnidade(70.0);
        Caixa caixa = new Caixa(200.0);

        caixa.registrarVenda(produto, 2);

        assertEquals(340.0, caixa.getSaldoAtual());
        assertEquals(8, produto.getQuantidade());
        assertEquals(140.0, caixa.getTotalReceitas());
    }
}

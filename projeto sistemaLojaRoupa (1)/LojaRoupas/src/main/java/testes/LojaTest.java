package testes;

import com.loja.model.Loja;

public class LojaTest {
    public static void main(String[] args) {
        testCalcularLucro();
        testAdicionarItemEstoque();
        testAtualizarPreco();
    }

    public static void testCalcularLucro() {
        Loja loja = new Loja(1, "Loja A", 100, 50.0);
        double lucroEsperado = 100 * 50.0 * 0.20; // 20% de margem de lucro
        double lucroCalculado = loja.calcularLucro();
        if (lucroCalculado == lucroEsperado) {
            System.out.println("Teste de calcularLucro passou.");
        } else {
            System.out.println("Teste de calcularLucro falhou.");
        }
    }

    public static void testAdicionarItemEstoque() {
        Loja loja = new Loja(1, "Loja A", 100, 50.0);
        int quantidadeAdicional = 50;
        loja.adicionarItemEstoque(quantidadeAdicional);
        if (loja.getQuantidadeItens() == 100 + quantidadeAdicional) {
            System.out.println("Teste de adicionarItemEstoque passou.");
        } else {
            System.out.println("Teste de adicionarItemEstoque falhou.");
        }
    }

    public static void testAtualizarPreco() {
        Loja loja = new Loja(1, "Loja A", 100, 50.0);
        double novoPreco = 60.0;
        loja.atualizarPreco(novoPreco);
        if (loja.getPrecoMedio() == novoPreco) {
            System.out.println("Teste de atualizarPreco passou.");
        } else {
            System.out.println("Teste de atualizarPreco falhou.");
        }
    }
}

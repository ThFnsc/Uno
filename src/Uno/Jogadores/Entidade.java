package Uno.Jogadores;

import Uno.Auxiliares.ArrayListBom;
import Uno.Baralho;
import Uno.Cartas.Carta;
import Uno.Cores.Console;
import Uno.Cores.CorCarta;
import Uno.Jogo;

import java.util.Scanner;

public abstract class Entidade {
    protected String nome;
    protected ArrayListBom<Carta> cartas;
    protected Jogo jogo;
    protected Baralho baralho;

    public Entidade(String nome, Jogo jogo) {
        this.nome = nome;
        this.jogo = jogo;
        cartas = new ArrayListBom<>();
        this.baralho = jogo.getBaralho();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayListBom<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(ArrayListBom<Carta> cartas) {
        this.cartas = cartas;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public void setBaralho(Baralho baralho) {
        this.baralho = baralho;
    }

    public abstract void rodada();

    public CorCarta perguntarCor(Scanner scanner) {return null;}

    public boolean comprar(int quantidade) {
        int compradas = 0;
        for (int i = 0; i < quantidade; i++)
            if (comprarUmaCarta()) compradas++;
        Console.println(nome + " comprou " + compradas + (compradas == 1 ? " carta." : " cartas."), Console.Roxo);
        return compradas == quantidade;
    }

    private boolean comprarUmaCarta() {
        if (baralho.getCartasPraComprar().isEmpty()) {
            if (baralho.getPilhaDeCartas().isEmpty()) {
                Console.println("Não há mais cartas disponíveis para comprar.", Console.Vermelho);
                return false;
            } else {
                Console.println("Acabaram as cartas, embaralhando pilha e disponibilizando pra compra...", Console.Roxo);
                Carta topo = baralho.getPilhaDeCartas().removerUltimo();
                baralho.getPilhaDeCartas().moverTudo(baralho.getCartasPraComprar()).embaralhar();
                baralho.getPilhaDeCartas().add(topo);
            }
        }
        cartas.add(baralho.getCartasPraComprar().removerUltimo());
        return true;
    }
}

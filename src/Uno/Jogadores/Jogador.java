package Uno.Jogadores;

import Uno.Auxiliares.ArrayListBom;
import Uno.Cartas.Carta;
import Uno.Cores.Console;
import Uno.Cores.CorCarta;
import Uno.*;

import java.util.Collections;
import java.util.Scanner;

public class Jogador extends Entidade{

    private boolean disseUno;

    public Jogador(String nome, Jogo jogo) {
        super(nome, jogo);
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

    public void rodada() {
        for (; ; ) {
            Console.println("\n" + baralho.getCartasPraComprar().size() + (baralho.getCartasPraComprar().size() == 1 ? " carta" : " cartas") + " para comprar, " + baralho.getPilhaDeCartas().size() + (baralho.getPilhaDeCartas().size() == 1 ? " carta" : " cartas") + " na pilha. Últimas cartas da pilha: (Mais a direita = mais em cima)", Console.Roxo);
            Carta.printArtes(baralho.getPilhaDeCartas().ultimos(5), null);
            Carta cartaEscolhida = escolherCarta();
            if (cartaEscolhida == null) {
                comprar(1);
                cartas.ultimo().printArte(null);
                break;
            } else if (!cartaEscolhida.jogadaValida(baralho.getCartaNoTopo())) {
                Console.println("Movimento inválido, tente novamente\n", Console.Vermelho);
            } else {
                baralho.jogar(cartas.remover(cartaEscolhida));
                if (cartas.size() == 1) {
                    if (disseUno)
                        Console.println(nome + ": Uno!", Console.Ciano);
                    else {
                        Console.println("Você não disse Uno! D:", Console.Vermelho);
                        comprar(2);
                    }
                }
                break;
            }
        }
    }

    public CorCarta perguntarCor(Scanner scanner) {
        return CorCarta.perguntar(scanner);
    }

    public Carta escolherCarta() {
        Console.println("Todas as suas cartas, " + nome + ":", Console.Amarelo);
        Collections.sort(cartas);
        Carta.printArtes(cartas, baralho.getCartaNoTopo());
        Console.print("Escolha uma ou digite \"Comprar\": ", Console.Branco);
        disseUno = false;
        Carta carta = null;
        do {
            String entrada;
            do entrada = jogo.getScanner().nextLine().toLowerCase();
            while (entrada.length() == 0);
            if (entrada.startsWith("compra"))
                return null;
            else {
                for (Carta cartaFor : cartas)
                    if (entrada.startsWith(cartaFor.toString().toLowerCase())) {
                        carta = cartaFor;
                        disseUno = entrada.endsWith("uno");
                        if (disseUno)
                            if (cartas.size() != 2)
                                Console.println("Acho que você não sabe comecar Uno", Console.Vermelho);
                        break;
                    }
                if (carta == null)
                    Console.println("Carta '" + entrada + "' não encontrada", Console.Vermelho);
            }
        } while (carta == null);
        return carta;
    }
}
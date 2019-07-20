package Uno.Jogadores;

import Uno.Cartas.Carta;
import Uno.Cores.Console;
import Uno.Cores.CorCarta;
import Uno.Jogo;
import java.util.Random;
import java.util.Scanner;

public class Bot extends Entidade {
    public Bot(String nome, Jogo jogo) {
        super(nome, jogo);
    }

    public void rodada() {
        pensar();
        Carta cartaJogavel = cartas.remover(cartas.embaralhar().procurar(carta -> carta.jogadaValida(baralho.getCartaNoTopo())));
        if (cartaJogavel == null)
            comprar(1);
        else {
            baralho.jogar(cartaJogavel);
            if (cartas.size() == 1)
                Console.println(nome + ": Uno!", Console.Ciano);
        }
    }

    public CorCarta perguntarCor(Scanner scanner) {
        return corQueMaisTem();
    }

    public CorCarta corQueMaisTem(){
        int[] cores = new int[CorCarta.count()];
        for(Carta carta : cartas)
            if(carta.getCorCarta() != null)
                cores[carta.getCorCarta().ordinal()]++;
        int maxIndex=0;
        for(int i = 0; i < CorCarta.count(); i++)
            if(cores[i] > cores[maxIndex]) maxIndex=i;
        return CorCarta.values()[maxIndex];
    }

    private void pensar(){
        Jogo.delay(new Random().nextInt(1000)+1000);
    }
}

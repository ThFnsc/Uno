package Uno;

import Uno.Auxiliares.ArrayListBom;
import Uno.Jogadores.Bot;
import Uno.Jogadores.Entidade;
import Uno.Jogadores.Jogador;

public class Uno {
    public static void main(String[] args) {
        boolean modoDesenvolvedor = false;
        if (modoDesenvolvedor) {
            ArrayListBom<Entidade> entidades = new ArrayListBom<>();
            Jogo jogo = new Jogo(entidades);
            entidades
                    .novo(new Bot("Google assistant", jogo))
                    .novo(new Bot("Alexa", jogo))
                    //.novo(new Jogador("Thiago", jogo))
                    .novo(new Bot("Siri", jogo))
                    .novo(new Bot("Bixby", jogo))
                    .novo(new Bot("Cortana", jogo));
            jogo.comecar();
        } else
            new Jogo().comecar();
    }
}
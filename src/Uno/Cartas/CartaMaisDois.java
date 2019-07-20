package Uno.Cartas;
import Uno.Cores.CorCarta;
import Uno.Jogo;

public class CartaMaisDois extends Carta{
    public CartaMaisDois(CorCarta corCarta, Jogo jogo) {
        super(jogo);
        this.corCarta = corCarta;
        msg="+2";
    }

    @Override
    public void acaoEspecial() {
        jogo.proximoPerdeAVez();
        jogo.entidadeAtual().comprar(2);
    }

    @Override
    public String toString() {
        return "+2 " + corCarta;
    }
}

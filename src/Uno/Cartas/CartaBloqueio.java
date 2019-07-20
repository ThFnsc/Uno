package Uno.Cartas;
import Uno.Cores.CorCarta;
import Uno.Jogo;

public class CartaBloqueio extends Carta {
    public CartaBloqueio(CorCarta corCarta, Jogo jogo) {
        super(jogo);
        this.corCarta = corCarta;
        msg="Blo";
    }

    @Override
    public void acaoEspecial() {
        jogo.proximoPerdeAVez();
    }

    @Override
    public String toString() {
        return "Bloqueio " + corCarta;
    }
}

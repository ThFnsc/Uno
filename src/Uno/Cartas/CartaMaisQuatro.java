package Uno.Cartas;
import Uno.Cores.CorCarta;
import Uno.Jogo;

public class CartaMaisQuatro extends CartaCoringa {
    public CartaMaisQuatro(Jogo jogo) {
        super(jogo);
        setCorCarta(null);
    }

    @Override
    public void setCorCarta(CorCarta corCarta) {
        super.setCorCarta(corCarta);
        msg="+4";
    }

    @Override
    public void acaoEspecial() {
        super.acaoEspecial();
        jogo.proximoPerdeAVez();
        jogo.entidadeAtual().comprar(4);
    }

    @Override
    public String toString() {
        if (corCarta == null)
            return "+4";
        else
            return "+4 " + corCarta;
    }
}

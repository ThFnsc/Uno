package Uno.Cartas;
import Uno.Cores.CorCarta;
import Uno.Jogo;

public class CartaCoringa extends Carta {
    public CartaCoringa(Jogo jogo) {
        super(jogo);
        setCorCarta(null);
    }

    @Override
    public void setCorCarta(CorCarta corCarta) {
        super.setCorCarta(corCarta);
        msg="Cor";
    }

    @Override
    public void decisao() {
        setCorCarta(jogo.entidadeAtual().perguntarCor(jogo.getScanner()));
    }

    @Override
    public String toString() {
        if (corCarta == null)
            return "Coringa";
        else
            return "Coringa " + corCarta;
    }
}

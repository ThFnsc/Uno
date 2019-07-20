package Uno.Cartas;
import Uno.Cores.CorCarta;
import Uno.Jogo;

public class CartaNumerica extends Carta {
    private int numero;

    public CartaNumerica(int numero, CorCarta corCarta, Jogo jogo) {
        super(jogo);
        this.numero = numero;
        this.corCarta = corCarta;
        msg=Integer.toString(numero);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public CorCarta getCor() {
        return corCarta;
    }

    public void setCor(CorCarta corCarta) {
        this.corCarta = corCarta;
    }

    @Override
    public String toString() {
        return numero + " " + corCarta;
    }
}

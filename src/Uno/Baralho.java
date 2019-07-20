package Uno;
import Uno.Auxiliares.ArrayListBom;
import Uno.Cartas.*;
import Uno.Cores.Console;
import Uno.Cores.CorCarta;
import Uno.Jogadores.Entidade;

public class Baralho {
    private ArrayListBom<Carta> cartasPraComprar;
    private ArrayListBom<Carta> pilhaDeCartas;
    private Jogo jogo;

    public Baralho(Jogo jogo) {
        pilhaDeCartas = new ArrayListBom<>();
        cartasPraComprar = new ArrayListBom<>();
        this.jogo = jogo;
    }

    public ArrayListBom<Carta> getCartasPraComprar() {
        return cartasPraComprar;
    }

    public ArrayListBom<Carta> getPilhaDeCartas() {
        return pilhaDeCartas;
    }

    public void inicializar(int cartasPorJogador) {
        gerarCartas();
        embaralharCartas();
        distribuirCartas(jogo.getEntidades(), cartasPorJogador);
        escolherPrimeiraCarta();
    }

    public void embaralharCartas() {
        Console.println("Embaralhando...", Console.Roxo);
        cartasPraComprar.embaralhar();
    }

    public void gerarCartas() {
        cartasPraComprar.clear();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < CorCarta.values().length; j++)
                for (int k = 0; k < (i == 0 ? 1 : 2); k++)
                    cartasPraComprar.novo(new CartaNumerica(i, CorCarta.values()[j], jogo));
        for (int j = 0; j < CorCarta.values().length; j++)
            for (int k = 0; k < 2; k++)
                cartasPraComprar.novo(new CartaBloqueio(CorCarta.values()[j], jogo))
                        .novo(new CartaMaisDois(CorCarta.values()[j], jogo));
        for (int k = 0; k < 4; k++)
            cartasPraComprar.novo(new CartaCoringa(jogo))
                    .novo(new CartaMaisQuatro(jogo));
        Console.println(cartasPraComprar.size() + " cartas geradas.", Console.Roxo);
    }

    public void distribuirCartas(ArrayListBom<Entidade> entidades, int qtdPraCada) {
        Console.println("Distribuindo " + qtdPraCada + " cartas para cada um dos " + entidades.size() + " jogadores...", Console.Roxo);
        for (int j = 0; j < qtdPraCada; j++)
            for(Entidade entidade : entidades)
                entidade.getCartas().add(cartasPraComprar.removerUltimo());
    }

    public void escolherPrimeiraCarta() {
        Console.println("Escolhendo primeira carta...", Console.Roxo);
        for (; ; ) {
            pilhaDeCartas.add(cartasPraComprar.removerUltimo());
            getCartaNoTopo().printArte(null);
            if (getCartaNoTopo() instanceof CartaNumerica) {
                Console.println("Válida.", Console.Verde);
                return;
            } else
                Console.println("Inválida.", Console.Vermelho);
        }
    }

    public Carta jogar(Carta carta) {
        pilhaDeCartas.add(carta);
        carta.decisao();
        carta.anunciar();
        if (jogo.ganhador() != null)
            return carta;
        carta.acaoEspecial();
        return carta;
    }

    public Carta getCartaNoTopo() {
        return pilhaDeCartas.ultimo();
    }
}
package Uno.Cartas;
import Uno.Auxiliares.ArrayListBom;
import Uno.Auxiliares.Posicao;
import Uno.Cores.Console;
import Uno.Cores.CorCarta;
import Uno.Auxiliares.Tamanho;
import Uno.Jogo;

public abstract class Carta implements Comparable<Carta> {
    protected CorCarta corCarta;
    protected String msg;
    protected static final String meio = "Uno";
    protected static final Tamanho t = new Tamanho(7, 5);
    protected Jogo jogo;

    public Carta() {
    }

    public Carta(Jogo jogo){
        this.jogo=jogo;
    }

    public CorCarta getCorCarta() {
        return corCarta;
    }

    public void setCorCarta(CorCarta corCarta) {
        this.corCarta = corCarta;
    }

    public boolean jogadaValida(Carta carta) {
        if (this.corCarta == carta.corCarta)
            return true;
        if (this instanceof CartaNumerica && carta instanceof CartaNumerica) {
            if (((CartaNumerica) this).getNumero() == ((CartaNumerica) carta).getNumero())
                return true;
        } else if (this.getClass().equals(carta.getClass()))
            return true;
        return this instanceof CartaCoringa;
    }

    private char writeWithin(String msg, int desl, int offset) {
        int pos = desl - offset;
        return pos >= 0 && pos < msg.length() ? msg.charAt(pos) : 0;
    }

    private void render(Posicao p, Carta topo) {
        char car;
        if (p.c == 0)
            Console.setCor(corCarta == null ? Console.Branco : corCarta.toConsole());
        if (p.l == 0)
            if (p.c == 0)
                car = '╔';
            else if (p.c == t.w - 1)
                car = '╗';
            else
                car = '═';
        else if (p.l == t.h - 1)
            if (p.c == 0)
                car = '╚';
            else if (p.c == t.w - 1)
                car = '╝';
            else
                car = '═';
        else if (p.c == 0 || p.c == t.w - 1)
            car = '║';
        else if (p.l == 1 && p.c == t.w - 2 && topo != null && this.jogadaValida(topo))
            car = '#';
        else if (p.l == 1 && (car = writeWithin(msg, p.c, 1)) != 0) ;
        else if (p.l == t.h - 2 && (car = writeWithin(msg, p.c, t.w - msg.length() - 1)) != 0) ;
        else if (p.l == t.h / 2 && (car = writeWithin(meio, p.c, (t.w - meio.length()) / 2)) != 0) ;
        else
            car = ' ';
        System.out.print(car);
    }

    public void printArte(Carta topo) {
        ArrayListBom<Carta> cartas = new ArrayListBom<Carta>();
        cartas.add(this);
        printArtes(cartas, topo);
    }

    public static void printArtes(ArrayListBom<Carta> cartas, Carta topo) {
        Posicao p = new Posicao();
        for (p.l = 0; p.l < t.h; p.l++) {
            for (Carta carta : cartas) {
                for (p.c = 0; p.c < t.w; p.c++)
                    carta.render(p, topo);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public void decisao(){}

    public void anunciar(){
        Console.println(jogo.entidadeAtual().getNome() + ", agora com " + jogo.entidadeAtual().getCartas().size() + (jogo.entidadeAtual().getCartas().size() == 1 ? " carta" : " cartas") + ", jogou:", Console.Azul);
        printArte(null);
    }

    public void acaoEspecial(){}

    @Override
    public int compareTo(Carta o) {
        if (this.corCarta == null)
            return -1;
        if (o.corCarta == null)
            return +1;
        if (this.corCarta != o.corCarta)
            return this.corCarta.ordinal() - o.corCarta.ordinal();
        if (this instanceof CartaNumerica && o instanceof CartaNumerica)
            return ((CartaNumerica) this).getNumero() - ((CartaNumerica) o).getNumero();
        if (this instanceof CartaNumerica)
            return +1;
        if (o instanceof CartaNumerica)
            return -1;
        return this.getClass().toString().compareToIgnoreCase(o.getClass().toString());
    }
}
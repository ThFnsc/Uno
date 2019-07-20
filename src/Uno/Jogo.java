package Uno;

import Uno.Auxiliares.ArrayListBom;
import Uno.Cores.Console;
import Uno.Jogadores.Bot;
import Uno.Jogadores.Entidade;
import Uno.Jogadores.Jogador;

import java.util.Scanner;

public class Jogo {
    private Scanner scanner;
    private Baralho baralho;
    private ArrayListBom<Entidade> entidades;
    private static final boolean delays=true;
    private int rodada;

    public Jogo() {
        scanner = new Scanner(System.in);
        baralho = new Baralho(this);
        entidades = new ArrayListBom<>();
    }

    public Jogo(ArrayListBom<Entidade> entidades) {
        this();
        this.entidades = entidades;
    }

    public Baralho getBaralho() {
        return baralho;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public ArrayListBom<Entidade> getEntidades() {
        return entidades;
    }

    public int getRodada() {
        return rodada;
    }

    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    public int proximoJogadorIndex(){
        return (rodada + 1) % entidades.size();
    }

    public Entidade proximaEntidade(){
        return entidades.get(proximoJogadorIndex());
    }

    public Entidade entidadeAtual(){
        return entidades.get(rodada% entidades.size());
    }

    public void proximoPerdeAVez(){
        rodada++;
        Console.println(entidadeAtual().getNome() + " perdeu a rodada.", Console.Vermelho);
    }

    public boolean registrarEntidade(boolean perguntar){
        if(perguntar && !fazerPergunta("Registrar mais um jogador? (sim/não): ", scanner))
            return false;
        Console.print("Digite o nome do jogador: ", Console.Roxo);
        String nome;
        do nome = scanner.nextLine();
        while (nome.length() == 0);
        entidades.add(Jogo.fazerPergunta("Bot? (sim/não): ", scanner) ? new Bot(nome, this) : new Jogador(nome, this));
        return true;
    }

    public int comecar() {
        if(entidades.size()<2)
            for(; registrarEntidade(entidades.size()>=2) && entidades.size() < 6;);
        baralho.inicializar(7);
        for (rodada = 0; ganhador() == null; rodada++) {
            Entidade entidadeAnterior = entidadeAtual();
            entidadeAtual().rodada();
            if(entidadeAnterior instanceof Jogador && proximaEntidade() instanceof Jogador && proximaEntidade() != entidadeAnterior && ganhador()==null) {
                limpaTela();
                System.out.print(proximaEntidade().getNome()+", quando estiver pronto(a), pressione Enter.");
                scanner.nextLine();
            }
        }
        Console.println(ganhador().getNome() + " venceu!\n"+rodada+" rodadas.", Console.Ciano);
        return rodada;
    }

    public void limpaTela(){
        for(int i = 0; i < 50; i++)
            System.out.println();
    }

    public Entidade ganhador() {
        return entidades.procurar(entidade -> entidade.getCartas().isEmpty());
    }

    public static boolean fazerPergunta(String pergunta, Scanner scanner){
        Console.print(pergunta, Console.Roxo);
        String entrada;
        do entrada = scanner.next().toLowerCase();
        while(!entrada.startsWith("s") && !entrada.startsWith("n"));
        return entrada.startsWith("s");
    }

    public static boolean delay(long ms){
        if(!delays) return false;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
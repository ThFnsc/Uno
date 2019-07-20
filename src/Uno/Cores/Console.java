package Uno.Cores;

public class Console {
    public static final String
            Normal = "\u001B[0m",
            Preto = "\u001B[30m",
            Vermelho = "\u001B[31m",
            Verde = "\u001B[32m",
            Amarelo = "\u001B[33m",
            Azul = "\u001B[34m",
            Roxo = "\u001B[35m",
            Ciano = "\u001B[36m",
            Branco = "\u001B[37m";

    public static void print(String msg, String cor) {
        System.out.print(inserirCor(msg, cor));
    }

    public static void println(String msg, String cor) {
        print(msg, cor);
        System.out.println();
    }

    public static String inserirCor(String msg, String cor) {
        return cor + msg + Console.Normal;
    }

    public static void setCor(String cor) {
        System.out.print(cor);
    }
}
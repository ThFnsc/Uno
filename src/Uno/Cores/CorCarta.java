package Uno.Cores;
import java.util.Scanner;

public enum CorCarta {
    Verde,
    Amarelo,
    Vermelho,
    Azul;

    public static int count(){
        return CorCarta.values().length;
    }

    public static CorCarta perguntar(Scanner scanner){
        Console.print("Escolha uma cor entre ", Console.Normal);
        for(int i = 0; i < CorCarta.count()-1; i++)
            System.out.print(CorCarta.values()[i] + (i< CorCarta.count()-2 ? ", " : ""));
        System.out.print(" e " + CorCarta.values()[CorCarta.count()-1]+": ");
        for(;;) {
            String entrada;
            do entrada = scanner.next();
            while (entrada.length() == 0);
            for (int i = 0; i < CorCarta.count(); i++)
                if (entrada.equalsIgnoreCase(CorCarta.values()[i].toString()))
                    return CorCarta.values()[i];
            System.out.print("'" + entrada + "' não é uma das cores válidas. tente novamente: ");
        }
    }

    public String toConsole() {
        switch (this) {
            case Amarelo:
                return Console.Amarelo;
            case Verde:
                return Console.Verde;
            case Vermelho:
                return Console.Vermelho;
            case Azul:
                return Console.Azul;
            default:
                return Console.Branco;
        }
    }
}
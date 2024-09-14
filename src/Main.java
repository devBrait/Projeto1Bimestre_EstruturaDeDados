import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Para sempre exibir ponto e não virgula
        Locale.setDefault(Locale.US);
        System.out.println("Seja bem-vindo(a) a calculadora!!!");
        Scanner s = new Scanner(System.in);
        VerificaEntrada verificaEntrada = new VerificaEntrada();

        while (true)
        {
            System.out.println("Digite o comando que você deseja executar: ");

            String expressao = s.nextLine().trim().toUpperCase();

            verificaEntrada.isValid(expressao);
        }
    }
}
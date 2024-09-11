import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Seja bem-vindo(a) a calculadora");
        Scanner s = new Scanner(System.in);
        VerificaEntrada verificaEntrada = new VerificaEntrada();

        while (true) {
            System.out.println("Digite o comando que você deseja executar: ");
            String input = s.nextLine().trim().toUpperCase();

            verificaEntrada.is_valid(input);
        }
    }
}
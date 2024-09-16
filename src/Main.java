/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

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
            System.out.print("> ");
            String expressao = s.nextLine().trim().toUpperCase();

            verificaEntrada.isValid(expressao);
        }
    }
}

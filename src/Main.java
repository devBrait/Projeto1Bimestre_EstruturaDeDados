/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046

Referências:

 - Formatação de valores double: https://receitasdecodigo.com.br/java/numberformat-ou-decimalformat-formatar-numeros-reais-em-java

  - Expressões regulares em java: https://www.devmedia.com.br/conceitos-basicos-sobre-expressoes-regulares-em-java/27539

  - Manipulação de strings: https://www.academicotech.com/post/manipulando-strings-com-java-10-funcoes-e-metodos-uteis

  - Fundamentos da pilha: https://www.devmedia.com.br/pilhas-fundamentos-e-implementacao-da-estrutura-em-java/28241

  - Tratamento de erros: https://www.alura.com.br/apostila-java-orientacao-objetos/excecoes-e-controle-de-erros
 */

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Para sempre exibir ponto e não vírgula
        Locale.setDefault(Locale.US);
        Scanner s = new Scanner(System.in);
        VerificaEntrada verificaEntrada = new VerificaEntrada();

        System.out.println("Seja bem-vindo(a) à calculadora!!!\n");
        Menu menu = new Menu();
        menu.exibeMenu();

        while (true)
        {
            System.out.print("\n> ");
            String expressao = s.nextLine().trim().toUpperCase();

            verificaEntrada.isValid(expressao);
        }
    }
}

/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

// Realiza o cálculo da expressão final em notação posfixa
public class VerificaExpressao {
    // Declaração de variáveos utilizadas na classe
    private double[] pilhaNumeros;
    private int topo;

    // Construtor que inicializa a pilha com o tamanho máximo fornecido
    public VerificaExpressao(int tamanhoMaximo) {
        pilhaNumeros = new double[tamanhoMaximo];
        topo = -1;
    }

    // Metodo para empilhar um número
    private void push(double numero) {
        if (topo == pilhaNumeros.length - 1)
        {
            throw new RuntimeException("Pilha cheia");
        }

        pilhaNumeros[++topo] = numero;
    }

    // Metodo para desempilhar um número
    private double pop() {
        if (topo == -1)
        {
            throw new RuntimeException("Pilha vazia");
        }

        return pilhaNumeros[topo--];
    }

    public double avaliar(String expressaoPosfixa, GerenciaVariavel variavel) {
        // Loop para verificar cada caractere da expressão pós-fixa
        for (int i = 0; i < expressaoPosfixa.length(); i++)
        {
            char c = expressaoPosfixa.charAt(i);

            // Ignora os espaços
            if (Character.isWhitespace(c))
            {
                continue;
            }

            // Se for letra, empilhar valor da variável
            if (Character.isLetter(c))
            {
                push(variavel.obterValor(c));
            }
            // Se for um operador
            else if ("+-*/^".indexOf(c) != -1)
            {
                if (topo < 1)
                {
                    throw new RuntimeException("Impossível realizar essa operação.");
                }

                // Desempilha dois números para operação
                double b = pop();
                double a = pop();
                switch (c)
                {
                    case '+':
                        push(a + b); // Realiza a adição e empilha o resultado
                        break;
                    case '-':
                        push(a - b); // Realiza a subtração e empilha o resultado
                        break;
                    case '*':
                        push(a * b); // Realiza a multiplicação e empilha o resultado
                        break;
                    case '/':
                        if (b == 0)
                        {
                            throw new ArithmeticException("Impossível realizar divisão por zero.");
                        }
                        push(a / b); // Realiza a divisão e empilha o resultado
                        break;
                    case '^':
                        push(Math.pow(a, b)); // Realiza a exponenciação e empilha o resultado
                        break;
                }
            }
        }
        return pop(); // Retorna o resultado final da expressão
    }

}

/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

public class VerificaExpressao {
    private double[] pilhaNumeros;
    private int topo;

    public VerificaExpressao(int tamanhoMaximo) {
        pilhaNumeros = new double[tamanhoMaximo];
        topo = -1;
    }

    private void push(double numero) {
        if (topo == pilhaNumeros.length - 1)
        {
            throw new RuntimeException("Pilha cheia");
        }

        pilhaNumeros[++topo] = numero;
    }

    private double pop() {
        if (topo == -1)
        {
            throw new RuntimeException("Pilha vazia");
        }

        return pilhaNumeros[topo--];
    }

    public double avaliar(String expressaoPosfixa, GerenciaVariavel variavel) {
        String[] tokens = expressaoPosfixa.split("\\s+");
        for (String token : tokens) {
            if (Character.isLetter(token.charAt(0)))
            {
                push(variavel.obterValor(token.charAt(0)));
            } else if (token.length() == 1 && "+-*/^".indexOf(token.charAt(0)) != -1) // Verifica se é um operador
            {
                if (topo < 1) {
                    throw new RuntimeException("Impossível realizar essa operação.");
                }

                double b = pop();
                double a = pop();
                switch (token.charAt(0)) {
                    case '+':
                        push(a + b);
                        break;
                    case '-':
                        push(a - b);
                        break;
                    case '*':
                        push(a * b);
                        break;
                    case '/':
                        if (b == 0)
                        {
                            throw new ArithmeticException("Impossível realizar divisão por zero.");
                        }
                        push(a / b);
                        break;
                    case '^':
                        push(Math.pow(a, b));
                        break;
                }
            } else {
                // Tenta empilhar como número, caso não seja letra ou operador
                push(Double.parseDouble(token));
            }
        }
        return pop();
    }
}

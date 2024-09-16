/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

public class ConversorExpressao {

    Pilha pilha = new Pilha();

    private int prioridade(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                throw new RuntimeException("Erro: Operador inválido.");
        }
    }

    public String convertorPosFixa(String expressao) {
        StringBuilder posfixa = new StringBuilder();
        StringBuilder numeroOuVariavel = new StringBuilder();

        for (char c : expressao.toCharArray())
        {
            if (Character.isLetterOrDigit(c)) //Verifica letras e números
            {
                numeroOuVariavel.append(c);
            } else
            {
                if (numeroOuVariavel.length() > 0)
                {
                    posfixa.append(numeroOuVariavel).append(' ');
                    numeroOuVariavel.setLength(0); // Limpa o buffer
                }

                if (c == '(')
                {
                    pilha.push(c);
                } else if (c == ')')
                {
                    // Desempilha até encontrar o parêntese de abertura '('
                    while (!pilha.isEmpty() && pilha.top() != '(') {
                        posfixa.append(pilha.pop()).append(' ');
                    }

                    // Verifica se encontrou o parêntese de abertura
                    if (!pilha.isEmpty() && pilha.top() == '(')
                    {
                        pilha.pop(); // Remove o '('
                    } else
                    {
                        throw new RuntimeException("Erro: Parênteses desbalanceados.");
                    }

                } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
                {
                    // Desempilha operadores de maior ou igual prioridade antes de empilhar o novo
                    while (!pilha.isEmpty() && pilha.top() != '(' && prioridade(pilha.top()) >= prioridade(c))
                    {
                        posfixa.append(pilha.pop()).append(' ');
                    }
                    pilha.push(c);
                } else if (!Character.isWhitespace(c))
                {
                    throw new RuntimeException("Erro: Operador inválido.");
                }
            }
        }

        // Adiciona qualquer número ou variável restante à expressão posfixa
        if (numeroOuVariavel.length() > 0)
        {
            posfixa.append(numeroOuVariavel).append(' ');
        }

        // Desempilha todos os operadores restantes
        while (!pilha.isEmpty())
        {
            char operador = pilha.pop();
            if (operador == '(' || operador == ')')
            {
                throw new RuntimeException("Erro: Parênteses desbalanceados.");
            }
            posfixa.append(operador).append(' ');
        }

        return posfixa.toString();
    }
}

/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

// Realiza a conversão da expressão para posFixa
public class ConversorExpressao {

    // Instância da classe pilha
    Pilha pilha = new Pilha();

    // Metodo que determina a prioridade dos operadores
    private int prioridade(char operador)
    {
        switch (operador)
        {
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
        StringBuilder posfixa = new StringBuilder(); // Armazena a expressão em notação pós-fixa
        StringBuilder strVar = new StringBuilder(); // Armazena variáveis temporariamente

        // Percorre cada caractere da expressao
        for (char c : expressao.toCharArray())
        {
            if (Character.isLetter(c)) //Verifica variaveis
            {
                strVar.append(c);
            } else
            {
                if (strVar.length() > 0)
                {
                    posfixa.append(strVar).append(' ');
                    strVar.setLength(0); // Limpa o buffer
                }

                // Caso encontra um parêntese de abertura, empilha
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
                    pilha.push(c); // Empilha o operador atual
                } else if (!Character.isWhitespace(c))
                {
                    throw new RuntimeException("Erro: Operador inválido.");
                }
            }
        }

        // Adiciona qualquer variável restante à expressão posfixa
        if (strVar.length() > 0)
        {
            posfixa.append(strVar).append(' ');
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
        // Retorna a notação posfixa
        return posfixa.toString();
    }
}

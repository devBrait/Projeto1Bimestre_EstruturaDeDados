/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

import java.text.DecimalFormat;

public class GerenciaVariavel {

    private double[] valores = new double[26];
    private boolean[] definidas = new boolean[26];
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public void definirValor(char var, double valor) {
        valores[var - 'A'] = valor;
        definidas[var - 'A'] = true;
    }

    public double obterValor(char var) {
        if(!definidas[var - 'A'])
        {
            return -1;
        }
        return valores[var - 'A'];
    }

    public void listarVariaveis() {
        boolean variaveisDefinidas = false;

        for (int i = 0; i < 26; i++)
        {
            if (definidas[i])
            {
                System.out.println((char) (i + 'A') + " = " + decimalFormat.format(valores[i]));
                variaveisDefinidas = true;
            }
        }

        if (!variaveisDefinidas)
        {
            System.out.println("Nenhuma variável definida.");
        }
    }

    public void reset() {
        for (int i = 0; i < 26; i++)
        {
            definidas[i] = false;
            valores[i] = 0;
        }
        System.out.println("Variáveis reiniciadas.");
    }

    public int quantidadeVariaveis() {
        int cont=0;

        for (int i = 0; i < 26; i++)
        {
            if (definidas[i])
            {
                cont++;
            }
        }

        return cont;
    }

}

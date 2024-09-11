public class GerenciaVariavel {

    private int[] valores = new int[26];
    private boolean[] definidas = new boolean[26];

    public void definirValor(char var, int valor) {
        if (var < 'A' || var > 'Z') {
            throw new IllegalArgumentException("Erro: Variável inválida.");
        }
        valores[var - 'A'] = valor;
        definidas[var - 'A'] = true;
    }

    public int obterValor(char var) {
        if (!definidas[var - 'A']) {
            System.out.println("Erro: variável " + var + " não definida.");
        }
        return valores[var - 'A'];
    }

    public void listarVariaveis() {
        boolean variaveisDefinidas = false;

        for (int i = 0; i < 26; i++) {
            if (definidas[i]) {
                System.out.println((char) (i + 'A') + " = " + valores[i]);
                variaveisDefinidas = true;
            }
        }

        if (!variaveisDefinidas) {
            System.out.println("Nenhuma variável definida.");
        }
    }

    public void reset() {
        for (int i = 0; i < 26; i++) {
            definidas[i] = false;
        }
        System.out.println("Variáveis reiniciadas.");
    }
}

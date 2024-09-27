/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

// Classe que representa uma fila com uma série de comandos
public class Fila {

    // Declaração de variáveis utilizadas na classe
    private String[] comandos;
    private int contador;
    private boolean gravando;

    // Construtor que inicializa a fila com um tamanho fixo de 10
    public Fila() {
        comandos = new String[10];
        contador = 0;
        gravando = false;
    }

    // Inicia a gravação de comandos
    public void iniciarGravacao() {
        gravando = true;
        System.out.println("Iniciando gravação... (REC: " + contador + "/10)");
    }

    // Para a gravação de comandos
    public void pararGravacao() {
        gravando = false;
        System.out.println("Encerrando gravação... (REC: " + contador + "/10)");
    }

    // Registra todos os comandos inseridos
    public void gravarComando(String expressao) {
        if (gravando) {
            if (contador < comandos.length)
            {
                if (expressao.equalsIgnoreCase("PLAY") || expressao.equalsIgnoreCase("ERASE"))
                {
                    System.out.println("Erro: comando inválido para gravação.");
                } else
                {
                    comandos[contador++] = expressao;
                    String[] partes = expressao.split("=");

                    if (partes.length == 2)
                    {
                        System.out.println("(REC: " + contador + "/10) " + partes[0].trim().toUpperCase() + " = " + partes[1].trim());
                    }else
                    {
                        System.out.println("(REC: " + contador + "/10) " + expressao);
                    }
                }
            } else
            {
                System.out.println("Erro: Limite de comandos alcançado.");
                pararGravacao();
            }
        } else {
            System.out.println("Erro: Não está gravando.");
        }
    }

    // Retorna a lista de comandos gravados na fila
    public String[] retornaFila()
    {
        if(contador == 0){
            throw new RuntimeException("Não há gravação para ser reproduzida.");
        }
        return comandos;
    }

    // Deleta todos os comandos gravados na fila
    public void apagar() {
        if (contador == 0)
        {
            System.out.println("Nenhuma gravação para ser apagada.");
        } else
        {
            comandos = new String[10];
            contador = 0;
            System.out.println("Gravação apagada.");
        }
    }

    // Verifica se a fila está cheia
    public boolean isFull()
    {
        return contador >= comandos.length;
    }
}

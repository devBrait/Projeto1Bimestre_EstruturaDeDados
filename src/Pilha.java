/*
Nome: Eduardo Henrique de Souza Cruz RA: 10358690
Nome: Guilherme Teodoro de Oliveira RA: 10425362
Nome: Vinícius Brait Lorimier RA: 10420046
 */

// Classe que representa uma pilha
public class Pilha {

        // Declaração de variáveis utilizadas na classe
        private char[] elementos;
        private int topo;

        // Construtor que inicializa a pilha com tamanho especifico
        public Pilha(int capacidade) {
            elementos = new char[capacidade];
            topo = -1;
        }

        // Construtor padrão que inicializa a pilha com tamanho padrão de 1024
        public Pilha(){
            this(1024);
        }

        // Adiciona um elemento ao topo da pilha
        public void push(char elemento) {
            if (topo == elementos.length - 1)
            {
                throw new RuntimeException("Pilha cheia");
            }
            elementos[++topo] = elemento;
        }

        // Remove e retorna o elemento do topo da pilha
        public char pop() {
            if (topo == -1)
            {
                throw new RuntimeException("Pilha vazia");
            }
            return elementos[topo--];
        }

        // Retorna o elemento do topo da pilha sem removê-lo
        public char top() {
            if (topo == -1)
            {
                throw new RuntimeException("Pilha vazia");
            }
            return elementos[topo];
        }

        // Verifica se a pilha está vazia
        public boolean isEmpty() {
            return topo == -1;
        }
}

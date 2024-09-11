public class Pilha {
        private char[] elementos;
        private int topo;

        public Pilha(int capacidade) {
            elementos = new char[capacidade];
            topo = -1;
        }

        public void empilhar(char elemento) {
            if (topo == elementos.length - 1) {
                throw new RuntimeException("Pilha cheia");
            }
            elementos[topo++] = elemento;
        }

        public char desempilhar() {
            if (topo == -1) {
                throw new RuntimeException("Pilha vazia");
            }
            return elementos[topo--];
        }

        public char topo() {
            if (topo == -1) {
                throw new RuntimeException("Pilha vazia");
            }
            return elementos[topo];
        }

        public boolean vazia() {
            return topo == -1;
        }
}

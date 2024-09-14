public class Pilha {
        private char[] elementos;
        private int topo;

        public Pilha(int capacidade) {
            elementos = new char[capacidade];
            topo = -1;
        }

        public Pilha(){
            this(1024);
        }

        public void push(char elemento) {
            if (topo == elementos.length - 1) {
                throw new RuntimeException("Pilha cheia");
            }
            elementos[++topo] = elemento;
        }

        public char pop() {
            if (topo == -1) {
                throw new RuntimeException("Pilha vazia");
            }
            return elementos[topo--];
        }

        public char top() {
            if (topo == -1) {
                throw new RuntimeException("Pilha vazia");
            }
            return elementos[topo];
        }

        public boolean isEmpty() {
            return topo == -1;
        }
}

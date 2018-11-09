package AVL;

/**
 *
 * @author FlowOverStack
 */
public class AVL<K extends Comparable<K>, E> {
    No<K, E> raiz;
    int tamanho;

    public AVL() {
        this.tamanho = 0;
    }
    
    public void inserir(K chave, E valor) {
        if (raiz == null) {
            raiz = new No<>(chave, valor);
        }else {
            No<K, E> no = this.raiz;
            boolean encontrou = false;
            do {
                if (chave.compareTo(no.chave) > 0) {
                    if (no.direito == null) {  
                        no.direito = new No<>(chave, valor, no);
                        tamanho++;
                        atualizarPesos(no, 1);
                        balancear(no);
                        encontrou = true;
                    }else {
                        no = no.direito;
                    }
                }
                else if (chave.compareTo(no.chave) < 0) {
                    if (no.esquerdo == null) {  
                        no.esquerdo = new No<>(chave, valor, no);
                        tamanho++;
                        atualizarPesos(no, -1);
                        balancear(no);
                        encontrou = true;
                    }else {
                        no = no.esquerdo;
                    }
                }else {
                    no.valor = valor;
                    encontrou = true;
                }
            } while (!encontrou);   
        }
    }
    
    public void atualizarPesos(No<K, E> inicial, int peso) {
        No<K, E> no = inicial;
        while (no != raiz) {
            no.peso += peso;
            no = no.pai;
        }
        no.peso += peso;
    }
    
    public void balancear(No<K, E> inicial) {
        No<K, E> no = inicial;
        if (no.peso > 1) {
            No<K, E> direito = no.direito;
            
        }
        
    }
}

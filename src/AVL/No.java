package AVL;

/**
 *
 * @author FlowOverStack
 */
public class No<K extends Comparable<K>, E> {
    No<K, E> esquerdo;
    No<K, E> direito;
    No<K, E> pai;
    K chave;
    E valor;
    int peso;

    public No(K chave, E valor) {
        this.peso = 0;
        this.chave = chave;
        this.valor = valor;
    }
    
    public No(K chave, E valor, No<K, E> pai) {
        this.peso = 0;
        this.chave = chave;
        this.valor = valor;
        this.pai = pai;
    }
}

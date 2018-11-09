package AVL;

/**
 *
 * @author FlowOverStack
 */
public class No<K extends Comparable<K>, V> {
    No<K, V> esquerdo;
    No<K, V> direito;
    No<K, V> pai;
    K chave;
    V valor;
    int altura;

    public No(K chave, V valor) {
        this.altura = 1;
        this.chave = chave;
        this.valor = valor;
    }
    
    public No(K chave, V valor, No<K, V> pai) {
        this.altura = 1;
        this.chave = chave;
        this.valor = valor;
        this.pai = pai;
    }
}

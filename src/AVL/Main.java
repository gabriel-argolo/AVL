package AVL;

/**
 *
 * @author FlowOverStack
 */
public class Main {
    public static void main(String[] args) {
        AVL<Integer, Integer> arvore = new AVL<>();
        
        arvore.inserir(20, 20);
        arvore.inserir(10, 10);
        arvore.inserir(40, 40);
        arvore.inserir(50, 50);
        
        
        
        
        for (No<Integer, Integer> no: arvore.listarOrdem()) {
            if (no.pai != null) {
                System.out.println(no.valor+", Pai:"+no.pai.valor + ", Peso:"+no.altura);
            }else {
                System.out.println(no.valor+", Raiz"+ ", Peso:"+no.altura);
            }
        }
        
        arvore.remover(20);
        
        for (No<Integer, Integer> no: arvore.listarOrdem()) {
            if (no.pai != null) {
                System.out.println(no.valor+", Pai:"+no.pai.valor + ", Peso:"+no.altura);
            }else {
                System.out.println(no.valor+", Raiz"+ ", Peso:"+no.altura);
            }
        }
    }
}

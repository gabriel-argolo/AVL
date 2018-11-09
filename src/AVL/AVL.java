package AVL;

import java.util.ArrayList;

/**
 *
 * @author FlowOverStack
 */
public class AVL<K extends Comparable<K>, V> {
    No<K, V> raiz;
    int tamanho;

    public AVL() {
        this.tamanho = 0;
    }
    
    public int altura(No<K, V> no) {
        if (no == null) { 
            return 0; 
        }
        return no.altura; 
    }
    
    public void inserir(K chave, V valor) {
        if (raiz == null) {
            raiz = new No<>(chave, valor);
            tamanho++;
        }else {
            No<K, V> no = this.raiz;
            boolean encontrou = false;
            do {
                if (chave.compareTo(no.chave) > 0) {
                    if (no.direito == null) {  
                        no.direito = new No<>(chave, valor, no);
                        tamanho++;
                        no.direito.altura = Math.max(altura(no.esquerdo), altura(no.direito))+1;
                        balancear(no.direito);
                        encontrou = true;
                    }else {
                        no = no.direito;
                    }
                }
                else if (chave.compareTo(no.chave) < 0) {
                    if (no.esquerdo == null) {  
                        no.esquerdo = new No<>(chave, valor, no);
                        tamanho++;
                        no.esquerdo.altura = Math.max(altura(no.esquerdo), altura(no.direito))+1;
                        balancear(no.esquerdo);
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
    
    public No<K, V> buscar(K chave) {
        No<K, V> no = raiz;
        boolean encontrou = false;
        do {
            if (no == null) {
                encontrou = true;
            } else if (chave.compareTo(no.chave) > 0) {
                no = no.direito;
            } else if (chave.compareTo(no.chave) < 0) {
                no = no.esquerdo;
            } else {
                encontrou = true;
            }
        } while (!encontrou);
        return no;
    }
    
    public V valorDe(K chave) {
        No<K, V> no = raiz;
        boolean encontrou = false;
        do {
            if (no == null) {
                encontrou = true;
            } else if (chave.compareTo(no.chave) > 0) {
                no = no.direito;
            } else if (chave.compareTo(no.chave) < 0) {
                no = no.esquerdo;
            } else {
                encontrou = true;
            }
        } while (!encontrou);
        return no.valor;
    }
    
    public void remover(K chave) {
        No<K, V> no = buscar(chave);
        if (no != null) {
            if (no.esquerdo == null && no.direito == null) {
                if (no != raiz) {
                    if (no.chave.compareTo(no.pai.chave) > 0) {
                        no.pai.direito = null;

                    } else {
                        no.pai.esquerdo = null;
                    }
                    tamanho--;
                } else {
                    raiz = null;
                    tamanho--;
                }
            } else if (no.direito != null && no.esquerdo == null) {
                if (no != raiz) {
                    if (no.chave.compareTo(no.pai.chave) > 0) {
                        no.direito.pai = no.pai;
                        no.pai.direito = no.direito;
                    } else {
                        no.direito.pai = no.pai;
                        no.pai.esquerdo = no.direito;
                    }
                } else {
                    no.direito.pai = null;
                    raiz = no.direito;
                }
            } else if (no.direito == null && no.esquerdo != null) {
                if (no != raiz) {
                    if (no.chave.compareTo(no.pai.chave) > 0) {
                        no.esquerdo.pai = no.pai;
                        no.pai.direito = no.esquerdo;
                    } else {
                        no.esquerdo.pai = no.pai;
                        no.pai.esquerdo = no.esquerdo;
                    }
                } else {
                    no.esquerdo.pai = null;
                    raiz = no.direito;
                }
            } else {
                No<K, V> proximo = no.direito;
                
                while (proximo.esquerdo != null) {
                    proximo = proximo.esquerdo;
                }
                
                remover(proximo.chave);
                no = buscar(no.chave);
                
                if (no == raiz) {
                    raiz = proximo;
                    proximo.pai = null;
                    proximo.esquerdo = no.esquerdo;
                    proximo.direito = no.direito;
                    no.direito.pai = proximo;
                    no.esquerdo.pai = proximo;
                }else {
                    proximo.pai = no.pai;
                    proximo.esquerdo = no.esquerdo;
                    proximo.direito = no.direito;
                    no.direito.pai = proximo;
                    no.esquerdo.pai = proximo;
                }
            }
        }
        
        no.altura = Math.max(altura(no.esquerdo), altura(no.direito)) + 1; 
        balancear(no);
    }
    
    public int balanceamento(No<K, V> no) {
        if (no == null) {
            return 0;
        }else {
            return altura(no.esquerdo) - altura(no.direito); 
        }
    }
    
    public void balancear(No<K, V> inicial) {
        No<K, V> no = inicial;
        if (balanceamento(no) < -1) {
            if (no.chave.compareTo(no.direito.chave) < 1) {
                rotacaoEsquerda(no);
            }else {
                rotacaoDireita(no.direito);               
                rotacaoEsquerda(no);
            }
        }else if (balanceamento(no) < -1) {
            if (no.chave.compareTo(no.direito.chave) > 1) {
                rotacaoDireita(no);
            }else {
                rotacaoEsquerda(no.esquerdo);
                rotacaoDireita(no);
            }
        }
        if (no.pai != null) {
            balancear(no.pai);
        }
    }
    
    public void rotacaoEsquerda(No<K, V> pivo) {
        No<K, V> filho = pivo.direito;
        
        if (pivo != raiz) {
            if (pivo.pai.esquerdo == pivo) {
                pivo.pai.esquerdo = filho;
            }else {
               pivo.pai.direito = filho;
            }
            filho.pai = pivo.pai;
        }else {
            filho.pai = null;
            raiz = filho;
        }
        
        pivo.pai = filho;
        pivo.direito = filho.esquerdo;
        filho.esquerdo = pivo;
        
        pivo.altura = Math.max(altura(pivo.esquerdo), altura(pivo.direito)) + 1; 
        filho.altura = Math.max(altura(filho.esquerdo), altura(filho.direito)) + 1; 
    }
    
    public void rotacaoDireita(No<K, V  > pivo) {
        No<K, V> filho = pivo.esquerdo;
        
        if (pivo != raiz) {
            if (pivo.pai.esquerdo == pivo) {
                pivo.pai.esquerdo = filho;
            }else {
               pivo.pai.direito = filho;
            }
            filho.pai = pivo.pai;
        }else {
            filho.pai = null;
            raiz = filho;
        }
        
        pivo.pai = filho;
        pivo.esquerdo = filho.direito;
        filho.direito = pivo;
        
        pivo.altura = Math.max(altura(pivo.esquerdo), altura(pivo.direito)) + 1; 
        filho.altura = Math.max(altura(filho.esquerdo), altura(filho.direito)) + 1; 
    }
    
    ArrayList<No<K, V>> listarOrdem() {
        ArrayList<No<K, V>> lista = new ArrayList<>();
        listarOrdem(raiz, lista);
        return lista;
    }
    
    void listarOrdem(No<K, V> no, ArrayList<No<K, V>> lista) {
        if (no != null) {
            listarOrdem(no.esquerdo, lista);
            lista.add(no);
            listarOrdem(no.direito, lista);
        }
    }
}

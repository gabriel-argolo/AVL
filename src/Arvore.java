
import java.util.ArrayList;

public class Arvore {

    No raiz;

    public void inserir(int i) {
        No n = new No(i);
        inserir(this.raiz, n);
    }

    public void inserir(No compare, No insere) {
        if (compare == null) {
            this.raiz = insere;
        } else {
            if (insere.getChave() < compare.getChave()) {
                if (compare.getEsq() == null) {
                    compare.setEsq(insere);
                    insere.setPai(compare);
                    balanceada(compare);
                } else {
                    inserir(compare.getEsq(), insere);
                }
            } else if (insere.getChave() > compare.getChave()) {
                if (compare.getDir() == null) {
                    compare.setDir(insere);
                    insere.setPai(compare);
                    balanceada(compare);
                } else {
                    inserir(compare.getDir(), insere);
                }
            } else {
                System.out.println("n� existente");
            }
        }
    }

    public void balanceada(No atual) {
        setBalanceamento(atual);
        int fCarga = atual.getBlnc();
        if (fCarga == -2) {
            if (altura(atual.getEsq().getEsq()) >= altura(atual.getEsq().getDir())) {
                atual = rotacionarD(atual);
            } else {
                atual = rotacaoDuplaED(atual);
            }
        } else if (fCarga == 2) {
            if (altura(atual.getDir().getDir()) >= altura(atual.getDir().getEsq())) {
                atual = rotacionarE(atual);
            } else {
                atual = rotacaoDuplaDE(atual);
            }
        }
        if (atual.getPai() != null) {
            balanceada(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }

    public void remover(int i) {
        remover(this.raiz, i);
    }

    public void remover(No atual, int i) {
        if (atual == null) {
            return;
        } else {
            if (atual.getChave() > i) {
                remover(atual.getEsq(), i);
            } else if (atual.getChave() < i) {
                remover(atual.getDir(), i);
            } else if (atual.getChave() == i) {
                removerNo(atual);
            }
        }
    }

    public void removerNo(No remover) {
        No n;
        if (remover.getEsq() == null || remover.getDir() == null) {
            if (remover.getPai() == null) {
                this.raiz = null;
                remover = null;
                return;
            }
            n = remover;
        } else {
            n = proximo(remover);
            remover.setChave(n.getChave());
        }
        No p;
        if (n.getEsq() != null) {
            p = n.getEsq();
        } else {
            p = n.getDir();
        }
        if (p != null) {
            p.setPai(n.getPai());
        }
        if (n.getPai() == null) {
            this.raiz = p;
        } else {
            if (n == n.getPai().getEsq()) {
                n.getPai().setEsq(p);
            } else {
                n.getPai().setDir(p);
            }
            balanceada(n.getPai());
        }
        n = null;
    }

    public No rotacionarE(No i) {
        No direito = i.getDir();
        direito.setPai(i.getPai());
        i.setDir(direito.getEsq());

        if (i.getDir() != null) {
            i.getDir().setPai(i);
        }
        direito.setEsq(i);
        i.setPai(direito);
        if (direito.getPai() != null) {
            if (direito.getPai().getDir() == i) {
                direito.getPai().setDir(direito);
            } else if (direito.getPai().getEsq() == i) {
                direito.getPai().setEsq(direito);
            }
        }
        setBalanceamento(i);
        setBalanceamento(direito);
        return direito;
    }

    public No rotacionarD(No i) {
        No esquerdo = i.getEsq();
        esquerdo.setPai(i.getPai());
        i.setEsq(esquerdo.getDir());
        if (i.getEsq() != null) {
            i.getEsq().setPai(i);
        }
        esquerdo.setDir(i);
        i.setPai(esquerdo);
        if (esquerdo.getPai() != null) {
            if (esquerdo.getPai().getDir() == i) {
                esquerdo.getPai().setDir(esquerdo);
            } else if (esquerdo.getPai().getEsq() == i) {
                esquerdo.getPai().setEsq(esquerdo);
            }
        }
        setBalanceamento(i);
        setBalanceamento(esquerdo);
        return esquerdo;
    }

    public No rotacaoDuplaED(No i) {
        i.setEsq(rotacionarE(i.getEsq()));
        return rotacionarD(i);
    }

    public No rotacaoDuplaDE(No i) {
        i.setDir(rotacionarD(i.getDir()));
        return rotacionarE(i);
    }

    public No proximo(No n) {
        if (n.getDir() != null) {
            No r = n.getDir();
            while (r.getEsq() != null) {
                r = r.getEsq();
            }
            return r;
        } else {
            No p = n.getPai();
            while (p != null && n == p.getDir()) {
                n = p;
                p = n.getPai();
            }
            return p;
        }
    }

    private int altura(No atual) {
        if (atual == null) {
            return -1;
        }
        if (atual.getEsq() == null && atual.getDir() == null) {
            return 0;
        } else if (atual.getEsq() == null) {
            return 1 + altura(atual.getDir());
        } else if (atual.getDir() == null) {
            return 1 + altura(atual.getEsq());
        } else {
            return 1 + Math.max(altura(atual.getEsq()), altura(atual.getDir()));
        }
    }

    private void setBalanceamento(No no) {
        no.setBlnc(altura(no.getDir()) - altura(no.getEsq()));
    }

    final protected ArrayList<No> ordenar() {
        ArrayList<No> list = new ArrayList<No>();
        ordenar(raiz, list);
        return list;
    }

    final protected void ordenar(No no, ArrayList<No> array) {
        if (no == null) {
            return;
        }
        ordenar(no.getEsq(), array);
        array.add(no);
        ordenar(no.getDir(), array);
    }
}

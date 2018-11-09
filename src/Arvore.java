import java.util.ArrayList;

public class Arvore {

	protected No raiz;

	public void inserir(int k) {
		No n = new No(k);
		inserir(this.raiz, n);
	}

	public void inserir(No compare, No insere) {
		if (compare == null) {
			this.raiz = insere;
		} else {
			if (insere.getChave() < compare.getChave()) {
				if (compare.getEsquerda() == null) {
					compare.setEsquerda(insere);
					insere.setPai(compare);
					balanceada(compare);
				} else {
					inserir(compare.getEsquerda(), insere);
				}
			} else if (insere.getChave() > compare.getChave()) {
				if (compare.getDireita() == null) {
					compare.setDireita(insere);
					insere.setPai(compare);
					balanceada(compare);
				} else {
					inserir(compare.getDireita(), insere);
				}
			} else {
				//nó existente
			}
		}
	}

	public void balanceada(No atual) {
		setBalanceamento(atual);
		int fCarga = atual.getBlnc();
		if (fCarga == -2) {
			if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
				atual = rotacaoDireita(atual);
			} else {
				atual = duplaRotacaoEsquerdaDireita(atual);
			}
		} else if (fCarga == 2) {
			if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
				atual = rotacaoEsquerda(atual);
			} else {
				atual = duplaRotacaoDireitaEsquerda(atual);
			}
		}
		if (atual.getPai() != null) {
			balanceada(atual.getPai());
		} else {
			this.raiz = atual;
		}
	}

	public void remover(int k) {
		remover(this.raiz, k);
	}

	public void remover(No atual, int k) {
		if (atual == null) {
			return;
		} else {
			if (atual.getChave() > k) {
				remover(atual.getEsquerda(), k);
			} else if (atual.getChave() < k) {
				remover(atual.getDireita(), k);
			} else if (atual.getChave() == k) {
				removerNo(atual);
			}
		}
	}

	public void removerNo(No remover) {
		No n;
		if (remover.getEsquerda() == null || remover.getDireita() == null) {
			if (remover.getPai() == null) {
				this.raiz = null;
				remover = null;
				return;
			}
			n = remover;
		} else {
			n = sucessor(remover);
			remover.setChave(n.getChave());
		}
		No p;
		if (n.getEsquerda() != null) {
			p = n.getEsquerda();
		} else {
			p = n.getDireita();
		}
		if (p != null) {
			p.setPai(n.getPai());
		}
		if (n.getPai() == null) {
			this.raiz = p;
		} else {
			if (n == n.getPai().getEsquerda()) {
				n.getPai().setEsquerda(p);
			} else {
				n.getPai().setDireita(p);
			}
			balanceada(n.getPai());
		}
		n = null;
	}

	public No rotacaoEsquerda(No inicial) {
		No direito = inicial.getDireita();
		direito.setPai(inicial.getPai());
		inicial.setDireita(direito.getEsquerda());

		if (inicial.getDireita() != null) {
			inicial.getDireita().setPai(inicial);
		}
		direito.setEsquerda(inicial);
		inicial.setPai(direito);
		if (direito.getPai() != null) {
			if (direito.getPai().getDireita() == inicial) {
				direito.getPai().setDireita(direito);
			} else if (direito.getPai().getEsquerda() == inicial) {
				direito.getPai().setEsquerda(direito);
			}
		}
		setBalanceamento(inicial);
		setBalanceamento(direito);
		return direito;
	}

	public No rotacaoDireita(No inicial) {
		No esquerdo = inicial.getEsquerda();
		esquerdo.setPai(inicial.getPai());
		inicial.setEsquerda(esquerdo.getDireita());
		if (inicial.getEsquerda() != null) {
			inicial.getEsquerda().setPai(inicial);
		}
		esquerdo.setDireita(inicial);
		inicial.setPai(esquerdo);
		if (esquerdo.getPai() != null) {
			if (esquerdo.getPai().getDireita() == inicial) {
				esquerdo.getPai().setDireita(esquerdo);
			} else if (esquerdo.getPai().getEsquerda() == inicial) {
				esquerdo.getPai().setEsquerda(esquerdo);
			}
		}
		setBalanceamento(inicial);
		setBalanceamento(esquerdo);
		return esquerdo;
	}

	public No duplaRotacaoEsquerdaDireita(No inicial) {
		inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
		return rotacaoDireita(inicial);
	}

	public No duplaRotacaoDireitaEsquerda(No inicial) {
		inicial.setDireita(rotacaoDireita(inicial.getDireita()));
		return rotacaoEsquerda(inicial);
	}

	public No sucessor(No n) {
		if (n.getDireita() != null) {
			No r = n.getDireita();
			while (r.getEsquerda() != null) {
				r = r.getEsquerda();
			}
			return r;

		} else {
			No p = n.getPai();
			while (p != null && n == p.getDireita()) {
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
		if (atual.getEsquerda() == null && atual.getDireita() == null) {
			return 0;
		} else if (atual.getEsquerda() == null) {
			return 1 + altura(atual.getDireita());
		} else if (atual.getDireita() == null) {
			return 1 + altura(atual.getEsquerda());
		} else {
			return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
		}
	}

	private void setBalanceamento(No no) {
		no.setBlnc(altura(no.getDireita()) - altura(no.getEsquerda()));
	}

	final protected ArrayList<No> inorder() {
		ArrayList<No> ret = new ArrayList<No>();
		inorder(raiz, ret);
		return ret;
	}

	final protected void inorder(No no, ArrayList<No> lista) {
		if (no == null) {
			return;
		}
		inorder(no.getEsquerda(), lista);
		lista.add(no);
		inorder(no.getDireita(), lista);
	}
}
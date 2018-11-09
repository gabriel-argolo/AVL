public class No {
  
	private No esquerda;
	private No direita;
	private No pai;
	private int chave;
	private int blnc;

	public No(int k) {
		setEsquerda(setDireita(setPai(null)));
		setBlnc(0);
		setChave(k);
	}
	
	
	@Override
	public String toString() {
		return Integer.toString(getChave());
	}

	public int getChave() {
		return chave;
	}

	public void setChave(int chave) {
		this.chave = chave;
	}

	public int getBlnc() {
		return blnc;
	}

	public void setBlnc(int blnc) {
		this.blnc = blnc;
	}

	public No getPai() {
		return pai;
	}

	public No setPai(No pai) {
		this.pai = pai;
		return pai;
	}

	public No getDireita() {
		return direita;
	}

	public No setDireita(No direita) {
		this.direita = direita;
		return direita;
	}

	public No getEsquerda() {
		return esquerda;
	}

	public void setEsquerda(No esquerda) {
		this.esquerda = esquerda;
	}	
}
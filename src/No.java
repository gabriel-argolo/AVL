public class No {
  
	private No esq;
	private No dir;
	private No pai;
	private int chave;
	private int blnc;

	public No(int i) {
		setEsq(setDir(setPai(null)));
		setBlnc(0);
		setChave(i);
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

	public No getDir() {
		return dir;
	}

	public No setDir(No direito) {
		this.dir = direito;
		return direito;
	}

	public No getEsq() {
		return esq;
	}

	public void setEsq(No esquerdo) {
		this.esq = esquerdo;
	}	
}
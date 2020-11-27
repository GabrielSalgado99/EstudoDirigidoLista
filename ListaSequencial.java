package Lista;

public class ListaSequencial<T extends Comparable> extends Lista<T> {

	private Object[] lista = new Object[10];
	private int posicao = 0;

	@Override
	public void incluir(T elemento) {
		if (posicao == lista.length) {
			expandir();
		}
		lista[posicao] = elemento;
		posicao++;
	}

	@Override
	public void incluirInicio(T elemento) {
		if (posicao == lista.length) {
			expandir();
		}
		for (int i = posicao; i >= 0; i--) {
			lista[i + 1] = lista[i];
		}
		lista[0] = elemento;
		posicao++;
	}

	@Override
	public void incluir(T elemento, int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > this.posicao) {
			throw new PosicaoInvalidaException();
		}
		if (this.posicao == lista.length) {
			expandir();
		}
		if (posicao == 1) {
			incluirInicio(elemento);
		} else {
			posicao--;
			for (int i = this.posicao; i > posicao; i++) {
				lista[i + 1] = lista[i];
			}
			lista[posicao] = elemento;
			this.posicao++;
		}

	}

	@Override
	public T get(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > this.posicao) {
			throw new PosicaoInvalidaException();
		}
		return (T) lista[posicao - 1];
	}

	@Override
	public int get(T elemento) throws ElementoInvalidoException {
		for (int i = 0; i < lista.length; i++) {
			if (lista[i].equals(elemento)) {
				return i;
			}
		}
		throw new ElementoInvalidoException();
	}

	@Override
	public void remover(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > lista.length) {
			throw new PosicaoInvalidaException();
		}
		posicao--;
		for (int i = posicao; i < this.posicao; i++) {
			lista[i] = lista[i + 1];
		}
		this.posicao--;
	}

	@Override
	public void limpar() {
		lista = new Object[50];
		posicao = 0;
	}

	@Override
	public int getTamanho() {
		return posicao;
	}

	@Override
	public boolean contem(T elemento) {
		for (int i = 0; i < lista.length; i++) {
			if (lista[i].equals(elemento)) {
				return true;
			}
		}
		return false;
	}

	public void expandir() {
		Object[] listaAux = new Object[(lista.length + 50)];
		for (int i = 0; i < lista.length; i++) {
			listaAux[i] = lista[i];
		}
		lista = listaAux;
	}

}

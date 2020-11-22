package Lista;

public class ListaDupEnc<T extends Comparable<T>> extends Lista<T> {

	private No inicio;
	private No fim;
	private int tamanho = 0;

	public void incluir(T elemento) {
		No novo = new No(elemento);
		if (inicio == null) {
			inicio = novo;
			tamanho++;
		} else if (fim == null) {
			novo.setAnterior(inicio);
			inicio.setProximo(novo);
			fim = novo;
			tamanho++;
		} else {
			fim.setProximo(novo);
			novo.setAnterior(fim);
			fim = novo;
			tamanho++;
		}
	}

	public void incluirInicio(T elemento) {
		No novo = new No(elemento);
		if (inicio == null) {
			novo.setProximo(fim);
			inicio = novo;
			tamanho++;
		} else if (fim == null) {
			inicio.setAnterior(novo);
			novo.setProximo(inicio);
			fim = inicio;
			inicio = novo;
			tamanho++;
		} else {
			inicio.setAnterior(novo);
			novo.setProximo(inicio);
			inicio = novo;
			tamanho++;
		}
	}

	public void incluir(T elemento, int posicao) throws PosicaoInvalidaException {
		No novo = new No(elemento);
		if (posicao == 1) {
			if (inicio == null) {
				novo.setProximo(fim);
				inicio = novo;
				tamanho++;
			} else if (fim == null) {
				novo.setProximo(inicio);
				inicio.setAnterior(novo);
				fim = inicio;
				inicio = novo;
				tamanho++;
			} else {
				inicio.setAnterior(novo);
				novo.setProximo(inicio);
				inicio = novo;
				tamanho++;
			}
		} else if (posicao == tamanho && tamanho > 0) {
			if (fim == null) {
				fim.setAnterior(inicio);
				inicio.setProximo(fim);
				fim = novo;
				tamanho++;
			} else {
				fim.setProximo(novo);
				novo.setAnterior(fim);
				fim = novo;
				tamanho++;
			}
		} else if (posicao <= 0 || posicao > tamanho) {
			throw new PosicaoInvalidaException();
		} else {
			if (posicao < tamanho / 2) {
				No inclusor = inicio;
				for (int i = 0; i < posicao; i++) {
					inclusor = inclusor.getProximo();
				}
				novo.setAnterior(inclusor.getAnterior());
				novo.setProximo(inclusor);
				inclusor.setAnterior(novo);
				tamanho++;
			} else {
				No inclusor = fim;
				for (int i = 0; i < posicao; i++) {
					inclusor = inclusor.getAnterior();
				}
				novo.setAnterior(inclusor.getAnterior());
				novo.setProximo(inclusor);
				inclusor.setAnterior(novo);
				tamanho++;
			}
		}
	}

	public T get(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > tamanho) {
			throw new PosicaoInvalidaException();
		} else if (posicao == 1) {
			return (T) inicio.getInfo();
		} else if (posicao == tamanho) {
			return (T) fim.getInfo();
		} else {
			if (posicao < tamanho / 2) {
				No retorno = inicio;
				for (int i = 1; i < posicao; i++) {
					retorno = retorno.getProximo();
				}
				return (T) retorno.getInfo();
			} else {
				No retorno = fim;
				for (int i = tamanho; i > posicao; i--) {
					retorno = retorno.getAnterior();
				}
				return (T) retorno.getInfo();
			}
		}

	}

	public int get(T elemento) throws ElementoInvalidoException {
		try {
			No percorrer = inicio;
			int posicao = 1;
			while (percorrer.getInfo() != elemento) {
				percorrer = percorrer.getProximo();
				posicao++;
			}
			return posicao;
		} catch (Exception e) {
			throw new ElementoInvalidoException();
		}
	}

	public void remover(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > tamanho) {
			throw new PosicaoInvalidaException();
		} else if (posicao == 1) {
			inicio = inicio.getProximo();
			tamanho--;
		} else if (posicao == tamanho) {
			fim = fim.getAnterior();
			fim.setProximo(null);
			tamanho--;
		} else {
			if (posicao < tamanho / 2) {
				No excluir = inicio;
				for (int i = 0; i < posicao; i++) {
					excluir = excluir.getProximo();
				}
				excluir.getAnterior().setProximo(excluir.getProximo());
				excluir.getProximo().setAnterior(excluir.getAnterior());
				tamanho--;
			} else {
				No excluir = fim;
				for (int i = tamanho; i > posicao; i--) {
					excluir = excluir.getAnterior();
				}
				excluir.getAnterior().setProximo(excluir.getProximo());
				excluir.getProximo().setAnterior(excluir.getAnterior());
				tamanho--;
			}
		}
	}

	public void limpar() {
		inicio = null;
		fim = null;
		tamanho = 0;
	}

	public int getTamanho() {
		return tamanho;
	}

	public boolean contem(T elemento) {
		No percorrer = inicio;
		while (percorrer != null) {
			if (percorrer.getInfo().equals(elemento)) {
				return true;
			}
			percorrer = percorrer.getProximo();
		}
		return false;
	}
}

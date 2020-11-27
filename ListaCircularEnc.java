package Lista;

public class ListaCircularEnc<T extends Comparable<T>> extends Lista<T> {

	private NoDup inicio;
	private NoDup fim;
	private int tamanho = 0;

	@Override
	public void incluir(T elemento) {
		NoDup novo = new NoDup(elemento);
		if (inicio == null) {
			inicio = novo;
			tamanho++;
		} else if (fim == null) {
			novo.setAnterior(inicio);
			inicio.setProximo(novo);
			fim = novo;
			inicio.setAnterior(fim);
			fim.setProximo(inicio);
			tamanho++;
		} else {
			fim.setProximo(novo);
			novo.setAnterior(fim);
			fim = novo;
			inicio.setAnterior(fim);
			fim.setProximo(inicio);
			tamanho++;
		}
	}

	@Override
	public void incluirInicio(T elemento) {
		NoDup novo = new NoDup(elemento);
		if (inicio == null) {
			novo.setProximo(fim);
			inicio = novo;
			tamanho++;
		} else if (fim == null) {
			inicio.setAnterior(novo);
			novo.setProximo(inicio);
			fim = inicio;
			inicio = novo;
			fim.setProximo(inicio);
			tamanho++;
		} else {
			inicio.setAnterior(novo);
			novo.setProximo(inicio);
			inicio = novo;
			inicio.setAnterior(fim);
			tamanho++;
		}
	}

	@Override
	public void incluir(T elemento, int posicao) throws PosicaoInvalidaException {
		NoDup novo = new NoDup(elemento);
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
				fim.setProximo(inicio);
				tamanho++;
			} else {
				inicio.setAnterior(novo);
				novo.setProximo(inicio);
				inicio = novo;
				inicio.setAnterior(fim);
				tamanho++;
			}
		} else if (posicao == tamanho && tamanho > 0) {
			if (fim == null) {
				fim = novo;
				fim.setAnterior(inicio);
				fim.setProximo(inicio);
				inicio.setProximo(fim);
				inicio.setAnterior(inicio);
				tamanho++;
			} else {
				fim.setProximo(novo);
				novo.setAnterior(fim);
				fim = novo;
				fim.setProximo(inicio);
				tamanho++;
			}
		} else if (posicao <= 0 || posicao > tamanho) {
			throw new PosicaoInvalidaException();
		} else {
			if (posicao < tamanho / 2) {
				NoDup inclusor = inicio;
				for (int i = 0; i < posicao; i++) {
					inclusor = inclusor.getProximo();
				}
				novo.setAnterior(inclusor.getAnterior());
				novo.setProximo(inclusor);
				inclusor.setAnterior(novo);
				tamanho++;
			} else {
				NoDup inclusor = fim;
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

	@Override
	public T get(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > tamanho) {
			throw new PosicaoInvalidaException();
		} else if (posicao == 1) {
			return (T) inicio.getInfo();
		} else if (posicao == tamanho) {
			return (T) fim.getInfo();
		} else {
			if (posicao < tamanho / 2) {
				NoDup retorno = inicio;
				for (int i = 1; i < posicao; i++) {
					retorno = retorno.getProximo();
				}
				return (T) retorno.getInfo();
			} else {
				NoDup retorno = fim;
				for (int i = tamanho; i > posicao; i--) {
					retorno = retorno.getAnterior();
				}
				return (T) retorno.getInfo();
			}
		}

	}

	@Override
	public int get(T elemento) throws ElementoInvalidoException {
		try {
			NoDup percorrer = inicio;
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

	@Override
	public void remover(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0 || posicao > tamanho) {
			throw new PosicaoInvalidaException();
		} else if (posicao == 1) {
			inicio = inicio.getProximo();
			inicio.setAnterior(fim);
			tamanho--;
		} else if (posicao == tamanho) {
			fim = fim.getAnterior();
			fim.setProximo(inicio);
			tamanho--;
		} else {
			if (posicao < tamanho / 2) {
				NoDup excluir = inicio;
				for (int i = 0; i < posicao; i++) {
					excluir = excluir.getProximo();
				}
				excluir.getAnterior().setProximo(excluir.getProximo());
				excluir.getProximo().setAnterior(excluir.getAnterior());
				tamanho--;
			} else {
				NoDup excluir = fim;
				for (int i = tamanho; i > posicao; i--) {
					excluir = excluir.getAnterior();
				}
				excluir.getAnterior().setProximo(excluir.getProximo());
				excluir.getProximo().setAnterior(excluir.getAnterior());
				tamanho--;
			}
		}
	}

	@Override
	public void limpar() {
		inicio = null;
		fim = null;
		tamanho = 0;
	}

	@Override
	public int getTamanho() {
		return tamanho;
	}

	@Override
	public boolean contem(T elemento) {
		NoDup percorrer = inicio;
		while (percorrer != null) {
			if (percorrer.getInfo().equals(elemento)) {
				return true;
			}
			percorrer = percorrer.getProximo();
		}
		return false;
	}

}

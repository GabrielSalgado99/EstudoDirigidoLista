package Lista;

public class ListaEnc<T extends Comparable<T>> extends Lista<T> {

	private No inicio;

	@Override
	public void incluir(T elemento) {
		No novo = new No(elemento);
		if (inicio == null) {
			inicio = novo;
		} else {
			No inclusor = inicio;
			while (inclusor.getProximo() != null) {
				inclusor = inclusor.getProximo();
			}
			inclusor.setProximo(novo);
		}
	}

	@Override
	public void incluirInicio(T elemento) {
		No novo = new No(elemento);
		novo.setProximo(inicio);
		inicio = novo;
	}

	@Override
	public void incluir(T elemento, int posicao) throws PosicaoInvalidaException {
		No novo = new No(elemento);
		if (posicao == 1) {
			novo.setProximo(inicio);
			inicio = novo;
		} else {
			if (posicao <= 0) {
				throw new PosicaoInvalidaException();
			}
			try {
				No inclusor = inicio;
				int posicaoatual = 1;
				while (posicaoatual != posicao - 1) {
					inclusor = inclusor.getProximo();
					posicaoatual++;
				}
				novo.setProximo(inclusor.getProximo());
				inclusor.setProximo(novo);
			} catch (Exception e) {
				throw new PosicaoInvalidaException();
			}
		}
	}

	@Override
	public T get(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0) {
			throw new PosicaoInvalidaException();
		}
		try {
			No elemento = inicio;
			int posicaoatual = 1;
			while (posicaoatual != posicao) {
				elemento = elemento.getProximo();
				posicaoatual++;
			}
			return (T) elemento.getInfo();
		} catch (Exception e) {
			throw new PosicaoInvalidaException();
		}
	}

	@Override
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

	@Override
	public void remover(int posicao) throws PosicaoInvalidaException {
		if (posicao <= 0) {
			throw new PosicaoInvalidaException();
		} else if (posicao == 1) {
			inicio = inicio.getProximo();
		} else {
			try {
				No elemento = inicio;
				int posicaoatual = 1;
				while (posicaoatual != posicao - 1) {
					elemento = elemento.getProximo();
					posicaoatual++;
				}
				elemento.setProximo(elemento.getProximo().getProximo());
			} catch (Exception e) {
				throw new PosicaoInvalidaException();
			}
		}
	}

	@Override
	public void limpar() {
		inicio = null;
	}

	@Override
	public int getTamanho() {
		if (inicio == null) {
			return 0;
		} else {
			No percorrer = inicio;
			int tamanho = 1;
			while (percorrer.getProximo() != null) {
				percorrer = percorrer.getProximo();
				tamanho++;
			}
			return tamanho;
		}
	}

	@Override
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

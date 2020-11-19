package Lista;

public abstract class Lista<T extends Comparable> {

	public void Lista() {
	}

	abstract public void incluir(T elemento) throws Exception;

	abstract public void incluirInicio(T elemento) throws Exception;

	abstract public void incluir(T elemento, int posicao) throws Exception;

	abstract public T get(int posicao) throws Exception;

	abstract public int get(T elemento) throws Exception;

	abstract public void remover(int posicao) throws Exception;

	abstract public void limpar();

	abstract public int getTamanho();

	abstract public boolean contem(T elemento) throws Exception;

}

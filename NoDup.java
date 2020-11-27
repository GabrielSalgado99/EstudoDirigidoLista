package Lista;

public class NoDup<T>{

	private T info;
	private NoDup proximo;
	private NoDup anterior;

	public NoDup(T elemento) {
		info = elemento;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public NoDup getProximo() {
		return proximo;
	}

	public void setProximo(NoDup proximo) {
		this.proximo = proximo;
	}

	public NoDup getAnterior() {
		return anterior;
	}

	public void setAnterior(NoDup anterior) {
		this.anterior = anterior;
	}

}

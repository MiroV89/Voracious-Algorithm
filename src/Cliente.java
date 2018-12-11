public class Cliente implements Comparable<Cliente> {
	int posicion,valor;
	public Cliente(int pos, int val){
		this.posicion=pos;
		this.valor=val;
	}
	public int getPosicion(){
		return posicion;
	}
	public int getValor(){
		return valor;		
	}
	@Override
    public int compareTo(Cliente cl) {
        if (valor < cl.valor) {
            return -1;
        }
        if (valor > cl.valor) {
            return 1;
        }
        return 0;
    }
	
}

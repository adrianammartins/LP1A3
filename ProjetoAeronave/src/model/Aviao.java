package model;

public class Aviao extends Aeronave {
	
	private Passageiro[][] lugares;

	public Aviao(String modelo, int linhas, int colunas) {
		super(modelo);
		this.lugares = new Passageiro[linhas][colunas];
	}
	
	public Passageiro getPassageiro(int linha, int coluna){
		if (lugares != null && lugares[linha][coluna] != null) {
			return lugares[linha][coluna];
		}else{
			return null;
		}
	}
	
	public void setPassageiro(int linha, int coluna, Passageiro passageiro){
		lugares[linha][coluna] = passageiro;
	}
	
	public boolean verificaLugarOcupado(int linha, int coluna){
		if (lugares[linha][coluna] != null) {
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	public Passageiro[][] getLugares() {
		return lugares;
	}
	
	
}

package model;

public class Aviao extends Aeronave {
	
	private Passageiro[][] lugares;

	public Aviao(String modelo, int linhas, int colunas) {
		super(modelo);
		this.lugares = new Passageiro[linhas][colunas];
	}
	
	//TODO TESTAR CASO SEJA INSERIDO UMA LINHA E COLUNA DIFERENTE DO PERMITIDO PELO LUGARES
	public Passageiro getPassageiro(int linha, int coluna){
		if (lugares != null && lugares[linha][coluna] != null) {
			return lugares[linha][coluna];
		}else{
			return null;
		}
	}
	
	//TODO TESTAR CASO COLOQUE UMA LINHA E COLUNA INEXISTENTE
	public void setPassageiro(int linha, int coluna, Passageiro passageiro){
		lugares[linha][coluna] = passageiro;
	}
	
	//TODO TESTAR CASO NAO EXISTA A LINHA E COLUNA
	public boolean verificaLugarOcupado(int linha, int coluna){
		if (lugares[linha][coluna] != null) {
			return true;
		}else{
			return false;
		}
	}
}

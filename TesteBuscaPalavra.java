public class TesteBuscaPalavra {

	public static void main(String [] args){

		BuscaPalavra bp = new BuscaPalavra();

		bp.carregaArquivo("texto.txt");
		
		String [] palavras = { "gustavo", "carro", "cobra", "amarelo", "dever", "mouse" };

		for(String p : palavras){

			String [] resultado = bp.busca(p);

			if(resultado != null){
		
				System.out.println("palavra " + resultado[0] + " encontrada."); 
			}
			else {

				System.out.println("palavra " + p + " nao encontrada."); 
			}
		}
	}
}

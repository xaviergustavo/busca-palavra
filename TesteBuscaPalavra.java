// Classe "usuária" da classe "BuscaPalavra".

public class TesteBuscaPalavra {

	public static void main(String [] args){

		// instanciação do objeto do tipo BuscaPalavra
		BuscaPalavra bp = new BuscaPalavra();

		// carga de um arquivo de texto
		bp.carregaArquivo("texto.txt");
		
		// lista de palavras a serem pesquisadas
		String [] palavras = { "carro", "cobra", "melão", "leão", "amarelo", "dever", "maçã" };

		for(String p : palavras){

			// chamada ao método "busca" para cada palavra pesquisada
			String [] resultado = bp.busca(p);

			// verificação do resultado da busca
			if(resultado != null){
		
				System.out.println("palavra " + resultado[0] + " encontrada."); 
			}
			else {

				System.out.println("palavra " + p + " NÃO encontrada."); 
			}
		}
	}
}

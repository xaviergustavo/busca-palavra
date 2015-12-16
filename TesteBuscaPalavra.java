public class TesteBuscaPalavra {

	public static void main(String [] args){

		BuscaPalavra bp = new BuscaPalavra();

		bp.carregaArquivo("texto.txt");
		
		String [] palavras = { "gustavo", "carro", "cobra", "amarelo", "dever", "mouse" };

		for(String p : palavras){

			String [] resultado = bp.busca(p);

			if(resultado != null){

				for(String s : resultado) {
					System.out.printf("%s ", s);
				}
				System.out.println();
			}
			else {

				System.out.println("palavra " + p + " nao encontrada."); 
			}
		}
	}
}

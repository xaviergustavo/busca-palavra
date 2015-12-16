import java.io.*;

class HashEntry {

	private String chave;
	private int valor;

	public HashEntry(String chave, int valor) {
		this.chave = chave;
		this.valor = valor;
	}

	public String getChave() {
		return chave;
	}

	public int getValor() {
		return valor;
	}

}

class HashMap {

	private static int tamanho;

	private HashEntry[] tabela;

	public HashMap(int tamanho) {
		this.tamanho = tamanho;
		tabela = new HashEntry[tamanho];
		for(int i = 0; i < tamanho; i++) {
			tabela[i] = null;
		}
	}

	public int get(String chave) {
		return tabela[]
	}

}

// classe que implementa um Buscador de palavras
public class BuscaPalavra{

	private String [] palavras = null;

	public BuscaPalavra(){

	}


	// metodo que faz a carga do arquivo de texto e guarda 
	// cada palavra presente no arquivo no vetor "palavras".
	// A carga do arquivo é feita em duas etapas. Na primeira
	// lemos todo o arquivo para determinar o número de palavras
	// presente no mesmo. Com o tamanho em mãos, alocamos o vetor
	// "palavras" com a quantidade certa de posições. Em seguida
	// é feita nova leitura, para guardar cada palavra presente
	// no arquivo no vetor "palavras".

	public void carregaArquivo(String nomeArquivo){

		try{
			String linha;
			String [] partes;
			int contador = 0;

			// abertura do arquivo para leitura 
			BufferedReader in = new BufferedReader(new FileReader(nomeArquivo));

			// leitura de uma linha por vez
			while( (linha = in.readLine()) != null ){

				// particionamento da linha em palavras
				partes = linha.split(" +");

				// atualização o contador de palavras
				contador += partes.length;
			}

			// alocação do vetor "palavras" com o tamanho correto
			palavras = new String[contador];

			// fechamento do arquivo...
			in.close();

			// ... e nova abertura para segunda etapa da carga
			in = new BufferedReader(new FileReader(nomeArquivo));
			contador = 0;

			// na segunda leitura, cada palavra 
			// lida é guardada no vo vetor
			while( (linha = in.readLine()) != null ){

				partes = linha.split(" +");

				for(String s : partes){

					palavras[contador] = s;
					contador++;
				}	
			}
		}
		catch(IOException e){

			System.out.println("Erro carregando arquivo '" + nomeArquivo + "'");
			e.printStackTrace();
		}
	}

	// método que verifica se uma palavra existe ou não no
	// conjunto de palavras carregadas do arquivo. Em caso
	// de busca bem sucedida, devolve um vetor de uma posição
	// contendo a palavra encontrada. Caso contrário devolve
	// null. Devolver um vetor de uma posição com a palavra
	// encontrada pode paracer meio estranho (bastaria devolver 
	// um boolean) mas facilitará a extensão deste método 
	// para também devolver os anagramas da palavra buscada.

	public String [] busca(String buscada){

		if(palavras != null){

			// para cada palavra presente no vetor "palavras"...
			for(String p : palavras){
	
				// ... se a palavra for igual à palavra buscada...
				if(p.equals(buscada)){

					// ... então a busca foi bem sucedida. Criamos
					// um vetor de uma posição e adicionamos ao mesmo
					// a palavra encontrada.
					String [] resultado = new String[1];
					resultado[0] = p;
					return resultado;
				}
			}
		}
		else{
			System.out.println("Arquivo não foi carregado!");	
		}
	
		// Se chegamos até aqui, ou o arquivo não foi carregado, ou 
		// a palavra buscada não existe. Então devolvemos "null".
		return null;
	}
}

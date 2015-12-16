import java.io.*;
import java.lang.Math;

class No {
	
	private String palavra;
	private No proximo;

	public No(String palavra) {
		this.palavra = palavra;
	}

	public void setProximo(No no) {
		proximo = no;
	}

	public No getProximo() {
		return proximo;
	}

	public String getPalavra() {
		return palavra;
	}
}

class Lista {
	
	private No inicio;

	public Lista() {
		inicio = null;
	}

	public void adiciona(String palavra) {

		No novo = new No(palavra);
		
		if(inicio == null) {
			inicio = novo;
		}
		else {
			No ultimo = inicio;
			while(ultimo.getProximo() != null) {
				ultimo = ultimo.getProximo();
			}
			ultimo.setProximo(novo);
		}
	}
	
	public String get(String palavra) {
		No n = inicio;
		while(n != null) {
			if(n.getPalavra().equals(palavra)) {
				return palavra;
			}
			n = n.getProximo();
		}
		return "";
	}
}

class HashEntry {

	private int chave;
	private Lista palavras;

	public HashEntry() {
		this.palavras = new Lista();
	}

	public int getChave() {
		return chave;
	}

	public Lista getPalavras() {
		return palavras;
	}
	
	public void setPalavras(Lista palavras) {
		this.palavras = palavras;
	}

}

class HashMap {

	private static int tamanho;

	private HashEntry[] tabela;

	public HashMap(int tamanho) {
		this.tamanho = tamanho;
		tabela = new HashEntry[tamanho];
		for(int i = 0; i < tamanho; i++) {
			tabela[i] = new HashEntry();
		}
	}
	
	private int hash(String palavra) {
		return Math.abs(palavra.hashCode() % tamanho);
	}

	public String get(String palavra) {
		int hash = hash(palavra);
		return tabela[hash].getPalavras().get(palavra);
	}

	public void put(String palavra) {
		int hash = hash(palavra);
		tabela[hash].getPalavras().adiciona(palavra);		
	}

}

// classe que implementa um Buscador de palavras
public class BuscaPalavra{

	private HashMap palavras = null;

	public BuscaPalavra(){

	}

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

				// atualizacao o contador de palavras
				contador += partes.length;
			}

			// alocacao do vetor "palavras" com o tamanho correto
			palavras = new HashMap(contador + 1);

			// fechamento do arquivo...
			in.close();

			// ... e nova abertura para segunda etapa da carga
			in = new BufferedReader(new FileReader(nomeArquivo));
			contador = 0;

			// na segunda leitura, cada palavra 
			// lida eh guardada no vo vetor
			while( (linha = in.readLine()) != null ){

				partes = linha.split(" +");

				for(String s : partes){

					palavras.put(s);
					contador++;
				}	
			}
		}
		catch(IOException e){

			System.out.println("Erro carregando arquivo '" + nomeArquivo + "'");
			e.printStackTrace();
		}
	}
	
	public String [] busca(String buscada){

		if(palavras != null){
		
			String palavra = palavras.get(buscada);
				
			if(palavra.equals(buscada)) {
					
				String [] resultado = new String[1];
				resultado[0] = palavra;
				return resultado;
			}
		}
		else{
			System.out.println("Arquivo nao foi carregado!");	
		}
	
		return null;
	}
}

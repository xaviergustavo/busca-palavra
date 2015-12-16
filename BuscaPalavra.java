// Nome: Gustavo Xavier Moreira
// NUSP: 8623757

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
	private int tamanho;

	public Lista() {
		inicio = null;
		tamanho = 0;
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
		tamanho++;
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

	public String[] palavras() {
		
		String[] palavras = new String[tamanho];
		
		No n = inicio;
		int i = 0;
		
		while(n != null) {
			palavras[i] = n.getPalavra();
			n = n.getProximo();
			i++;	
		}
		
		return palavras;
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
	
	public int particiona(char[] a, int ini, int fim) {
		int x = a[ini];
		int i = ini - 1;
		int j = fim + 1;
		while(true) {
			do {
				j--;
			} while(a[j] > x);
			do {
				i++;
			} while(a[i] < x);
			if(i < j) {
				char temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
			else return j;
		}
	}

	public void quickSortRec(char[] a, int ini, int fim) {
		if (ini < fim) {
			int q = particiona(a, ini, fim);
			quickSortRec(a, ini, q);
			quickSortRec(a, q + 1, fim);
		}
	}

	public void ordenar(char[] a) {
		quickSortRec(a, 0, a.length - 1);
	}

	public boolean arraysIguais(char[] c1, char[] c2) {
		if(c1.length != c2.length) return false;

		for(int i = 0; i < c1.length; i++) {
			if(c1[i] != c2[i]) {
				return false;
			}
		}

		return true;
	}

	public boolean isAnagrama(String s1, String s2) {
		char[] c1 = s1.toLowerCase().toCharArray();
		char[] c2 = s2.toLowerCase().toCharArray();

		ordenar(c1);
		ordenar(c2);

		return arraysIguais(c1, c2);
	}

	public String[] anagramas(String palavra) {	

		int tamanho = 0;
		for(HashEntry e : tabela) {
			Lista l = e.getPalavras();
			String[] palavras = l.palavras();
			for(String p : palavras) {
				if(!p.equals(palavra) && isAnagrama(palavra, p)) {
					tamanho++;
				}
			}
		}
		
		if(tamanho == 0) return null;
		
		String[] anagramas = new String[tamanho];
		
		int i = 0;
		for(HashEntry e : tabela) {
			Lista l = e.getPalavras();
			String[] palavras = l.palavras();
			for(String p : palavras) {
				if(!p.equals(palavra) && isAnagrama(palavra, p)) {
					anagramas[i] = p;
					i++;
				}
			}
		}

		return anagramas;
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

			// alocacao do hashMap "palavras" com o tamanho correto
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
		
			String[] resultado = null;

			String[] anagramas = palavras.anagramas(buscada);
			
			String palavra = palavras.get(buscada);
			
			if(anagramas != null) {
				
				int count = 0;
				
				if(palavra.equals(buscada)) {
					int tamanho = anagramas.length + 1;
					resultado = new String[tamanho];
					resultado[0] = palavra;
					for(int i = 0; i < anagramas.length; i++) {
						resultado[i+1] = anagramas[i];
					}
					return resultado;
				}
				else {
					return anagramas;
				}
			}
			else {
				if(palavra.equals(buscada)) {
					resultado = new String[1];
					resultado[0] = palavra;
					return resultado;
				}
			}
		}
		else{
			System.out.println("Arquivo nao foi carregado!");	
		}
	
		return null;
	}
}

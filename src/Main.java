import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QDecoderStream;


public class Main {

	private ArrayList<Integer> listagem;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.executar();
	}
	
	public void executar(){		

		try {
		
			this.listagem = new ArrayList();	
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("============================ DESAFIO ENTREGA DE PACOTES ==================================");		
			System.out.println("=========== Qual a quantidade mínima de viagens a partir dos dados informados? ===========\n\n");
			
			System.out.print("- Os entregadores podem carregar ATÉ quantos kg? ");
			Integer pesoMaximo = Integer.parseInt(br.readLine());
			
			System.out.println("\n");
			
			System.out.print("- Quantos pacotes serão entregues? ");
			Integer qtdPacotes = Integer.parseInt(br.readLine());
			
			System.out.println("\n");
			
			// Preenche os pesos dos pacotes
			for (int p = 0; p < qtdPacotes; p++){
				System.out.print("- Qual é o peso do PACOTE " + (p+1) + " em kg? ");
				Integer peso = Integer.parseInt(br.readLine());
				listagem.add(peso);
			}
			
			System.out.println("\n");
			System.out.println("LISTAGEM DE PACOTES:");
			
			int contador = 0;
			for(Integer p : listagem){
				contador++;
				System.out.println("PACOTE " + contador + " possui " + p + " kg.");			
			}
			
			// Calcula a quantidade mínima de viagens a serem realizadas
			Integer qtdMinima = this.qtdMinimaDeViagens(listagem, pesoMaximo);
			
			System.out.println("\n\nQUANTIDADE MÍNIMA DE VIAGENS = " + qtdMinima);
			
		} catch (Exception e){
			System.out.println("ERRO >> " + e.toString());
		}
			
	}
	
	/* A partir da listagem de pacotes, calcula a quantidade mínima de viagens 
	 * que os entregadores precisarão realizar, respeitando o limite de peso 
	 * de transporte por viagem.
	 * 
	 * */
	private Integer qtdMinimaDeViagens(ArrayList<Integer> pesos, Integer limitePeso){
		
		Integer pesoUltimoPacote = 0;
		Integer qtdViagens = 0;		
		List listagemOrdenada = pesos;		
		
		// ordena a lista
		Collections.sort(listagemOrdenada);
		
		// Exibe a lista ordenada
		System.out.println("\n");
		System.out.println("LISTAGEM DE PACOTES - ORDENADA:");
		for(int x = 0; x < listagemOrdenada.size(); x++){
			System.out.println("PACOTE " + (x+1) + " possui " + listagemOrdenada.get(x).toString() + " kg.");			
		}
		
		// Enquanto ainda houverem elementos na listagem
		// de pacotes, continua o processamento...
		while(listagemOrdenada.size() > 0){
			
			// Obtém o último pacote
			int posUltimo = ((listagemOrdenada.size())-1);

			// Obtém o peso do último elemento do array
			pesoUltimoPacote = Integer.parseInt(listagemOrdenada.remove(posUltimo).toString());			
			
			// Se o peso limite ainda não for atingido,
			// complementa com os primeiros elementos da lista (pesos menores)
			if(pesoUltimoPacote < limitePeso){										
				
				// Incrementa o contador de viagens a serem realizadas
				qtdViagens++;
				
				// Ajusta variáveis de controle
				int posicaoInicial = 0;
				int pesoAux = 0;
				int somaAux = 0;
				boolean atingiuLimite = false;
				
				// calcula o peso que ainda pode ser levado nesta mesma viagem
				int pesoRestante = limitePeso - pesoUltimoPacote; 
				
				// Verifica quantos dos elementos iniciais da lista 
				// devem ser enviados juntos com este último elemento, 
				// o qual possui um peso maior. Verifica enquanto o limite 
				// não for atingido e enquanto a lista de dados não acabar.
				while(listagemOrdenada.size() > 0 
						&& !atingiuLimite){
					
					// Obtém peso dos elementos iniciais e ao mesmo tempo 
					// vai retirando-os da lista.
					pesoAux = Integer.parseInt(listagemOrdenada.remove(0).toString());
													
					// Soma os pesos dos itens iniciais
					somaAux = somaAux + pesoAux;
					
					// Se a soma dos pesos dos itens iniciais atingiu o limite 
					// de peso permitido, coloca novamente este último elemento na listagem
					if (somaAux >= pesoRestante){
						
						// Marca que o limite já foi atingido.
						// Força sair do while de processamento dos 
						// elementos iniciais.
						atingiuLimite = true;
						
						// Coloca novamente na listagem, na posição 0 (inicial),
						// o último elemento lido.
						listagemOrdenada.add(0, pesoAux);
						
					}
					
				} // fim do while de verificação dos elementos iniciais
			
			} // fim do if-else da verificação do limite 
			
		}		
		
		// Retorna a quantidade de viagens mínimas
		return qtdViagens;
		
	} // fim do qtdMinimaDeViagens()

}

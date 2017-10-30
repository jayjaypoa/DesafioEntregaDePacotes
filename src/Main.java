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
			System.out.println("=========== Qual a quantidade m�nima de viagens a partir dos dados informados? ===========\n\n");
			
			System.out.print("- Os entregadores podem carregar AT� quantos kg? ");
			Integer pesoMaximo = Integer.parseInt(br.readLine());
			
			System.out.println("\n");
			
			System.out.print("- Quantos pacotes ser�o entregues? ");
			Integer qtdPacotes = Integer.parseInt(br.readLine());
			
			System.out.println("\n");
			
			// Preenche os pesos dos pacotes
			for (int p = 0; p < qtdPacotes; p++){
				System.out.print("- Qual � o peso do PACOTE " + (p+1) + " em kg? ");
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
			
			// Calcula a quantidade m�nima de viagens a serem realizadas
			Integer qtdMinima = this.qtdMinimaDeViagens(listagem, pesoMaximo);
			
			System.out.println("\n\nQUANTIDADE M�NIMA DE VIAGENS = " + qtdMinima);
			
		} catch (Exception e){
			System.out.println("ERRO >> " + e.toString());
		}
			
	}
	
	/* A partir da listagem de pacotes, calcula a quantidade m�nima de viagens 
	 * que os entregadores precisar�o realizar, respeitando o limite de peso 
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
			
			// Obt�m o �ltimo pacote
			int posUltimo = ((listagemOrdenada.size())-1);

			// Obt�m o peso do �ltimo elemento do array
			pesoUltimoPacote = Integer.parseInt(listagemOrdenada.remove(posUltimo).toString());			
			
			// Se o peso limite ainda n�o for atingido,
			// complementa com os primeiros elementos da lista (pesos menores)
			if(pesoUltimoPacote < limitePeso){										
				
				// Incrementa o contador de viagens a serem realizadas
				qtdViagens++;
				
				// Ajusta vari�veis de controle
				int posicaoInicial = 0;
				int pesoAux = 0;
				int somaAux = 0;
				boolean atingiuLimite = false;
				
				// calcula o peso que ainda pode ser levado nesta mesma viagem
				int pesoRestante = limitePeso - pesoUltimoPacote; 
				
				// Verifica quantos dos elementos iniciais da lista 
				// devem ser enviados juntos com este �ltimo elemento, 
				// o qual possui um peso maior. Verifica enquanto o limite 
				// n�o for atingido e enquanto a lista de dados n�o acabar.
				while(listagemOrdenada.size() > 0 
						&& !atingiuLimite){
					
					// Obt�m peso dos elementos iniciais e ao mesmo tempo 
					// vai retirando-os da lista.
					pesoAux = Integer.parseInt(listagemOrdenada.remove(0).toString());
													
					// Soma os pesos dos itens iniciais
					somaAux = somaAux + pesoAux;
					
					// Se a soma dos pesos dos itens iniciais atingiu o limite 
					// de peso permitido, coloca novamente este �ltimo elemento na listagem
					if (somaAux >= pesoRestante){
						
						// Marca que o limite j� foi atingido.
						// For�a sair do while de processamento dos 
						// elementos iniciais.
						atingiuLimite = true;
						
						// Coloca novamente na listagem, na posi��o 0 (inicial),
						// o �ltimo elemento lido.
						listagemOrdenada.add(0, pesoAux);
						
					}
					
				} // fim do while de verifica��o dos elementos iniciais
			
			} // fim do if-else da verifica��o do limite 
			
		}		
		
		// Retorna a quantidade de viagens m�nimas
		return qtdViagens;
		
	} // fim do qtdMinimaDeViagens()

}

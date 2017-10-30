import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;


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
			
			
			
		} catch (Exception e){
			System.out.println("ERRO");
		}
			
	}
	
	/* A partir da listagem de pacotes, calcula a quantidade mínima de viagens 
	 * que os entregadores precisarão realizar, respeitando o limite de peso 
	 * de transporte por viagem.
	 * 
	 * */
	private Integer qtdMinimaDeViagens(ArrayList<Integer> pesos){
		return 0;
	}

}

package client;
import java.rmi.RemoteException;
import java.util.Scanner;

import model.Conta;

public class UI {
	
	private Controller controller = null;
	Scanner keyboard = new Scanner(System.in);
	
	public UI() {
		Conta novaConta = register();
		controller = new Controller(novaConta);
	}
	
	public void start() {
		boolean run = true;
		while (run) {
			showMenu();
			int option = keyboard.nextInt();
			keyboard.nextLine();
			if (option != 5)
				advanceMenu(option);
			else
				run = false;
		}
	}
	
	private Conta register() {
		Conta newConta = new Conta();
		System.out.println("Bem vindo ao banco central.");
		System.out.println("Digite seu nome:");
		newConta.setNome(keyboard.nextLine());
		System.out.println("Digite seu telefone:");
		newConta.setTelefone(keyboard.nextLine());
		System.out.println("Digite seu email:");
		newConta.setEmail(keyboard.nextLine());
		System.out.println("Digite sua senha:");
		newConta.setSenha(keyboard.nextLine());
		return newConta;
	}
	
	private static void showMenu() {
		System.out.println("Bem vindo ao banco central.");
		System.out.println("------ Menu Principal ------");
		System.out.println("1. Consultar extrato");
		System.out.println("2. Depositar");
		System.out.println("3. Sacar");
		System.out.println("4. Transferir");
		System.out.println("5. Sair");
	}
	
	private void advanceMenu(int option) {
		String nome;
		float quant;
		String senha;
		String nomeBusca;
		switch (option) {
			case 1:
				System.out.print(controller.gerarExtrato());
			break;
			case 2:
				System.out.println("Digite a quantidade");
				quant = keyboard.nextFloat();
				controller.reqDeposito(quant);
				System.out.println("Operação finalizada com sucesso!");
			break;
			case 3:
				System.out.println("Digite a senha");
				senha = keyboard.nextLine();
				System.out.println("Digite a quantidade");
				quant = keyboard.nextFloat();
				if (controller.reqSaque(quant, senha)) {
					System.out.println("Operação finalizada com sucesso!");
				} else {
					System.out.println("Operação falhou!");
				}
			break;
			case 4:
				System.out.println("Digite para quem você planeja transferir");
				nome = keyboard.nextLine();
				System.out.println("Digite a quantidade");
				quant = keyboard.nextFloat();
				keyboard.nextLine();
				System.out.println("Digite sua senha para confirmar");
				senha = keyboard.nextLine();
				if (controller.transferir(nome, quant, senha)) {
					System.out.println("Operação finalizada com sucesso!");
				} else {
					System.out.println("Operação falhou!");
				}
			break;
		}
	}
}

package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.Conta;

public class Server extends UnicastRemoteObject implements BancoInterface {

	private static final long serialVersionUID = 1L;
	private static int idConta = 0;
	private Map<Integer, Conta> contas;
	
	public Server() throws RemoteException {
		super();
		contas = new HashMap<>(); 
		
	}

	@Override
	public Conta novaConta(Conta conta) throws RemoteException {
		Conta novaConta = conta;
		novaConta.setId(idConta);
		novaConta.setSaldo(0);
		contas.put(idConta, novaConta);
		idConta = idConta + 1;
		return novaConta;
	}

	@Override
	public String extrato(int idConta) throws RemoteException {
		Conta conta = contas.get(idConta);
		return "Nome da conta: " + conta.getNome() + "\n" +
				"Valor em saldo: " + conta.getSaldo() + "\n";
	}

	@Override
	public boolean transferir(int idTransfer, int idReceive, float quant, String senha) throws RemoteException {
		Conta conta = contas.get(idTransfer);
		if ((conta.getSenha().equals(senha))) {
			
			if (conta.getSaldo() >= quant) {
				System.out.println("2");
				Conta contaReceiver = contas.get(idReceive);
				contaReceiver.setSaldo(contaReceiver.getSaldo() + quant);
				conta.setSaldo(conta.getSaldo() - quant);
				contas.put(contaReceiver.getId(), contaReceiver);
				contas.put(conta.getId(), conta);
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public void depositar(int idConta, float quant) throws RemoteException {
		Conta conta = contas.get(idConta);
		float total = quant + conta.getSaldo();
		conta.setSaldo(total);
		contas.put(conta.getId(), conta);
	}

	@Override
	public boolean sacar(int idConta, float quant, String senha) throws RemoteException {
		Conta conta = contas.get(idConta);
		if(!(conta.getSenha().equals(senha))){
			System.out.println("Senha digitada é invalida, tente novamente");
			return false;
		}
		if(quant > conta.getSaldo()) {
			System.out.println("Saldo insuficente em conta");
			return false;
		}
		else {
			conta.setSaldo(conta.getSaldo() - quant);
			contas.put(conta.getId(), conta);
			System.out.println("Sacando valor de R$" + quant + " reais");
			return true;
		}

	}

	@Override
	public void teste() throws RemoteException {
		System.out.println("oi");
	}

	@Override
	public int getIdContaByNome(String nome) throws RemoteException {
		for (Entry<Integer, Conta> entry : contas.entrySet()) {
		    if (entry.getValue().getNome().equals(nome)) return entry.getKey();
		}
		return -1;
	}
}

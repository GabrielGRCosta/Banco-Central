package client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import model.Conta;
import server.BancoInterface;

public class Controller {
	
	private BancoInterface stub = null;
	private Conta contaCliente;

	public Controller(Conta conta) {
		try {
			stub = (BancoInterface) Naming.lookup("rmi://localhost:1099/Banco");
			contaCliente = stub.novaConta(conta);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String gerarExtrato() {
		try {
			System.out.println(contaCliente.getId());
			return stub.extrato(contaCliente.getId());
		} catch (RemoteException e) {
			return null;
		}
	}
	
	public boolean transferir(String nome, float quant, String senha) {
		try {
			int idReceive = stub.getIdContaByNome(nome);
			if (idReceive == -1) return false;
			return stub.transferir(contaCliente.getId(), idReceive, quant, senha);
		} catch (RemoteException e) {
			return false;
		}
	}
	
	public boolean reqSaque(float valor, String senha) {
		try {
			return stub.sacar(contaCliente.getId(), valor, senha);
		} catch (RemoteException e) {
			return false;
		}
	}

	public void reqDeposito(float valor) {
		try {
			stub.depositar(contaCliente.getId(), valor);
		} catch (RemoteException e) {
		}
	}
}

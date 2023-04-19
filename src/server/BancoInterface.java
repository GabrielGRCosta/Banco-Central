package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import model.Conta;

public interface BancoInterface extends Remote{
	public Conta novaConta(Conta conta) throws RemoteException;
	public String extrato(int idConta) throws RemoteException;
	public boolean transferir(int idTransfer, int idReceive, float quant, String senha) throws RemoteException;
	public void depositar(int idConta, float quant) throws RemoteException;
	public boolean sacar(int idConta, float quant, String senha) throws RemoteException;
	public int getIdContaByNome(String nome) throws RemoteException;
	public void teste() throws RemoteException;
}

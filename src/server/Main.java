package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
//		System.setProperty("java.rmi.server.hostname","127.0.0.1");
		BancoInterface server = new Server();
	
		LocateRegistry.createRegistry(1099);
		
		Naming.rebind("rmi://127.0.0.1:1099/Banco", server);
	}

}

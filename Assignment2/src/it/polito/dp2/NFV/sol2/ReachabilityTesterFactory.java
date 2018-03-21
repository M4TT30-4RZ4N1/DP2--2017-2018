package it.polito.dp2.NFV.sol2;


import it.polito.dp2.NFV.sol2.ReachabilityTester;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.NfvReaderFactory;
import it.polito.dp2.NFV.lab2.ReachabilityTesterException;

public class ReachabilityTesterFactory extends it.polito.dp2.NFV.lab2.ReachabilityTesterFactory{

	@Override
	public ReachabilityTester newReachabilityTester() throws ReachabilityTesterException {
		
		ReachabilityTester reachabilityTester = null;
		//define the entry point	
		try{
			NfvReaderFactory nfvReaderFactory = NfvReaderFactory.newInstance();
			NfvReader nfvReader = nfvReaderFactory.newNfvReader();			
			reachabilityTester = new ReachabilityTester(nfvReader);
			System.out.println("Reachability tester instatiated");
		}catch(NfvReaderException e ){
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}catch(NullPointerException e){
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(2);
		}
		
		return reachabilityTester;
	}

}

package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;

import it.polito.dp2.NFV.FunctionalType;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RVnft;

public class Client2VNFTypeReader implements it.polito.dp2.NFV.VNFTypeReader {

	private RVnft vnft;
	
	public Client2VNFTypeReader(URI vnftURI) {
		// get a single vnft from base URI
		vnft = NfvDeployer.createClient().resource(vnftURI).get(RVnft.class);
	}

	@Override
	public String getName() {
		return vnft.getName();
	}

	@Override
	public FunctionalType getFunctionalType() {
		return FunctionalType.fromValue(vnft.getVnftype().toString());
	}

	@Override
	public int getRequiredMemory() {
		return vnft.getMemory().intValue();
	}

	@Override
	public int getRequiredStorage() {
		return vnft.getStorage().intValue();
	}

}

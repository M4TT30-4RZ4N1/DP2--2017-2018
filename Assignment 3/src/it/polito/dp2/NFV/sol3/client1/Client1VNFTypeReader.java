package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;

import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.NFV.FunctionalType;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RVnft;

public class Client1VNFTypeReader implements it.polito.dp2.NFV.VNFTypeReader {
	
	private WebResource vnfService;
	private RVnft resource = new RVnft();

	public Client1VNFTypeReader(URI vnfUri) {
		// init
		vnfService = NfvDeployer.createClient().resource(vnfUri);
		resource = vnfService.get(RVnft.class);
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public FunctionalType getFunctionalType() {
		return FunctionalType.valueOf(resource.getVnftype().toString());
	}

	@Override
	public int getRequiredMemory() {
		return resource.getMemory().intValue();
	}

	@Override
	public int getRequiredStorage() {
		return resource.getStorage().intValue();
	}

}

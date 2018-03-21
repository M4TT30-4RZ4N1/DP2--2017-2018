package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RHost;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNode;

public class Client1HostReader implements it.polito.dp2.NFV.HostReader {
	
	private WebResource hostService;
	private RHost resource = new RHost();

	public Client1HostReader(URI hostUri) {
		//init
		hostService = NfvDeployer.createClient().resource(hostUri);
		resource = hostService.get(RHost.class);
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public int getAvailableMemory() {
		return resource.getMemory().intValue();
	}

	@Override
	public int getAvailableStorage() {
		return resource.getStorage().intValue();
	}

	@Override
	public int getMaxVNFs() {
		return resource.getMaxRvnfts().intValue();
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		Set<NodeReader> set = new HashSet<>();
		
		for (ResourceName node : resource.getDeployedNodes().getRNodes()) {
			
			RNode tmp = NfvDeployer.createClient().resource(node.getReference()).get(RNode.class);
			set.add(new it.polito.dp2.NFV.sol3.client1.Client1NodeReader(URI.create(tmp.getReference())));
		}
		
		return set;
	}

}

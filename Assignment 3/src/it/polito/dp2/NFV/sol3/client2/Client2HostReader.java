package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RHost;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer;

public class Client2HostReader implements it.polito.dp2.NFV.HostReader {

	private RHost hostResource;
	int storage = 0;
	int memory = 0;
	private Set<URI> nodesURI = new HashSet<URI>();

	public Client2HostReader(URI uri) throws ServiceException {
		
		try {
			/// get host resource
			hostResource = NfvDeployer.createClient().resource(uri).get(RHost.class);
			
			// get all nodes uri from ahost and map it
			for (ResourceName n : hostResource.getDeployedNodes().getRNodes()) {
				nodesURI.add(URI.create(n.getReference()));
			}
		} 
		catch (WebApplicationException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String getName() {
		return hostResource.getName();
	}

	@Override
	public int getAvailableMemory() {
		
		if(hostResource.getMemory() != null){
			memory = hostResource.getMemory().intValue();
		}
		
			return memory;
		
	}

	@Override
	public int getAvailableStorage() {
		
		storage = 0;
		if(hostResource.getStorage() != null){
			storage = hostResource.getStorage().intValue();
		}
		
			return storage;
	}

	@Override
	public int getMaxVNFs() {
		
		if(hostResource.getMaxRvnfts() != null){
			return hostResource.getMaxRvnfts().intValue();
		}
		return 0;
		
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		HashSet<NodeReader> allocatedNodes = new HashSet<>();
		
		for (URI tmpUri : nodesURI) {
			// tryadd the new Node reader
			try {
				allocatedNodes.add(new Client2NodeReader(tmpUri));
			} 
			catch (ServiceException e) {
				return null;
			}
		}
		return allocatedNodes;
	}

}

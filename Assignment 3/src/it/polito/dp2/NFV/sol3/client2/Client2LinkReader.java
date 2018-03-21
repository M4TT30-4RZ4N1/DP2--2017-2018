package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;

import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RLink;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RNodes;

public class Client2LinkReader implements it.polito.dp2.NFV.LinkReader {

	private String linkName;
	private URI srcURI;
	private URI dstURI;
	private RLink linkResource;
	
	
	public Client2LinkReader(URI linkUri, URI nodesUri) throws ServiceException {
		
		try {
			// get link resource from uri
			linkResource = NfvDeployer.createClient().resource(linkUri).get(RLink.class);
			// get all nodes from nodes uri
			RNodes allNodes = NfvDeployer.createClient().resource(nodesUri).get(RNodes.class);
			
			for (ResourceName node_tmp : allNodes.getRNodes()) {
				
				// for scr get uri
				if (node_tmp.getName().equals(linkResource.getDst())) {
					dstURI = URI.create(node_tmp.getReference());
				}
				// fro dst get uri
				if (node_tmp.getName().equals(linkResource.getSrc())){
					srcURI = URI.create(node_tmp.getReference());
				}
				
			}
		} 
		catch (WebApplicationException e) {
			throw new ServiceException();
		}
	}

	@Override
	public String getName() {
		return linkResource.getName();
	}

	@Override
	public NodeReader getDestinationNode() {
		try {
			return new Client2NodeReader(dstURI);
		} 
		catch (ServiceException e) {
		
			return null;
		}
	}

	@Override
	public int getLatency() {
		int maxL = 0;
		if(linkResource.getMaxL() != null){
			maxL = linkResource.getMaxL().intValue();
		}
		return maxL;
	}

	@Override
	public NodeReader getSourceNode() {
		try {
			return new Client2NodeReader(srcURI);
		}
		catch (ServiceException e) {

			return null;
		}
	}

	@Override
	public float getThroughput() {
		return linkResource.getMinT();
	}

}

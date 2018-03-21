package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;

import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RLink;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNodes;

public class Client1LinkReader implements it.polito.dp2.NFV.LinkReader {

	private WebResource linkService;
	private WebResource nodesService;
	private String garbageRandom;
	private RLink resource = new RLink();
	private RNodes nodesResource = new RNodes();
	
	public Client1LinkReader(URI linkURI, URI nodesURI) {
		
		linkService = NfvDeployer.createClient().resource(linkURI);
		resource = linkService.get(RLink.class);
		nodesService = NfvDeployer.createClient().resource(nodesURI);
		nodesResource  =nodesService.get(RNodes.class);
		
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public NodeReader getDestinationNode() {
		
		// get dst
		String dst = resource.getDst();
		//find dst on nodes resources one step before
		for (ResourceName node : nodesResource.getRNodes()) {
			if (node.getName().equals(dst)) {
				return new Client1NodeReader(URI.create(node.getReference()));
			}
		}
		return null;
	}

	@Override
	public int getLatency() {
		return resource.getMaxL().intValue();
	}

	@Override
	public NodeReader getSourceNode() {
		
		// get the scr node from nodes
		String src = resource.getSrc();
		for (ResourceName node : nodesResource.getRNodes()) {
			if (node.getName().equals(src)) {
				return new Client1NodeReader(URI.create(node.getReference()));
			}
		}
		return null;
	}

	@Override
	public float getThroughput() {
		return resource.getMinT();
	}

}

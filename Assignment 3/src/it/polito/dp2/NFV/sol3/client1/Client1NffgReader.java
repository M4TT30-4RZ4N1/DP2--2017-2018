package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNffg;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer.Root.Nffgs.NffgName.Nodes;

public class Client1NffgReader implements it.polito.dp2.NFV.NffgReader {

	private URI nffgBase_URI;
	private NfvDeployer.Root.Nffgs.NffgName.Nodes nodesService;
	private String garbageRandom = new String();
	private WebResource nffgService;
	private RNffg resource = new RNffg();
	
	public Client1NffgReader(URI nffgUri) {
		// get URI of Nffg
		nffgBase_URI = nffgUri;
		nodesService = new Nodes(NfvDeployer.createClient(), nffgUri);
		nffgService =  NfvDeployer.createClient().resource(nffgBase_URI);
		resource = nffgService.get(RNffg.class);
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public Calendar getDeployTime() {
		
		// return only if not null
		if (resource != null) {
			if (resource.getDeployTime() != null) {
				return resource.getDeployTime().toGregorianCalendar();
			}
		}
	
		return null;

	}

	@Override
	public NodeReader getNode(String nodeName) {
		
		// find the node 
		for (ResourceName node : nodesService.getAsRNodesXml().getRNodes()) {
			if (node.getName().equals(nodeName)){
				return new Client1NodeReader(URI.create(node.getReference()));
			}
		}
		
		return null;
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		HashSet<NodeReader> set = new HashSet<>();
		
		//populate with all nodes
		for (ResourceName node : nodesService.getAsRNodesXml().getRNodes()) {
			set.add(new Client1NodeReader(URI.create(node.getReference())));
		}
		return set;
	}

}

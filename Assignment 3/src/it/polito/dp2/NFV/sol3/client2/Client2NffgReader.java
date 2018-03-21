package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNffg;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer.Root.Nffgs.NffgName.Nodes;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RNodes;

public class Client2NffgReader implements it.polito.dp2.NFV.NffgReader {

	private RNffg nffg;
	private Map<String, NodeReader> nodes = new HashMap<>();
	
	public Client2NffgReader(URI uri) throws ServiceException {
		
		try {
			nffg = NfvDeployer.createClient().resource(uri).get(RNffg.class);
			RNodes allNodes = new Nodes(NfvDeployer.createClient(), uri).getAsRNodesXml();
			
			for (ResourceName node_tmp : allNodes.getRNodes()) {
				nodes.put(node_tmp.getName(), new Client2NodeReader(URI.create(node_tmp.getReference())));
			}
		} 
		catch (WebApplicationException e) {
			throw new ServiceException();
		}
	}

	@Override
	public String getName() {
		return nffg.getName();
	}

	@Override
	public Calendar getDeployTime() {
		
		if (nffg.getDeployTime() != null) {
			return nffg.getDeployTime().toGregorianCalendar();
		}
		
		return null;
	}

	@Override
	public NodeReader getNode(String nodeName) {
		
		if (nodes.containsKey(nodeName)){
			return nodes.get(nodeName);
		}
		return null;
	}

	@Override
	public Set<NodeReader> getNodes() {
		
		HashSet<NodeReader> set = new HashSet<NodeReader>(nodes.values());
		return set;
	}

}

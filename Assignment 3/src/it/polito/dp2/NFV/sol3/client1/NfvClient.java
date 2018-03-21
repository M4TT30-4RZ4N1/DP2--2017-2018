package it.polito.dp2.NFV.sol3.client1;

import java.math.BigInteger;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang.math.RandomUtils;

import com.sun.jersey.api.client.ClientResponse;

import it.polito.dp2.NFV.lab3.AllocationException;
import it.polito.dp2.NFV.lab3.LinkDescriptor;
import it.polito.dp2.NFV.sol3.client1.DeployedNffg;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NewRnffg;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NewRnffg.RLinks;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NewRnffg.RLinks.RLink;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NewRnffg.RNodes;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NewRnffg.RNodes.RNode;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNffg;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer.Root.Nffgs;
import it.polito.dp2.NFV.lab3.NffgDescriptor;
import it.polito.dp2.NFV.lab3.NodeDescriptor;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.lab3.UnknownEntityException;

public class NfvClient implements it.polito.dp2.NFV.lab3.NfvClient {
	
	
	private NfvDeployer.Root.Nffgs serviceNffg_API;
	String garbageRandom = new String();
	private Map<NodeDescriptor, String> nodeDescriptorNameMap;
	
	
	public NfvClient() {
		// get base Uri
		String serviceBase_URI = System.getProperty("it.polito.dp2.NFV.lab3.URL", "http://localhost:8080/NfvDeployer/rest/");
		// get the URI for Nffgs
		serviceNffg_API = new Nffgs(NfvDeployer.createClient(), URI.create(serviceBase_URI));
	}

	@Override
	public DeployedNffg deployNffg(NffgDescriptor nffgDescriptor) throws AllocationException, ServiceException {
		
		nodeDescriptorNameMap = new HashMap<>();
		NewRnffg completeNffg = new NewRnffg();
		RNodes nodes = new RNodes();
		RLinks links = new RLinks();
		
		// assign random name
		completeNffg.setName("Nffg"+RandomUtils.nextInt());
			
		for (NodeDescriptor nodeDescriptor : nffgDescriptor.getNodes()) {
			RNode nodeToAdd = new RNode();
			// get random name for node
			String nodeName = nodeDescriptor.getFuncType().getName()+completeNffg.getName()+RandomUtils.nextInt();
			// get information
			nodeToAdd.setName(nodeName);
			nodeToAdd.setRVnft(nodeDescriptor.getFuncType().getName());
			nodeToAdd.setOwnerHost(nodeDescriptor.getHostName());
			
			// add locally
			nodes.getRNode().add(nodeToAdd);
			// map names for future use inside a linkdescriptor
			nodeDescriptorNameMap.put(nodeDescriptor, nodeName);
		}
		
		for (NodeDescriptor nodeDescriptor2 : nffgDescriptor.getNodes()) {
			
			for (LinkDescriptor linkDescriptor : nodeDescriptor2.getLinks()) {
				RLink linkToAdd = new RLink();
				// assign random name base on src and dst
				String linkName = "Link"+nodeDescriptorNameMap.get(nodeDescriptor2)+nodeDescriptorNameMap.get(linkDescriptor.getDestinationNode());
				
				linkToAdd.setName(linkName);
				linkToAdd.setSrcNode(nodeDescriptorNameMap.get(nodeDescriptor2));
				linkToAdd.setDstNode(nodeDescriptorNameMap.get(linkDescriptor.getDestinationNode()));
				linkToAdd.setMaxL(BigInteger.valueOf(linkDescriptor.getLatency()));
				linkToAdd.setMinT(linkDescriptor.getThroughput());
				
				// add locally
				links.getRLink().add(linkToAdd);
			}
			
		}
		
		//populate the nffg to deploy
		completeNffg.setRNodes(nodes);
		completeNffg.setRLinks(links);
				
		
		// try to deploy the complete nffg to the service
		try {
			// get the response as done in neo4j
			ClientResponse response = serviceNffg_API.postXml(completeNffg, ClientResponse.class);
			
			if (response.getStatus() == 200) {
				RNffg loadedGraph = response.getEntity(RNffg.class);
				return new DeployedNffg(loadedGraph);
			}		
			if (response.getStatus() == 409){
				throw new AllocationException();
			}
			if (response.getStatus() >= 400){
				throw new ServiceException();
			}
			if (response.getStatus() >= 500){
				throw new ServiceException();
			}	
			
			throw new ServiceException();  // otherwise unknown response
			
		}
		catch (WebApplicationException e) {
			throw new ServiceException(e);
		}
		
		
	}

	@Override
	public DeployedNffg getDeployedNffg(String nffgName) throws UnknownEntityException, ServiceException {
		
		ClientResponse response = serviceNffg_API.nffgName(nffgName).getAsXml(ClientResponse.class);
		
		if (response.getStatus() == 200) {
			RNffg nffg = response.getEntity(RNffg.class);
			return new DeployedNffg(nffg);
		}
		if (response.getStatus() == 404) {
			throw new UnknownEntityException();
		}
		if (response.getStatus() >= 400){
			throw new ServiceException();
		}		
		if (response.getStatus() >= 500){
			throw new ServiceException();
		}
			
		throw new ServiceException();  // throw in any case of other codes
		 
	}

}

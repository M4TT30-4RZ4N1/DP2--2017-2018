package it.polito.dp2.NFV.sol2;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.lab2.NoGraphException;
import it.polito.dp2.NFV.lab2.ServiceException;

//
// bad import comnflict Node types !!!! :import it.polito.dp2.NFV.sol2.Nodes.Node;

public class ConcreteExtendedNodeReader implements it.polito.dp2.NFV.lab2.ExtendedNodeReader{
	
	private Node node;
	private NodeReader nodeReader;  // passed to satisfy unimplemented methods
	private Set<HostReader> hosts;
	private WebTarget webTarget;

	
	public ConcreteExtendedNodeReader(Node node2, NodeReader nodeReader, Set<HostReader> hosts, WebTarget webTarget) throws NullPointerException{
		
		// check if something is not instantiated
		if (node2 == null || nodeReader == null || hosts == null || webTarget == null)
			throw new NullPointerException();
			
		this.node = node2;
		this.nodeReader= nodeReader;
		this.hosts = hosts;
		this.webTarget = webTarget;
	}


	@Override
	public VNFTypeReader getFuncType() {
		// TODO Auto-generated method stub
		return nodeReader.getFuncType();
	}


	@Override
	public HostReader getHost() {
		// TODO Auto-generated method stub
		return nodeReader.getHost();
	}


	@Override
	public Set<LinkReader> getLinks() {
		// TODO Auto-generated method stub
		return nodeReader.getLinks();
	}


	@Override
	public NffgReader getNffg() {
		// TODO Auto-generated method stub
		return nodeReader.getNffg();
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return nodeReader.getName();
	}

	// each node is allocated to one host (owner) but each node is linked also to different nodes
	// this mean that nodes can reachable an another host  ???  ask to Prof/ control FAQ  ???
	@Override
	public Set<HostReader> getReachableHosts() throws NoGraphException, ServiceException {
		// TODO Auto-generated method stub
		
		// create local set
		Set<HostReader> hostReaderSet = new HashSet<HostReader>();

		//get all hosts reachable from that node
		Nodes reachableHostNodes = null;
		try{
			reachableHostNodes = tryToGetReachableHosts(node.getId());
		}
		catch ( ServiceException e ){
			throw new ServiceException();
		}

		// for each reachable host(node)
		for(Nodes.Node hostNode : reachableHostNodes.getNode()){			
			// for each host
			for(HostReader host : hosts){
				
				// if the node property AllocatedOn is equal to the hostName and it's the first time we see him: save it to the set
				if (!hostReaderSet.contains(host) && hostNode.getProperties().getProperty().get(0).getValue().equals(host.getName()))
						hostReaderSet.add(host);
			}
		}

		return hostReaderSet;
	}
	
	public Nodes tryToGetReachableHosts(String nodeId) throws ServiceException{

		// get reachableNodes from a nodeId
		Response response = webTarget.path("/data/node/" + nodeId +"/reachableNodes")
									 .queryParam ("", "Host")
									 .request("application/xml")
									 .get();

		//check the statusCode
		int statusCode = response.getStatus();
		if (statusCode == 200 || statusCode == 201){
			return response.readEntity(Nodes.class); 
		}
		else if (statusCode == 400){
			throw new ServiceException();
		}
		else if (statusCode == 404){
			throw new ServiceException();
		}
		else if ( statusCode == 500 ){
			throw new ServiceException();
		}

		return null;
	}
	
		
}

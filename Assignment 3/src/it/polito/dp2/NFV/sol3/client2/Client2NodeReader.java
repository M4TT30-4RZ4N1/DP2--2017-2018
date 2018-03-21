package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.WebApplicationException;

import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RLinks;
import it.polito.dp2.NFV.sol3.client2.Client2VNFTypeReader;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RLink;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RNode;
import it.polito.dp2.NFV.sol3.client2.Client2HostReader;

public class Client2NodeReader implements it.polito.dp2.NFV.NodeReader {

	private URI hostURI;
	private URI nffgURI;
	private URI linkURI;
	WebResource nodeService;
	private RNode node;
	private Client2VNFTypeReader vnft;
	private Set<LinkReader> linkSet;
	
	public Client2NodeReader(URI nodeUri) throws ServiceException {
		
		linkSet = new HashSet<>();
		
		try {
			// get service 
			nodeService = NfvDeployer.createClient().resource(nodeUri);
			node = nodeService.get(RNode.class);
			
			// create uri for host and get back a vnft from node vnft
			vnft = new Client2VNFTypeReader(URI.create(node.getRVnft().getReference()));
			hostURI = URI.create(node.getRHost().getReference());
			
			// create uri for nffgs/id/nodes
			URI nffgUri = URI.create(node.getRNffg().getReference().concat("/nodes")).normalize();
			
			for (RLink link_tmp : NfvDeployer.createClient().resource(node.getRLinks().getReference()).get(RLinks.class).getRLink()) {
				
				linkSet.add(new Client2LinkReader(URI.create(link_tmp.getReference()), nffgUri ));
			}
			
		} 
		catch (WebApplicationException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String getName() {
		return node.getName();
	}

	@Override
	public Client2VNFTypeReader getFuncType() {
		return vnft;
	}

	@Override
	public HostReader getHost() {
		// use the resource of client 1 
		try {
			return new Client2HostReader(hostURI);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Set<LinkReader> getLinks() {
		return linkSet;
	}

	@Override
	public NffgReader getNffg() {
		
		try {
			return new Client2NffgReader(nffgURI);
		}
		catch (ServiceException e) {
			return null;
		}
	}

}

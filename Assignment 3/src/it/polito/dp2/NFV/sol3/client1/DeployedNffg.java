/**
 * 
 */
package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;
import javax.ws.rs.WebApplicationException;

import org.apache.commons.lang.math.RandomUtils;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.lab3.AllocationException;
import it.polito.dp2.NFV.lab3.LinkAlreadyPresentException;
import it.polito.dp2.NFV.lab3.NoNodeException;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RLink;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNffg;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer.Root.Nffgs.NffgName;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer.Root.Nffgs.NffgName.Links;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNode;

public class DeployedNffg implements it.polito.dp2.NFV.lab3.DeployedNffg {

	private URI nffgBase_URI;
	private URI nodesURI;
	private String garbageRandom = new String();
	private WebResource nodesService;
	private Links linksService;   // from WADL -SERVICE
	private String nffgName;
		
	public DeployedNffg(RNffg nffg) {
		
		nffgName = nffg.getName();
		// get URI
		nffgBase_URI = URI.create(nffg.getReference());
		nodesURI = URI.create(nffg.getRNodes().getReference());
		
		// get service
		nodesService = NfvDeployer.createClient().resource(nodesURI);
		linksService = new NffgName.Links(NfvDeployer.createClient(), nffgBase_URI);
	}


	@Override
	public NodeReader addNode(VNFTypeReader type, String hostName) throws AllocationException, ServiceException {
		
		RNode nodeToAdd = new RNode();
		ResourceName vnft = new ResourceName();
		ResourceName ownerHost = new ResourceName();
		
		// associate hostname to owner host
		ownerHost.setName(hostName);
		nodeToAdd.setRHost(ownerHost);
		vnft.setName(type.getName());
		nodeToAdd.setRVnft(vnft);
		nodeToAdd.setName(vnft.getName()+nffgName+RandomUtils.nextInt());
		
		
		try {
			ClientResponse clientResponse = nodesService.post(ClientResponse.class, nodeToAdd);
			
			if (clientResponse.getStatus() == 200){
				return new Client1NodeReader(URI.create(clientResponse.getEntity(RNode.class).getReference()));
			}
			if (clientResponse.getStatus() == 409) {
				
				throw new AllocationException();
			}
			if (clientResponse.getStatus() >= 500){
				throw new ServiceException();
			}
			if (clientResponse.getStatus() >= 400){
				
				throw new ServiceException();
			}		
			
			throw new ServiceException();
			
		} 
		catch (WebApplicationException e) {
			
			throw new ServiceException(e);
		}

	}

	@Override
	public LinkReader addLink(NodeReader source, NodeReader dest, boolean overwrite)
			throws NoNodeException, LinkAlreadyPresentException, ServiceException {
		
		RLink finalLink;
		ClientResponse clientResponse;
		RLink linkToAdd = new RLink();
		
		linkToAdd.setName("Link"+source.getName()+dest.getName());
		linkToAdd.setSrc(source.getName());
		linkToAdd.setDst(dest.getName());
		
		if (overwrite) { // perform the put in ordet to update
			clientResponse = linksService.linkName(linkToAdd.getName()).putXml(linkToAdd, ClientResponse.class);

		} else { // perform a post
			clientResponse = linksService.postXml(linkToAdd, ClientResponse.class);
		}
		

		if (clientResponse.getStatus() == 200) {
			finalLink = clientResponse.getEntity(RLink.class);
			return new Client1LinkReader(URI.create(finalLink.getReference()), nodesURI);	
		}	
		if (clientResponse.getStatus() == 409){
			throw new LinkAlreadyPresentException();
		}
		if (clientResponse.getStatus() == 404){
			throw new NoNodeException();
		}
		if (clientResponse.getStatus() >= 500){
			throw new LinkAlreadyPresentException();
		}
		if (clientResponse.getStatus() >= 400){
			throw new ServiceException();
		}	
		
		throw new ServiceException();
		
		
	}

	@Override
	public NffgReader getReader() throws ServiceException {
		return new Client1NffgReader(nffgBase_URI);
	}

}

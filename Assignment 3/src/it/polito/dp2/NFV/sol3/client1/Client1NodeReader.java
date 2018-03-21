package it.polito.dp2.NFV.sol3.client1;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import com.sun.jersey.api.client.WebResource;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RLink;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer.Root.Nffgs.NffgName.Nodes.NodeName.Links;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.RNode;

public class Client1NodeReader implements it.polito.dp2.NFV.NodeReader {
	
	private Links linksService;
	private WebResource nodeService;
	private URI nffgBase_URI;
	private URI vnftBase_URI;
	private String garbageRandom;
	RNode resource = new RNode();
	
	public Client1NodeReader(URI nodeURI) {
		
		// get base URI and the resource
		nodeService = NfvDeployer.createClient().resource(nodeURI);
		resource= nodeService.get(RNode.class);
		linksService = new Links(NfvDeployer.createClient(), nodeURI);
		nffgBase_URI = URI.create(resource.getRNffg().getReference());
		vnftBase_URI = URI.create(resource.getRVnft().getReference());	
	}

	@Override
	public String getName() {
		return resource.getName();
	}

	@Override
	public VNFTypeReader getFuncType() {
		return new Client1VNFTypeReader(vnftBase_URI);
	}

	@Override
	public HostReader getHost() {
		return new Client1HostReader(URI.create(this.nodeService.get(RNode.class).getRHost().getReference()));
	}

	@Override
	public Set<LinkReader> getLinks() {
		
		Set<LinkReader> set = new HashSet<>();
		// get fine granularity
		// get links of node
		
		for (RLink link_tmp : linksService.getAsRLinksXml().getRLink()) {
			set.add(new Client1LinkReader(URI.create(link_tmp.getReference()), nffgBase_URI.resolve("nodes")));
		}
		
		return set;
	}

	@Override
	public NffgReader getNffg() {
		// get information about nffg from node
		URI nffgUri = URI.create(resource.getRNffg().getReference());
		return new Client1NffgReader(nffgUri);
	}

}

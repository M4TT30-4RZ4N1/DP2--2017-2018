package it.polito.dp2.NFV.sol1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.Link;
import it.polito.dp2.NFV.sol1.jaxb.Nffg;
import it.polito.dp2.NFV.sol1.jaxb.Node;

public class ConcreteNodeReader implements NodeReader {

	private String name;
	private VNFTypeReader typeReader;
	private HostReader hostReader;
	private HashMap<String,LinkReader> linkMapReader = new HashMap <String,LinkReader>();
	private NffgReader nffgReader;
	
	public ConcreteNodeReader(Node node, ConcreteNffgReader concreteNffgReader, VNFTypeReader vnfTypeReader) {
		// TODO Auto-generated constructor stub
		if(node == null) return; // safety lock
		
		this.name = node.getName();
		this.nffgReader = concreteNffgReader;
		this.typeReader = vnfTypeReader;
				
	}

	public void setTypeReader(VNFTypeReader typeReader) {
		this.typeReader = typeReader;
	}

	@Override 
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public VNFTypeReader getFuncType() {
		// TODO Auto-generated method stub
		return typeReader;
	}

	@Override
	public HostReader getHost() {
		// TODO Auto-generated method stub
		return hostReader;
	}

	@Override
	public Set<LinkReader> getLinks() {
		// TODO Auto-generated method stub
		Set<LinkReader> set = new HashSet<LinkReader>(linkMapReader.values());
		return set;
	}

	@Override
	public NffgReader getNffg() {
		// TODO Auto-generated method stub
		return nffgReader;
	}

	public void setHostReader(HashMap<String, HostReader> hosts) {
		
		// for each host control if he own the node
		for(HostReader hostReader : hosts.values()){
			if(hostReader.getNodes().contains(this)){
				this.hostReader = hostReader;
				break;
			}
		}
		
		
	}

	public void setLinkReader(LinkReader linkReader) {
		this.linkMapReader.put(linkReader.getName(), linkReader);
	}

	public void setNffgReader(NffgReader nffgReader) {
		this.nffgReader = nffgReader;
	}

}

package it.polito.dp2.NFV.sol1;
import it.polito.dp2.NFV.sol1.jaxb.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.Host;

public class ConcreteHostReader implements HostReader{
	
	private String name;
	private int memory;
	private int storage;
	private int maxVNFs;
	private HashMap<String,NodeReader> nodes = new HashMap<String,NodeReader>();
	
	// internal use
	private Set<String> names = new HashSet<String>();

	public ConcreteHostReader(Host host, HashMap<String, NffgReader> nffgs) {
		// TODO Auto-generated constructor stub
		if(host == null) return; // safety lock
		
		this.name = host.getName();
		this.memory = host.getMemory().intValue();
		this.storage = host.getDisk().intValue();
		this.maxVNFs = host.getMaxVNF().intValue();
		
		// save the name of nodes allocate
		for(Object node : host.getNodes()){					
			
			if(node instanceof Node){
				Node myNode = (Node) node;
				names.add(myNode.getName());
			}			
		}
		
		// find nodes allocated for each nffg
		for (NffgReader nffgReader : nffgs.values()){			
			for(NodeReader nodeReader : nffgReader.getNodes()){
				// match node in the list of node's names
				if(nodeReader instanceof ConcreteNodeReader){
					ConcreteNodeReader concreteNode = (ConcreteNodeReader) nodeReader;
					if(names.contains(concreteNode.getName())){
						nodes.put(concreteNode.getName(), concreteNode);
					}
				}
			}
		}
		
		System.out.println(this.getName()+" has "+ nodes.size()+ " nodes");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int getAvailableMemory() {
		// TODO Auto-generated method stub
		return memory;
	}

	@Override
	public int getAvailableStorage() {
		// TODO Auto-generated method stub
		return storage;
	}

	@Override
	public int getMaxVNFs() {
		// TODO Auto-generated method stub
		return maxVNFs;
	}

	@Override
	public Set<NodeReader> getNodes() {
		// TODO Auto-generated method stub
		Set<NodeReader> set = new HashSet<NodeReader>(nodes.values());
		return set;
	}

}

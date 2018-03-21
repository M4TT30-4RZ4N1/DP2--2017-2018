package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.sol1.jaxb.Link;

public class ConcreteLinkReader implements LinkReader {
	
	private String name;
	private NodeReader src;
	private NodeReader dst;
	private int latency;
	private float throughput;
	
	public ConcreteLinkReader(Link link, NodeReader srcNode, NodeReader dstNode) {
		// TODO Auto-generated constructor stub
		if(link == null) return; // safety lock
		
		this.name = link.getName();
		this.src = srcNode;
		this.dst = dstNode;
		this.latency = link.getMaxL().intValue();
		this.throughput = link.getThroughput().floatValue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public NodeReader getDestinationNode() {
		// TODO Auto-generated method stub
		return dst;
	}

	@Override
	public int getLatency() {
		// TODO Auto-generated method stub
		return latency;
	}

	@Override
	public NodeReader getSourceNode() {
		// TODO Auto-generated method stub
		return src;
	}

	@Override
	public float getThroughput() {
		// TODO Auto-generated method stub
		return throughput;
	}

}

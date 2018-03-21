package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.sol1.jaxb.Connection;

public class ConcreteConnectionPerformanceReader implements ConnectionPerformanceReader{

	private int latency;
	private float throughput;
	
	public ConcreteConnectionPerformanceReader(Connection connection) {
		// TODO Auto-generated constructor stub
		if(connection == null) return;
		
		this.latency = connection.getLatency().intValue();
		this.throughput = connection.getThroughput().floatValue();
	}

	@Override
	public int getLatency() {
		// TODO Auto-generated method stub
		return latency;
	}

	@Override
	public float getThroughput() {
		// TODO Auto-generated method stub
		return throughput;
	}

}

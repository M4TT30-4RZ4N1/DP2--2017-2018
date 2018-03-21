package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;

import javax.ws.rs.WebApplicationException;

import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.lab3.UnknownEntityException;
import it.polito.dp2.NFV.sol3.client1.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RConnection;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer.Root.Hosts;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RConnections;

public class Client2ConnectionPerformanceReader implements it.polito.dp2.NFV.ConnectionPerformanceReader {

	private Hosts hostService;
	private int maxL = 0;
	private float minT = 0;
	private RConnection connectionResource;

	public Client2ConnectionPerformanceReader(String src, String dst, URI uri) throws UnknownEntityException, ServiceException {
		
		// get service
		hostService = new Hosts(NfvDeployer.createClient(), uri);
		int flag_src = 0;
		int flag_dst = 0;
		
		try {
			
			for (ResourceName host_tmp : hostService.getAsRHostsXml().getRHosts()) {
				
				// find src
				if (host_tmp.getName().equals(src)) {
					flag_src = 1;
					RConnections conns = hostService.hostName(src).connections().getAsRConnectionsXml();

					for (RConnection connectionFound : conns.getRConnections()) {
						// check if exist dst
						if (connectionFound.getDst().equals(dst)) {
							flag_dst = 1;
							// assign to global resource
							connectionResource = connectionFound;
						}
					}
				}
			}
			if (flag_src == 0 || flag_dst == 0) {
			
					throw new UnknownEntityException();		
			}
		} 
		catch (WebApplicationException e) {
			
			throw new ServiceException(e);
		}

	}

	@Override
	public int getLatency() {
		maxL = 0;
		if (connectionResource.getMaxL() != null){
			maxL = connectionResource.getMaxL().intValue();
		}
		return maxL; 
	}

	@Override
	public float getThroughput() {
		
		minT = connectionResource.getMinT();
		return minT;
	}

}

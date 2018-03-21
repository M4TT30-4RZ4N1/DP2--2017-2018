package it.polito.dp2.NFV.sol3.client2;

import java.net.URI;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.lab3.ServiceException;
import it.polito.dp2.NFV.lab3.UnknownEntityException;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RVnfts;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RHosts;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.ResourceName;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RNffgs;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.NfvDeployer.Root;
import it.polito.dp2.NFV.sol3.client2.nfvdeployer.RVnft;

public class NfvReader implements it.polito.dp2.NFV.NfvReader {

	private Root webService;  // from WADL
	private Set<HostReader> allHosts = new HashSet<>();
	private Set<NffgReader> allNffgs = new HashSet<>();
	private Set<VNFTypeReader> allVnfts= new HashSet<>();
	private Map<String, Map<String, ConnectionPerformanceReader>> connections = new HashMap<>();
	
	public NfvReader(URI baseURI) throws UnknownEntityException, ServiceException {
		
		// get base uri of web service
		webService = new Root(NfvDeployer.createClient(), baseURI);
		RHosts hosts = webService.hosts().getAsRHostsXml();
		RNffgs nffgs = webService.nffgs().getAsRNffgsXml();
		RVnfts vnfts = webService.catalog().getAsRVnftsXml();
	
	
		// add all hosts
		for (ResourceName host_tmp : hosts.getRHosts()) {
			allHosts.add(new Client2HostReader(URI.create(host_tmp.getReference())));
		}
		// add all vnfts
		for (RVnft vnft_tmp : vnfts.getRVnft()) {
			allVnfts.add(new Client2VNFTypeReader(URI.create(vnft_tmp.getReference())));
		}
		// add all nffgs
		for (ResourceName nffg_tmp : nffgs.getRNffgs()) {
			allNffgs.add(new Client2NffgReader(URI.create(nffg_tmp.getReference())));
		}
		
		// map as Assignment 2 map of map for (h1- (h2 - connection))
		for (ResourceName h1 : hosts.getRHosts()) {
			connections.put(h1.getName(), new HashMap<>());
			for (ResourceName h2 : hosts.getRHosts()) {
				connections.get(h1.getName()).put(h2.getName(), new Client2ConnectionPerformanceReader(h1.getName(), h2.getName(), baseURI));
			}
		}
		
		
	}

	@Override
	public ConnectionPerformanceReader getConnectionPerformance(HostReader src, HostReader dst) {
		
		if (connections.containsKey(src.getName())) {
			if (connections.get(src.getName()).containsKey(dst.getName())) {
				return connections.get(src.getName()).get(dst.getName());
			}
		}
		
		return null;
	}

	@Override
	public HostReader getHost(String hostName) {
		
		for (HostReader hostReader : allHosts) {
			if (hostReader.getName().equals(hostName)) {
				return hostReader;
			}
		}
		
		return null;
	}

	@Override
	public Set<HostReader> getHosts() {
		return allHosts;
	}

	@Override
	public NffgReader getNffg(String nffgName) {
		
		for (NffgReader nffgReader : this.allNffgs) {
			if (nffgReader.getName().equals(nffgName)) return nffgReader;
		}
		
		return null;
	}

	@Override
	public Set<NffgReader> getNffgs(Calendar deployTime) {
		
		Set<NffgReader> set = new HashSet<>();
		
		for (NffgReader nffgReader : allNffgs) {
			
			// check null
			if (deployTime == null) {
				set.add(nffgReader);
			} 
			else {
				if (nffgReader.getDeployTime() != null) {
					if (nffgReader.getDeployTime().compareTo(deployTime) >= 0){
						set.add(nffgReader);
					}
				}
			}
		}
		
		return set;
	}

	@Override
	public Set<VNFTypeReader> getVNFCatalog() {
		return allVnfts;
	}

}

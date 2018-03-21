package it.polito.dp2.NFV.sol3.service;

import java.math.BigInteger;
import java.net.URI;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.xml.datatype.DatatypeConfigurationException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import it.polito.dp2.NFV.FactoryConfigurationError;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.NfvReaderFactory;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;

import it.polito.dp2.NFV.sol3.service.neo4j.Neo4JSimpleXML;
import it.polito.dp2.NFV.sol3.service.neo4j.Neo4JSimpleXML.Data;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.*;

@Singleton
@Path("/")
@Api(value = "/")
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceMain {
	
	private NfvDeployer nfvDeployer = null;
	private RNfv nfv = new RNfv();
	private NfvReader monitor;
	
	// define 3 main collections
	private ResourceNffgs nffgs = new ResourceNffgs();
	private ResourceHosts hosts = new ResourceHosts();
	private ResourceCatalog catalog = new ResourceCatalog();
	
	public ResourceMain(@Context UriInfo serverUri) throws NfvReaderException, FactoryConfigurationError, MyAlreadyLoadedException, UnknownEntityException, AllocationException, DatatypeConfigurationException, ServiceException {
		
		monitor = NfvReaderFactory.newInstance().newNfvReader();

		try {
			nfvDeployer = NfvDeployer.getInstance(serverUri.getBaseUri());

			nfv = nfvDeployer.newNfv();
			
			catalog = new ResourceCatalog();
			nffgs = new ResourceNffgs();
			hosts = new ResourceHosts(); 

			
			/**
			 * 
			 *  	INITIALIZE THE SYSTEM
			 * 
			 * 
			 */
			
			// upload all the vnfts
			for (VNFTypeReader t : monitor.getVNFCatalog()) {
				
				nfvDeployer.newVnft(RVnftType.fromValue(t.getFunctionalType().toString()), t.getName(),BigInteger.valueOf(t.getRequiredMemory()), BigInteger.valueOf(t.getRequiredStorage()));
			}

			// upload all hosts
			for (HostReader host : monitor.getHosts()) {
				
				try {
					nfvDeployer.newHost(host.getName(), host.getAvailableMemory(), host.getAvailableStorage(),host.getMaxVNFs());
				} 
				catch (MyAlreadyLoadedException e) {
					throw new My409Exception(e);
				} 
				catch (ServiceException e) {
					e.printStackTrace();
					throw new InternalServerErrorException(e);
				}
			}

			//upload all connections
			for (HostReader h1 : monitor.getHosts()) {
				
				for (HostReader h2 : monitor.getHosts()) {

					nfvDeployer.newConnection(h1.getName(), h2.getName(), monitor.getConnectionPerformance(h1, h2).getLatency(), monitor.getConnectionPerformance(h1, h2).getThroughput());
				}
			}

			//  fill the basic structure to complete NFFG0
			NffgReader nffgReader = monitor.getNffg("Nffg0");
			NewRnffg nffg0 = new NewRnffg();
			
			nffg0.setName(nffgReader.getName());
			NewRnffg.RNodes nodes = new NewRnffg.RNodes();
			NewRnffg.RLinks links = new NewRnffg.RLinks();
			
			
			// for each node
			for (NodeReader nnodeReader : nffgReader.getNodes()) {
				
				NewRnffg.RNodes.RNode node = new NewRnffg.RNodes.RNode();
				node.setName(nnodeReader.getName());
				node.setRVnft(nnodeReader.getFuncType().getName());
				node.setOwnerHost(nnodeReader.getHost().getName());
				
				//for each link
				for (LinkReader linkReader : nnodeReader.getLinks()) {
					
					NewRnffg.RLinks.RLink link = new NewRnffg.RLinks.RLink();
					link.setName(linkReader.getName());
					link.setDstNode(linkReader.getDestinationNode().getName());
					link.setSrcNode(linkReader.getSourceNode().getName());
					link.setMaxL(BigInteger.valueOf(linkReader.getLatency()));
					link.setMinT(linkReader.getThroughput());
					links.getRLink().add(link);
					
				}
				
				nodes.getRNode().add(node);
			}
			
			// update nffg0 final structure
			nffg0.setRNodes(nodes);
			nffg0.setRLinks(links);

			// try deployment
			nfvDeployer.tryNffgDeployment(nffg0);
			
		} 
		catch (MyNotValidExeption e) {
			
			throw new NfvReaderException(e);
		}
	}
	
	@GET
    @ApiOperation(value = "Get the main resources of nfv-system")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RNfv.class),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RNfv getNfv() {
		return nfv;
	}
	
	@Path("nffgs")
	public ResourceNffgs getNffgs() {
		return nffgs;
	}
	
	@Path("hosts")
	public ResourceHosts getHosts() {
		return hosts;
	}
	
	@Path("catalog")
	public ResourceCatalog getCatalog() {
		return catalog;
	}
}

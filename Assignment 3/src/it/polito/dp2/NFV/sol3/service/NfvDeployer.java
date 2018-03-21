package it.polito.dp2.NFV.sol3.service;

import java.math.BigInteger;
import java.net.URI;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import com.sun.jersey.api.client.ClientResponse;

import it.polito.dp2.NFV.FactoryConfigurationError;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.*;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RHost.DeployedNodes;
import it.polito.dp2.NFV.sol3.service.neo4j.Labels;
import it.polito.dp2.NFV.sol3.service.neo4j.Neo4JSimpleXML;
import it.polito.dp2.NFV.sol3.service.neo4j.Neo4JSimpleXML.Data;
import it.polito.dp2.NFV.sol3.service.neo4j.Nodes;
import it.polito.dp2.NFV.sol3.service.neo4j.Properties;
import it.polito.dp2.NFV.sol3.service.neo4j.Property;
import it.polito.dp2.NFV.sol3.service.neo4j.Relationships;
import it.polito.dp2.NFV.sol3.service.neo4j.Relationships.Relationship;

/**
 * @author matteo
 *
 */
public class NfvDeployer {
	
	// Signleton
	private static NfvDeployer deployerSingleton = null;
	
	// Paths Uri used with the base Uri of the service for hyperlinks constructions
	public static final String nffgsUri= "nffgs";
	public static final String nodesUri = "nodes";
	public static final String reachableHostsUri = "reachableHosts";
	public static final String linksUri = "links";
	public static final String catalogUri = "catalog";
	public static final String hostsUri = "hosts";
	public static final String connectionsUri = "connections";
	
	// give back the istance of my class - deprecated
	public static NfvDeployer getInstance() {
		return deployerSingleton;
	}
	
	// always called to create the istance
	public static NfvDeployer getInstance(URI absolutePath) {
		
		try {
			deployerSingleton = new NfvDeployer(absolutePath);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NfvReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return deployerSingleton;
	}
	
	// lock for read and write operations in order to manage concurrency
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	private RNfv entryPointWebService = new RNfv();
	private NfvReader nfvReader;
	private URI serviceURI;
	private String neo4jURI;
	private Data neo4jAPI;	
	private RHosts hosts = new RHosts();
	private RVnfts catalog = new RVnfts();  // renaming of vnfts as Assignment 1 
	
	/*
	 *   My collections / maps  used like the DB class shown in class
	 * 
	 */
	
	// nffgname -  nffg
	private ConcurrentMap<String, RNffg> allNffgs = new ConcurrentHashMap<>(); 
	// nffgname - map of nodes
	private ConcurrentMap<String, Map<String, RNode>> nodesByNffg = new ConcurrentHashMap<>();
	// nodename - map of link
	private ConcurrentMap<String,Map<String, RLink> > linksByNode = new ConcurrentHashMap<>();
	// nffg name - all links
	private ConcurrentMap<String, RLinks> nffgLinks = new ConcurrentHashMap<>();
	// nffg - map link
	private ConcurrentMap<String, Map<String, RLink>> allLinkNameByNffg = new ConcurrentHashMap<>();
	// nodename - nodeId
	private ConcurrentMap<String, String> nodeIds = new ConcurrentHashMap<>();
	// nodeId - node
	private ConcurrentMap<String, RNode> nodeById = new ConcurrentHashMap<>();
	// nodename - map of all nodes reachable
	private ConcurrentMap<String, Map<String, RLink>> nodesReachableFromNode = new ConcurrentHashMap<>();
	// nodename - map of all hosts reachable
	private ConcurrentMap<String, Map<String, RLink>> hostsReachableFromNode = new ConcurrentHashMap<>();
	// name - vnft
	private Map<String, RVnft> allVnfts = new HashMap<>(); 
	// hostname - connection
	private Map<String, RConnections> allConnections = new HashMap<>(); 
	 // hostname - host id
	private Map<String, String> storeHosts = new HashMap<>();
	 // host id - host 
	private Map<String, RHost> hostById = new HashMap<>();
	// (relationshipID, RLink)
	private ConcurrentMap<String, RLink> linkByRelationship = new ConcurrentHashMap<>();

	
	private NfvDeployer(URI dp2ServerUri) throws DatatypeConfigurationException, NfvReaderException, FactoryConfigurationError {
		
		    // TODO : 22/01/2018 modification
			// from the main resource i pass the contex uri of the server 
			serviceURI = dp2ServerUri;
			
			if (serviceURI == null) {
				System.out.println("[FATAL] - Error with the Uri");
			}
			
			// neo4j configuration
			neo4jURI = System.getProperty("it.polito.dp2.NFV.lab3.Neo4JSimpleXMLURL","http://localhost:8080/Neo4JSimpleXML/rest");		
			neo4jAPI = Neo4JSimpleXML.data(Neo4JSimpleXML.createClient(), URI.create(neo4jURI));
					
	
	}
	
	public RNfv newNfv() {
		
		// create the entry point of my service using the 3 main roots elements nffgs, hosts, and catalog (vnfts)
		RNfv nfv = new RNfv();
		
		nfv.setRNffgs(newReference(serviceURI  + nffgsUri));
		
		nfv.setRHosts(newReference(serviceURI + hostsUri));
		
		nfv.setRVnfts(newReference(serviceURI  + catalogUri));
		
		return nfv;
	}

	private Reference newReference(String reference) {
		
		// create the hyperlink for the resource
		Reference ref = new Reference();
		ref.setReference(reference);
		
		return ref;
	}

	private ResourceName newNameReference(String resource, String reference) {
		
		ResourceName resourceName = new ResourceName();
		
		// here i managed the class defined in the xsd schema
		// reference name that extend the reference class
		
		resourceName.setReference(reference);
		resourceName.setName(resource);
		
		return resourceName;
	}

	private RNffg newNffg(String nffgName) throws DatatypeConfigurationException {
		RNffg nffg = new RNffg();
		
		//hyperlinks
		nffg.setReference(serviceURI + nffgsUri + "/" + nffgName);
		nffg.setRNodes(newReference(serviceURI + nffgsUri + "/" + nffgName + "/" + nodesUri));
		nffg.setRLinks(newReference(serviceURI + nffgsUri + "/" + nffgName + "/" + NfvDeployer.linksUri));
		nffg.setName(nffgName);
		
		// update DB
		nodesByNffg.put(nffgName, new ConcurrentHashMap<>());
		nffgLinks.put(nffgName, new RLinks());
		allLinkNameByNffg.put(nffg.getName(), new ConcurrentHashMap<>());
		allNffgs.put(nffg.getName(), nffg);

		return nffg;
	}

	public synchronized RNode newNode(String nffgName, String nodeName, String type, boolean override)throws MyAlreadyLoadedException, UnknownEntityException, ServiceException, MyNotValidExeption {
		
		// a node newNode can be created also when the nffg is not deployed
		if (nffgName == null) {
			throw new MyNotValidExeption("NffgName missing.");
		}
		if (nodeName == null){
			throw new MyNotValidExeption("NodeName missing.");
		}
		if (type == null) {
			throw new MyNotValidExeption("Type missing.");
		}
		
		if (!allNffgs.containsKey(nffgName)) {
			throw new UnknownEntityException(nffgName + "Nffg does not exist in database.");
		}
		if (!checkVnftType(type)){
			throw new UnknownEntityException(type + "Nffg does not exist in database.");		
		}
		
		boolean isDuplicateNode = checkDuplicateNode(nodeName);

		if (isDuplicateNode && !override){
			throw new MyAlreadyLoadedException(nodeName + " already loaded.");
		}

		
		RNode node = new RNode();
		
		// create node
		node.setReference(serviceURI + nffgsUri + "/" + nffgName + "/" + nodesUri + "/" + nodeName);
		node.setReachableHosts(newReference(node.getReference() + "/" + reachableHostsUri));
		node.setName(nodeName);
		node.setRVnft(newNameReference(allVnfts.get(type).getName(), allVnfts.get(type).getReference()));
		node.setRNffg(newNameReference(nffgName, serviceURI + nffgsUri + "/" + nffgName));
		node.setRLinks(newReference(node.getReference() + "/" + linksUri));

		// create a new neo4j node
		it.polito.dp2.NFV.sol3.service.neo4j.Node requestedNode = new it.polito.dp2.NFV.sol3.service.neo4j.Node();
		
		// create labels according to  my xsd schema
		Labels labels = new Labels();
		
		labels.getLabel().add("RNode");
		
		Properties properties = new Properties();
		Property name = new Property();
		
		// set property name
		name.setName("name");
		name.setValue(nodeName);
		properties.getProperty().add(name);
		requestedNode.setProperties(properties);

		try {
		it.polito.dp2.NFV.sol3.service.neo4j.Node createdNode = neo4jAPI.node().postXml(requestedNode,it.polito.dp2.NFV.sol3.service.neo4j.Node.class);

		if (createdNode == null){
			throw new ServiceException(nodeName + " impossible to create a node in neo4j");
		}
		if (createdNode.getId() == null){
			throw new ServiceException("Id not set on neo4j for node: "+ nodeName);
		}
		
		// add replace labels
		ClientResponse response = neo4jAPI.nodeNodeidLabels(createdNode.getId()).putXml(labels, ClientResponse.class);
		
		if (response.getStatus() >= 400){
			
			throw new ServiceException(" impossible to create label for node: " + nodeName);
		}


			
		// override it using a put
		nodeIds.put(nodeName, createdNode.getId());
		nodeById.put(createdNode.getId(), node);
		nodesByNffg.get(nffgName).put(nodeName, node);

		 
		} 
		catch (WebApplicationException e) {
			
			throw new ServiceException(e);
		}

		return node;
	}
	

	public synchronized RLink newLink(String nffgName, String linkName, String srcName, String dstName, int maxL,float minT, boolean override) throws MyAlreadyLoadedException, UnknownEntityException, ServiceException, MyNotValidExeption {
		
		// new link created also without ann nffg deployed but for a nffg that is deploying
		if (!allNffgs.containsKey(nffgName)){
			throw new UnknownEntityException(nffgName + " does not exist.");
		}

		boolean isDuplicateLink = (checkDuplicateLink(nffgName, linkName) || checkDuplicateLink(nffgName, srcName, dstName));
		
		
		if (isDuplicateLink && !override){
			throw new MyAlreadyLoadedException(linkName + " already loaded.");
		}
		if (!checkDuplicateNode(srcName)){
			throw new UnknownEntityException("src node does not exist.");
		}
		if (!checkDuplicateNode(dstName)){
			throw new UnknownEntityException("dst node does not exist.");	
		}
		if (maxL < 0) {
			throw new MyNotValidExeption("negative maxL.");
		}
		if (minT < 0) {
			throw new MyNotValidExeption("negative minT.");
		}
			
		it.polito.dp2.NFV.sol3.service.neo4j.Relationship relationshipLink = new it.polito.dp2.NFV.sol3.service.neo4j.Relationship();
		
		// set forward to property
		relationshipLink.setDstNode(nodeIds.get(dstName));
		relationshipLink.setType("ForwardsTo");

		RLink newLink = new RLink();
		
		// set basic information
		newLink.setReference(serviceURI + nffgsUri + "/" + nffgName + "/" + NfvDeployer.linksUri + "/" + linkName);
		newLink.setName(linkName);
		newLink.setSrc(srcName);
		newLink.setDst(dstName);
		newLink.setMaxL(BigInteger.valueOf(maxL));
		newLink.setMinT(minT);

		try {
		
			if (isDuplicateLink) {  // duplication --> delete
				
				Relationships relationships = neo4jAPI.nodeNodeidRelationshipsOut(nodeIds.get(srcName)).getAsRelationships();
				
				for (Relationship relationship : relationships.getRelationship()) {
					
					if (relationship.getType().equals("ForwardsTo")) {
						
						//delete existing link
						if (linkByRelationship.containsKey(relationship.getId())) {
							
							// delete relationship forward to
							if (linkByRelationship.get(relationship.getId()).getName().equals(linkName)) {
								// from neo4j
								neo4jAPI.relationshipRelationshipid(relationship.getId()).deleteAsXml(ClientResponse.class);
								
								// from DB
								allLinkNameByNffg.get(nffgName).remove(linkName);
								linkByRelationship.remove(relationship.getId());
								nffgLinks.get(nffgName).getRLink().removeIf(t->t.getName().equals(linkName));
							}
						}
					}
				}
	
			}
			
			
		it.polito.dp2.NFV.sol3.service.neo4j.Relationship createdLink = neo4jAPI
						.nodeNodeidRelationships(this.nodeIds.get(srcName))
						.postXml(relationshipLink, it.polito.dp2.NFV.sol3.service.neo4j.Relationship.class);
			
			linkByRelationship.put(createdLink.getId(), newLink);
			allLinkNameByNffg.get(nffgName).put(linkName, newLink);
			nffgLinks.get(nffgName).getRLink().add(newLink);
	
			return newLink;
			
		} 
		catch (WebApplicationException e) {
			throw new ServiceException(e);
		}

	}
	

	public String newHost(String hostName, Integer availableMemory, Integer availableStorage, Integer maxVnfts)throws MyAlreadyLoadedException, ServiceException {
		
		// check host existance
		if (storeHosts.containsKey(hostName)){
			throw new MyAlreadyLoadedException(hostName+ "  does not exist.");
		}
			
		try {

			//host not in the DB
			it.polito.dp2.NFV.sol3.service.neo4j.Node neo4jHost = new it.polito.dp2.NFV.sol3.service.neo4j.Node();
			
			// create labels
			Labels labels = new Labels();
			labels.getLabel().add("RHost");
			Properties properties = new Properties();
			Property name = new Property();
			name.setName("name");
			name.setValue(hostName);
			properties.getProperty().add(name);
			
			// set name property
			neo4jHost.setProperties(properties);
	
			it.polito.dp2.NFV.sol3.service.neo4j.Node uploadedHost = neo4jAPI.node().postXml(neo4jHost,it.polito.dp2.NFV.sol3.service.neo4j.Node.class);
	
			if (uploadedHost == null){
				throw new WebApplicationException(hostName + "  not present on neo4j.");
			}
			if (uploadedHost.getId() == null){
				throw new WebApplicationException(" not id set for that host.");
			}
			
			RHost newHost = new RHost();
			
			// set hyperlink
			newHost.setReference(serviceURI + hostsUri + "/" + hostName);
			newHost.setName(hostName);
			newHost.setMemory(BigInteger.valueOf(availableMemory));
			newHost.setStorage(BigInteger.valueOf(availableStorage));
			newHost.setMaxRvnfts(BigInteger.valueOf(maxVnfts));
			newHost.setRConnections(newReference(serviceURI + hostsUri + "/" + hostName + "/" + connectionsUri));
			newHost.setDeployedNodes(new DeployedNodes());
	
			// update DB
			storeHosts.put(hostName, uploadedHost.getId());
			hostById.put(uploadedHost.getId(), newHost);
			hosts.getRHosts().add(newNameReference(hostName, serviceURI + hostsUri + "/" + hostName));
			allConnections.put(uploadedHost.getId(), new RConnections());
	
			// add labels to the node, replace if existing
			ClientResponse clientResponse = neo4jAPI.nodeNodeidLabels(uploadedHost.getId()).putXml(labels, ClientResponse.class);
			
			if (clientResponse.getStatus() >= 400){
				throw new ServiceException("impossible to create labels");
			}
			return uploadedHost.getId();
			
		} 
		catch (WebApplicationException e) {
			throw new ServiceException(e);
		}
			
	}
	
public RConnection newConnection (String src, String dest, int maxL, float minT) throws MyNotValidExeption, UnknownEntityException {
		
		
		// check if host1 exist
		if (!checkHost(src)){
			throw new UnknownEntityException("src does not xist.");
		}
		// check if host2 exist
		if (!checkHost(dest)){
			throw new UnknownEntityException("dst does not exist.");
		}
		
		if (maxL < 0){
			throw new MyNotValidExeption("maxL negative.");
		}
		if (minT < 0){
			throw new MyNotValidExeption("minT negative.");
		}
		
		RConnection newConnection = new RConnection();
		
		newConnection.setReference(serviceURI + hostsUri + "/" + src + "/" + connectionsUri);
		newConnection.setSrc(src);
		newConnection.setDst(dest);
		newConnection.setMaxL(BigInteger.valueOf(maxL));
		newConnection.setMinT(Float.valueOf(minT));

		// update DB
		allConnections.get(this.storeHosts.get(src)).getRConnections().add(newConnection);
		
		return newConnection;
	}





	public RVnft newVnft(RVnftType type, String vnftName, BigInteger memory, BigInteger storage) {
		
		RVnft vnft = new RVnft();
		
		// add info
		vnft.setReference(serviceURI + catalogUri + "/" + vnftName);  //hyperlink
		vnft.setName(vnftName);
		vnft.setVnftype(type);			
		vnft.setMemory(memory);
		vnft.setStorage(storage);
		
		// update DB
		catalog.getRVnft().add(vnft);
		allVnfts.put(vnftName, vnft);

		return vnft;
	}



 /***
  * 
  *  TRY THE DEPLOYMENT OF A COMPLETE NFFG
  */
	
	
	

	public synchronized boolean allocateNode(String nodeName, String suggestedHost) throws AllocationException, UnknownEntityException {
		
		if (!checkDuplicateNode(nodeName)){
			throw new UnknownEntityException(nodeName + " does not exist.");
		}
		
		if (suggestedHost != null && !storeHosts.containsKey(suggestedHost)){
				throw new UnknownEntityException("suggested host not found.");
		}
		
		if (nodeById.get(nodeIds.get(nodeName)).getRHost() != null){
			throw new AllocationException(nodeName+" already allocated");
		}
			
		// link node to host
		RNode rNode = nodeById.get(nodeIds.get(nodeName));
		
		
		if (!checkVnftType(rNode.getRVnft().getName())){
			throw new UnknownEntityException("VNF type does not exist");
		}
		
		RVnft rVnft = allVnfts.get(rNode.getRVnft().getName());
		
		it.polito.dp2.NFV.sol3.service.neo4j.Relationship allocatedOn = new it.polito.dp2.NFV.sol3.service.neo4j.Relationship();
		
		int allocationMemory = 0;
		int allocationStorage = 0;
		
		if (rVnft.getMemory() != null){
			allocationMemory = rVnft.getMemory().intValue() ;
		}
		if(rVnft.getStorage() != null){
			allocationStorage = rVnft.getStorage().intValue();
		}
		
		if (suggestedHost != null) {
			
			// (1) FIRST TRY ALLOCATION ON THE PREFERD HOST
			RHost targetHost = hostById.get(storeHosts.get(suggestedHost));
			
			// get host info
			int availableMemory =0;
			int availableStorage = 0;
			int deployedVnfs = 0;
			int maxVnfs = 0;
			
			if(targetHost.getMemory() != null) {
				availableMemory = targetHost.getMemory().intValue();
			}
			
			if(targetHost.getStorage() != null) {
				availableStorage = targetHost.getStorage().intValue();
			}
			
			if (targetHost.getDeployedNodes() != null) {
				deployedVnfs = targetHost.getDeployedNodes().getRNodes().size();
			}
			
			if(targetHost.getMaxRvnfts() != null) {
				maxVnfs = targetHost.getMaxRvnfts().intValue();
			}
			
			// the condition !!!!
			if (allocationMemory < availableMemory && allocationStorage < availableStorage && deployedVnfs < maxVnfs) {
				
				// allocate node to host
				hostById.get(storeHosts.get(suggestedHost)).getDeployedNodes().getRNodes().add(newNameReference(nodeName, rNode.getReference()));
				
				// create relationship
				nodeById.get(nodeIds.get(nodeName)).setRHost(newNameReference(targetHost.getName(), targetHost.getReference()));
				allocatedOn.setDstNode(storeHosts.get(suggestedHost));
				allocatedOn.setType("AllocatedOn");
				
				// upload on neo4j
				neo4jAPI.nodeNodeidRelationships(this.nodeIds.get(nodeName))
						.postXml(allocatedOn, it.polito.dp2.NFV.sol3.service.neo4j.Relationship.class);
				
				
				return true;
			}
		}
		
		// (2) - SCAN ALL HOSTS
		
		for (RHost host_tmp : hostById.values()) {
			
			// get host info
			int availableMemory = 0;
			int availableStorage = 0;
			int deployedVnfs = 0;
			int maxVnfs = 0;
			
			if (host_tmp.getMemory() != null){
				availableMemory = host_tmp.getMemory().intValue();
			}
			
			if(host_tmp.getStorage() != null){
				availableStorage = host_tmp.getStorage().intValue();
			}
			
			if(host_tmp.getDeployedNodes() != null){
				deployedVnfs = host_tmp.getDeployedNodes().getRNodes().size();
			}
			
			if(host_tmp.getMaxRvnfts() != null){
				maxVnfs =  host_tmp.getMaxRvnfts().intValue();
			}
			
			
			if (allocationMemory < availableMemory && allocationStorage < availableStorage && deployedVnfs < maxVnfs) {
				
				// get host
				hostById.get(storeHosts.get(host_tmp.getName())).getDeployedNodes().getRNodes().add(newNameReference(nodeName, rNode.getReference()));
				
				//create relationship
				nodeById.get(nodeIds.get(nodeName)).setRHost(newNameReference(host_tmp.getName(), host_tmp.getReference()));
				allocatedOn.setDstNode(this.storeHosts.get(host_tmp.getName()));
				allocatedOn.setType("AllocatedOn");
				
				// upload relationship on neo4j
				neo4jAPI.nodeNodeidRelationships(this.nodeIds.get(nodeName))
				.postXml(allocatedOn, it.polito.dp2.NFV.sol3.service.neo4j.Relationship.class);
				
				return true;
			}
			
		}

		throw new AllocationException("Limit in terms of resource - not possible to allocate.");


	}
	
	public synchronized RNffg tryNffgDeployment(NewRnffg completeNffg)throws  AllocationException, MyAlreadyLoadedException, UnknownEntityException, MyNotValidExeption, DatatypeConfigurationException, ServiceException {
		
		// single thread deployment
		boolean isDeployed = false;
		
		if (allNffgs.containsKey(completeNffg.getName())){
			throw new MyAlreadyLoadedException(completeNffg.getName() + " already deployed.");
		}

		if (completeNffg.getName() == null){
			throw new MyNotValidExeption(" Error no nffg name.");
		}
		
		// check the constraints for each node of my complete nffg
		for (NewRnffg.RNodes.RNode completeNode : completeNffg.getRNodes().getRNode()) {
			
			if (completeNode.getName() == null){
				throw new MyNotValidExeption("Error no node name");
			}
			if (completeNode.getRVnft() == null){
				throw new MyNotValidExeption("Error not valid vnft");
			}
			if (completeNode.getOwnerHost() != null) {
				if (!checkHost(completeNode.getOwnerHost())){
					throw new UnknownEntityException("Owner Host does not exist");
				}
			}
			if (checkDuplicateNode(completeNode.getName())){
				throw new MyAlreadyLoadedException("Errror Node already existing.");
			}
			if (!checkVnftType(completeNode.getRVnft())){
				throw new UnknownEntityException("Error not valid VnftType.");
			}
			
		}
		
		// check all constraints for each link
		for (NewRnffg.RLinks.RLink completeLink : completeNffg.getRLinks().getRLink()) {
			
			if (completeLink.getName() == null) {
				throw new MyNotValidExeption("Error link without name.");
			}
			if (completeLink.getDstNode() == null){
				throw new MyNotValidExeption("Error link without src");
			}
			if (completeLink.getSrcNode() == null){
				throw new MyNotValidExeption("Error link without src");
			}
		
			// check values of link
			float minT = completeLink.getMinT();
			int maxL = 0;
			if (completeLink.getMaxL() != null) {
				
				maxL = completeLink.getMaxL().intValue();
			}
			
			if (maxL < 0) {
				throw new MyNotValidExeption("Error negatice maxL");
			}
			
			if (minT < 0) {
				throw new MyNotValidExeption("Error negative minT");
			}
		}
		
		/**
		 *  ACQUIRE LOCK
		 */
		
		lock.writeLock().lock();

		try {
			
			newNffg(completeNffg.getName());
			
			// upload all nodes for the complete nffg
			for (NewRnffg.RNodes.RNode nodeToUpload : completeNffg.getRNodes().getRNode()) {
				
				// create base obj
				newNode(completeNffg.getName(), nodeToUpload.getName(), nodeToUpload.getRVnft(), false);
				// try allocation
				// here something can be thrown and stop the deployment
				allocateNode(nodeToUpload.getName(), nodeToUpload.getOwnerHost());
				
			}
			
			// upload all links for the complete nffg 
			for (NewRnffg.RLinks.RLink linkToUpload : completeNffg.getRLinks().getRLink()) {
				
				if (!checkDuplicateNode(linkToUpload.getSrcNode())){
					throw new UnknownEntityException("Error src node does not exists.");
				}
				if (!checkDuplicateNode(linkToUpload.getDstNode())){
					throw new UnknownEntityException("Error dst node does not exist.");
				}
				
				float minT = (linkToUpload.getMinT());
				int maxL = 0;
				if(linkToUpload.getMaxL() != null){
					maxL =linkToUpload.getMaxL().intValue();
				}
				
				newLink(completeNffg.getName(), linkToUpload.getName(), linkToUpload.getSrcNode(), linkToUpload.getDstNode(),maxL, minT, false);
			}
			
			// set "REAL TIME" FOR DEPLOYMENT
			allNffgs.get(completeNffg.getName()).setDeployTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar()));
			
			isDeployed = true;
			
			return allNffgs.get(completeNffg.getName());
		}

		finally {
			
			// IF THERE IS A ERROR PERFORM THE ROLLBACK PROCEDURE
			if (!isDeployed){
				rollbackOnNffg(completeNffg.getName());
			}
			
			lock.writeLock().unlock();
			
			/***
			 *   RELEASE LOCK
			 */
		}

	}
	
	/**
	 * 
	 *  	METHODS USED TO RETRIEVE SYSTEM INFORMATION
	 * 
	 */

	public RNfv getNfv() {
		// nfv of HATEOS web service
		return entryPointWebService;
	}

	public RNffgs getNffgs(Date date) {
		
		RNffgs resultNffgs = new RNffgs();
		
		/**
		 *  ACQUIRE LOCK
		 */
		lock.readLock().lock();
		
		try {
			
			// show only complete deployed nffgs
			
			for (RNffg nffg_tmp : allNffgs.values()) {
				
				// not deployed ???
				
				if (nffg_tmp.getDeployTime() != null) {
					
					// (1) - GET ALL NFFGS WITHOUT THE DATE FILTER
					if (date == null) {
						
						// create nffg with associated hyperlink
						// name - reference
						resultNffgs.getRNffgs().add(newNameReference(nffg_tmp.getName(), serviceURI + nffgsUri + "/" + nffg_tmp.getName()));
					} 
					// (2) - GET  NFFGS WITH THE DATE FILTER
					else {
						
						// coversion of dateTime in gregorian and in date
						if (allNffgs.get(nffg_tmp.getName()).getDeployTime().toGregorianCalendar().getTime().compareTo(date) >= 0) {
							// create nffg with associated hyperlink
							// name - reference
							resultNffgs.getRNffgs().add(newNameReference(nffg_tmp.getName(), serviceURI + nffgsUri + "/" + nffg_tmp.getName()));
						}
					}
					
				}
				
			}

			return resultNffgs;
		} 
		finally {
			
			lock.readLock().unlock();
			/**
			 *  RELEASE LOCK
			 */
		}

	}

	public RNffg getNffgByName(String nffgName) throws UnknownEntityException {
		
		if (nffgName == null || !checkDeployment(nffgName)) {
			throw new UnknownEntityException(nffgName +" missign in the database.");
		}
		
		/**
		 *  ACQUIRE LOCK
		 */
		lock.readLock().lock();
		
		try {
			
			return allNffgs.get(nffgName);
			
		} finally {
			/**
			 *  RELEASE LOCK
			 */
			lock.readLock().unlock();
		}
	}

	public RNode getNode(String nffgName, String nodeName) throws UnknownEntityException {
		
		// check in database
		if (!checkDeployment(nffgName)){
			throw new UnknownEntityException(nffgName +" missign in the database.");
		}
		// check node in nffg
		if (!nodeIds.containsKey(nodeName)){
			throw new UnknownEntityException(nodeName +  " note present in the nffg: " + nffgName);
		}
		
		// check node in neo4j
		String neo4jId = nodeIds.get(nodeName);
		
		if (!nodeById.containsKey(neo4jId)){
			throw new UnknownEntityException(nodeName + " not loaded on neo4j.");
		}
			
		return nodeById.get(neo4jId);		
			
	}

	public RNodes getAllNodesOfNffg(String nffgName) throws UnknownEntityException {
	
		
		RNodes resultNodes = new RNodes();
		
		if (nffgName == null || !checkDeployment(nffgName)){
			throw new UnknownEntityException(nffgName +" missign in the database.");
		}
	
		for (RNode node_tmp : nodesByNffg.get(nffgName).values()) {
			
			// create a name - hyperlink 
			ResourceName referenceName = new ResourceName();
			
			referenceName.setName(node_tmp.getName());
			referenceName.setReference(node_tmp.getReference());
			
			// add locally
			resultNodes.getRNodes().add(referenceName);
		}
		
		
		return resultNodes;	
	}
	
	public RNodes getAllNodesOfNfv() {
		
		// get all nodes in the System
		RNodes allNodes = new RNodes();
		
		for (String nffg_tmp : nodesByNffg.keySet()) {
			
			if (checkDeployment(nffg_tmp)) {
				
				for (RNode node_tmp : nodesByNffg.get(nffg_tmp).values()) {
					
					ResourceName referenceName = new ResourceName();
					
					referenceName.setName(node_tmp.getName());
					referenceName.setReference(node_tmp.getReference());
					
					// add locally
					allNodes.getRNodes().add(referenceName);
				}
			}
		}
		
		
		return allNodes;
	}
	
	public RHosts getReachableHosts(String nffgName, String nodeName) throws UnknownEntityException, ServiceException {
		
		
		RHosts result = new RHosts();
		
		// check deployment
		if (!checkDeployment(nffgName)){
			
			throw new UnknownEntityException(nffgName +" missign in the database.");
		}
		
		// check node in the nffg
		if (!nodeIds.containsKey(nodeName)){
			
			throw new UnknownEntityException(nodeName +  " note present in the nffg: " + nffgName);
		}

		// Nodes of neo4j
		Nodes reachableHosts = neo4jAPI.nodeNodeidReachableNodes(nodeIds.get(nodeName)).getAsNodes(null, "RHost");
		
		// read neo4j properties of reachable hosts
		for (Nodes.Node neoNode : reachableHosts.getNode()) {
			
			String hostName = null;
			
			for (Property property : neoNode.getProperties().getProperty()) {
				
				if (property.getName().equals("name"))
					hostName = property.getValue();
			}
			
			if (hostName == null){
				throw new ServiceException(neoNode.getId() +" has no -name- property");
			}
			
			RHost host_tmp =hostById.get(neoNode.getId());
			
			// save locally
			result.getRHosts().add(newNameReference(host_tmp.getName(), host_tmp.getReference()));
		}

		return result;

			
	}
	

	public RLink getLink(String nffgName, String linkName) throws UnknownEntityException {
		
		// check for nffg  database
		if (!checkDeployment(nffgName)) {
			throw new UnknownEntityException(nffgName +" missign in the database.");
		}
		
		// check in the links association
		if (!allLinkNameByNffg.containsKey(nffgName)) {
			throw new UnknownEntityException(nffgName + " defined in the database but probably not already deployed.");
		}
		
		// finally check the presence of link in the nffg
		if (!allLinkNameByNffg.get(nffgName).containsKey(linkName)) {
			throw new UnknownEntityException(linkName + " not present in the nffg:" + nffgName);
		}
		
		// all fine return the link
		return allLinkNameByNffg.get(nffgName).get(linkName);
	
	}

	public RLinks getLinks(String nffgName, String nodeName) throws UnknownEntityException {
		
		// check for nffg  database
		if (!checkDeployment(nffgName)){
			throw new UnknownEntityException(nffgName +" missign in the database.");
		}
		
		// get all links of the nffg COARSE FILTER
		if (nodeName == null){
				return nffgLinks.get(nffgName);
		}
		
		// check if node exist
		if (!nodeIds.containsKey(nodeName)){
			throw new UnknownEntityException(nodeName + " does not exist.");
		}
		
		RLinks linksFromMyNode = new RLinks();
		// get relationships of node
		Relationships nodeRelationships = neo4jAPI.nodeNodeidRelationshipsOut(nodeIds.get(nodeName)).getAsRelationships();
		
		
		// for each relationship
		for (Relationship relationship : nodeRelationships.getRelationship()) {
			
			// add a link for each relationship id
			// i've mapped them relationship ID- link
			linksFromMyNode.getRLink().add(linkByRelationship.get(relationship.getId()));
		}
		
		return linksFromMyNode;
	
	}
	
	
	public RHosts getHosts() {
		return hosts;
	}
	
	public RHost getHostByName(String hostName) throws UnknownEntityException {
		
		// check for host in db
		if (!storeHosts.containsKey(hostName)){
			throw new UnknownEntityException(hostName + " missing in the database!");
		}
			
		String neo4jId = storeHosts.get(hostName);
		
		// control if it s uploaded on neo4j
		if (!hostById.containsKey(neo4jId)){
			throw new UnknownEntityException(hostName + " not loaded on neo4j");
		}
				
		return hostById.get(neo4jId);
			
	}

	public RConnections getConnections(String hostName) throws UnknownEntityException {
		
		// check for db presence
		if (!storeHosts.containsKey(hostName)) {
			throw new UnknownEntityException(hostName + " has not connections!");
		}
			
		String neoId = storeHosts.get(hostName);
		
		// check on neo4j if present
		if (!allConnections.containsKey(neoId)){
			throw new UnknownEntityException(hostName + " not loaded on Neo4j!");
		}
				
		return allConnections.get(neoId);
	
	}
	
	public RVnfts getCatalog() {
		// correspond to the vnfts --> changed name from Assignment 1
		return catalog;
	}

	public RVnft getVnftByName(String vnftName) throws UnknownEntityException {
		
		if (allVnfts.containsKey(vnftName)){
			return allVnfts.get(vnftName);
		}
		else{
			throw new UnknownEntityException(vnftName + " does not exist.");
		}
	}
	
	/**
	 *  	CHECK - SUITE
	 *  	Used to check some useful parameters in my context
	 * 
	 */

	// check if a nffg is already deployed
	public boolean checkDeployment(String nffgName) {
		
		if (!allNffgs.containsKey(nffgName) || allNffgs.get(nffgName).getDeployTime() == null){
			return false;
		}	
		return true;
	}
	
	// check link presence in the nodes maps in case of a not already deployed nffg
	public boolean checkDuplicateNode(String linkName) {
		
		if (linkName == null || !nodeIds.containsKey(linkName) || !nodeById.containsKey(nodeIds.get(linkName))) {
			return false;
		}
		
		return true;
	}
	
	// check for a duplicate link with same same
	public boolean checkDuplicateLink(String nffgName, String linkName) {
		
		if (!allLinkNameByNffg.containsKey(nffgName) || linkName == null || !allLinkNameByNffg.get(nffgName).containsKey(linkName)){
			return false;
		}
		return true;
	}
	

	private boolean checkDuplicateLink(String nffgName, String srcName, String dstName) {
		
		if (srcName == null || dstName == null || !allNffgs.containsKey(nffgName)){
			return false;
		}
		if (!checkDuplicateNode(srcName) || !checkDuplicateNode(dstName)){
			return false;
		}
		if (!allLinkNameByNffg.containsKey(nffgName) || !allLinkNameByNffg.get(nffgName).containsKey(srcName)){
			return false;
		}
		
		Relationships linksFromNode = neo4jAPI.nodeNodeidRelationshipsOut(nodeIds.get(srcName)).getAsRelationships();
		
		for (Relationship relationship : linksFromNode.getRelationship()) {
			// match relationship base on the name of dst (forwardTo)
			if (linkByRelationship.get(relationship.getId()).getName().equals(dstName)){
				return true;
			}
		}
		
		return false;
	}
		
	// check the if the vnfttype exist
	public boolean checkVnftType(String vnftName) {
		
		if (vnftName == null || !allVnfts.containsKey(vnftName)) {
			return false;
		}		
		return true;
	}
	

	
	// check for host existance
	public boolean checkHost(String hostName) {
		
		if (hostName == null || !storeHosts.containsKey(hostName) || !hostById.containsKey(storeHosts.get(hostName))){
			return false;
		}

		return true;
	}
	
	/***
	 * 
	 *  ROLLBACK- SUITE
	 *  each rollback operation is used in case of a bad deployment
	 *  the errors can be related due to node allocation, node duplicate or link duplicate
	 *
	 */
	
	private synchronized boolean rollbackOnAllocation(String nodeName) throws ServiceException {
		
		// bad call
		if (!checkDuplicateNode(nodeName)){
			return false; 
		}
		
		RNode nodeToDelete = nodeById.get(nodeIds.get(nodeName));
		// remove allocation info from node
		nodeById.get(nodeIds.get(nodeName)).setRHost(null);  
		
		// allocation was performed when i called rollback
		if (nodeToDelete.getRHost() != null) { 
		
			if (checkHost(nodeToDelete.getRHost().getName())) {
				// update host and remove the allocation
				String id = storeHosts.get(nodeToDelete.getRHost().getName());
				hostById.get(id).getDeployedNodes().getRNodes().removeIf(n->n.getName().equals(nodeName));
	
			}
		}
		
		try {
			// exploit neo4j relationships
			
			Relationships relationships = neo4jAPI.nodeNodeidRelationshipsOut(nodeIds.get(nodeToDelete.getName())).getAsRelationships();
			
			for (Relationship relationship : relationships.getRelationship()) {
				
				// get allocation relationship and delete it !!! from neo4j
				if (relationship.getType().equals("AllocatedOn")) {
					neo4jAPI.relationshipRelationshipid(relationship.getId()).deleteAsXml(ClientResponse.class);
				}	
			
			}

			return true;
			
		} catch (WebApplicationException e) {
			
			throw new ServiceException(e);
		}	

	}

	private synchronized boolean rollbackOnLink(String nffgName, String nodeName) throws ServiceException {
		
		// delete node from nffg and node
		
		nffgLinks.get(nffgName).getRLink().removeIf(l -> l.getSrc().equals(nodeName));
		
		try {
			
			Relationships relationships = 
			neo4jAPI.nodeNodeidRelationshipsOut(nodeIds.get(nodeName)).getAsRelationships();
			
			for (Relationship relationship : relationships.getRelationship()) {
				
				if (relationship.getType().equals("ForwardsTo")) {
					
					// delete from neo4j
					neo4jAPI.relationshipRelationshipid(relationship.getId()).deleteAsXml(ClientResponse.class);
					
					// remove from DB
					if (linkByRelationship.containsKey(relationship.getId())) {
					
						allLinkNameByNffg.get(nffgName).remove(linkByRelationship.get(relationship.getId()).getName());
						linkByRelationship.remove(relationship.getId());
					
					}
					
				}
			}
			
			return true;
			
		} 
		catch (WebApplicationException e) {
			
			throw new ServiceException(e);
		}
	}

	public synchronized boolean rollbackOnNffg(String name) throws UnknownEntityException, ServiceException {
		
		// bad call
		if (!allNffgs.containsKey(name)){
			return false; 
		}
		/**
		 *  ACQUIRE LOCK
		 */
		lock.writeLock().lock();
		
		try {
			
			// rollback on sub elements (nodes and links)
			for (RNode n : nodesByNffg.get(name).values()) {
				rollbackOnNode(allNffgs.get(name).getName(), n.getName());
			}
			
			// delete nffg from the DB
			allNffgs.remove(name);
			nodesByNffg.remove(name);
			nffgLinks.remove(name);
			allLinkNameByNffg.remove(name);
			
			return true;
			
		} 
		finally {
			
			lock.writeLock().unlock();
			
			/**
			 *  RELEASE LOCK
			 */
		}
	}

	public synchronized boolean rollbackOnNode(String nffgName, String nodeName) throws UnknownEntityException, ServiceException {
		
		// not valid nffg
		if (!allNffgs.containsKey(nffgName)){
			throw new UnknownEntityException();
		}
		
		//badd call
		if (!checkDuplicateNode(nodeName)){
			return false;
		}
		
		// remove node allocation and links
		rollbackOnAllocation(nodeName);
		rollbackOnLink(nffgName, nodeName);
		
		// remove from neo4j
		neo4jAPI.nodeNodeid(nodeIds.get(nodeName)).deleteAsXml(ClientResponse.class);
		
		// update DB
		nodesByNffg.get(nffgName).remove(nodeName);
		nodeById.remove(nodeIds.get(nodeName));
		nodeIds.remove(nodeName);
		
		return true;
			
	}
	

	


}

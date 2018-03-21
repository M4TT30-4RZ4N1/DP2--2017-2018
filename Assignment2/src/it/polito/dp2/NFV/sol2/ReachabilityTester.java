package it.polito.dp2.NFV.sol2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.lab2.AlreadyLoadedException;
import it.polito.dp2.NFV.lab2.ExtendedNodeReader;
import it.polito.dp2.NFV.lab2.NoGraphException;
import it.polito.dp2.NFV.lab2.ReachabilityTesterException;
import it.polito.dp2.NFV.lab2.ServiceException;
import it.polito.dp2.NFV.lab2.UnknownNameException;

public class ReachabilityTester implements it.polito.dp2.NFV.lab2.ReachabilityTester {
	
	private NfvReader nfvReader;
	private Client client;
	private String url;	
	private WebTarget webTarget;
	
	// structure instantiated for internal use:
	
	// a map for nffg in order to retrieve the entry point (nffgReader) given an nffgName
	private HashMap<String,NffgReader> nffgMap = new HashMap<String,NffgReader>();
	// hashmap (with only keys) used to check if an nffg is already loaded
	private HashMap<String,Object> nffgLoaded = new HashMap<String,Object>();
	// structure used with key = nffg value = set of entities (node or relationship) associated to that nffg
	private HashMap<String,Set<Node>> nodeMap = new HashMap<String,Set<Node>>();
	private HashMap<String,Set<Relationship>> relationshipNodeMap = new HashMap<String,Set<Relationship>>();
	// the key is the host name, cause host allocation can be overlapped to nffgs
	private HashMap<String,Node> hostMap = new HashMap<String, Node>();
	private HashMap<String,Set<Relationship>> relationshipHostMap = new HashMap<String,Set<Relationship>>();

	public ReachabilityTester(NfvReader nfvReader) {
		
		// set the nfvReader (the class that gives all the information of generated data)
		this.nfvReader = nfvReader;
		// create HashMap of nffgReader
		for (NffgReader nffgReader : nfvReader.getNffgs(null)){
			nffgMap.put(nffgReader.getName(), nffgReader);
		}
		// instantiate the client (nb: need to release it)
		this.client = ClientBuilder.newClient();
		// get the url from a system property, throw an exception if null
		this.url = System.getProperty ("it.polito.dp2.NFV.lab2.URL");		
		// check url
		if (url == null)
			throw new NullPointerException();
		// if it is all fine --> instantiate the taget
		this.webTarget = client.target(url);
		
	}

	// this method create the graph of the nffg, i choose to allocate only host of nffg, not all hosts of IN
	@Override
	public void loadGraph(String nffgName) throws UnknownNameException, AlreadyLoadedException, ServiceException {
		
		// check the nffgName
		if(nffgName == null || !verifyNffgNameExistence(nffgName))
			throw new UnknownNameException();
		// check if it is already loaded
		if(isLoaded(nffgName)){
			throw new AlreadyLoadedException();
		}
		else{ // all is fine so i can proceed to load the Graph
			
			// create one Graph Node for each nffgNode and one Graph Node for each nffgHost
			populateNodes(nffgName);
			
			// create local set passed to the function (contain also the node and host already allocated)
			Set<Node> hostSet = new HashSet<Node>(hostMap.values());
			
			// create the labels
			
			//for each set of each nffg
			for(String nffg : nodeMap.keySet()){
				tryCreateNodeLabel(nodeMap.get(nffg), "Node");
			}	
			// for each host
			tryCreateNodeLabel(hostSet, "Host");
			
			// create the relationships
			tryCreateNodeRelation(nffgName);
			tryCreateHostRelation(nffgName);
			
			// add the nffg to the list of loaded, no need values
			nffgLoaded.put(nffgName, null);
			
		}
		
	}

	// return the Nodes of a specific Nffg
	@Override
	public Set<ExtendedNodeReader> getExtendedNodes(String nffgName)
			throws UnknownNameException, NoGraphException, ServiceException {
			
		// check the nffgName
		if(nffgName == null || !verifyNffgNameExistence(nffgName))
			throw new UnknownNameException();
		
		// check if the nffg has been loaded
		if (!isLoaded(nffgName))
			throw new NoGraphException();
		
		// create the local set that will returned
		Set<ExtendedNodeReader> extendedNodeSet = new HashSet<ExtendedNodeReader>();
		
		// get the nffg base on name
		NffgReader nffgReader = nffgMap.get(nffgName);
	
		// for each node in the nffg
		for(NodeReader nodeReader : nffgReader.getNodes()){
			try{
				Node node = getNode(nodeReader, nffgName);
				// pass to the ExtendedNodeReader class all the necessary to perform a get request for ReachableNodes	
				ConcreteExtendedNodeReader extendedNodeReader = new ConcreteExtendedNodeReader(node, nodeReader, nfvReader.getHosts(), webTarget);
				ExtendedNodeReader tmp = (ExtendedNodeReader) extendedNodeReader;
				extendedNodeSet.add(tmp);
			}
			catch(NullPointerException e){
				throw new ServiceException();
			}
					
		} // close node


		return extendedNodeSet;
	}

	// check if the graph is Loaded --> all nffgs allocated ???
	@Override
	public boolean isLoaded(String nffgName) throws UnknownNameException {
		// check the nffgName
		if(nffgName == null)
			throw new UnknownNameException();
		
		// loaded
		if(nffgLoaded.containsKey(nffgName))
			return true;
		else // not loaded
			return false;
	}
	
	// check if the nffg Name exist
	private boolean verifyNffgNameExistence(String nffgName) {
		// TODO Auto-generated method stub
			if (nffgMap.containsKey(nffgName))
				return true;
			else
				return false;		
	}
		
	private void populateNodes(String nffgName) throws ServiceException {
		
		// get the nffg
		NffgReader nffgReader = nffgMap.get(nffgName);
						
		//here i have to create the set of nodes to put inside the map
		Set<Node> localNodeSet = new HashSet<Node>();
		
		// for each node in the nffg create a node used from post method
		for(NodeReader nodeReader : nffgReader.getNodes()){
			//create the node
			Node nodeToPost = new Node();
			
			// create the name property
			Property nodeProperty = new Property();
			nodeProperty.setName("name");
			nodeProperty.setValue(nodeReader.getName());
			Properties nodeProperties = new Properties();
			nodeProperties.getProperty().add(nodeProperty); 
			
			// set node's properties
			nodeToPost.setProperties(nodeProperties); // property 0
							
			// try to post node
			try{								
				//if success, save it to the set
				Node nodeToGet = null; 
				if((nodeToGet = tryPostNode(nodeToPost)) != null) 
						localNodeSet.add(nodeToGet); // add node in the response (it has the id)
				// node are unique for the entire system, i don't need to check for duplicate
			}
			catch(ServiceException e){
				throw new ServiceException();
			}
			
							
			//create the host
			Node hostToPost = new Node();
							
			// create the property
			Property hostProperty = new Property();
			hostProperty.setName("name");
			hostProperty.setValue(nodeReader.getHost().getName()); // host where the node is allocatedOn
			Properties hostProperties = new Properties();
			
			// set host's properties
			hostProperties.getProperty().add(hostProperty);  // property 0
			
			// set host's properties
			hostToPost.setProperties(hostProperties); // property 0
			
			//post host refered to that node 
			try{
				Node hostToGet = null; 
				// try to post node(host) only if it doens't already exist
				if(!verifyHostExistance(nodeReader.getHost().getName())){
					if ((hostToGet=tryPostNode(hostToPost)) != null){
						// all fine so add it to the global map
						hostMap.put(nodeReader.getHost().getName(), hostToGet); // save host data
					}
				}						
			}
			catch(ServiceException e){
					throw new ServiceException();
			}
			
		} // end nodeReader
		
		// save data (all nodes saved at the end all together)
		if(nodeMap.get(nffgName) == null)
			nodeMap.put(nffgReader.getName(),localNodeSet);									
	}
	
	// method used to eventually create a node Graph istance
	private Node tryPostNode(Node myNode) throws ServiceException {

		// perform the request to the web target
		Response response = webTarget.path("/data/node/")
								     .request("application/xml")
								     .post(Entity.xml(myNode),Response.class);

		// check the response code 
		int statusCode = response.getStatus();
		if(statusCode == 201){			
			return response.readEntity(Node.class);	
		}
		else if(statusCode == 400){
			System.out.println("tryPostNode: Bad Request"); 
			throw new ServiceException();
		}	
		else if (statusCode == 500){
			System.out.println("tryPostNode: Server Error"); 
			throw new ServiceException();
		}
		
		return null;
		
	}
	// method used to post the node's label
	private void tryCreateNodeLabel(Set<Node> nodeSet, String labelName) throws ServiceException{
			
		for (Node node : nodeSet){
			
			// create the label
			Labels labels = new Labels();
			labels.getLabel().add(labelName);
			
			// perform the request to the web target
			Response response = webTarget.path("/data/node/" + node.getId() +"/labels")
										 .request("application/xml")
										 .post(Entity.xml(labels),Response.class);
			
			// check the response code 
			int statusCode = response.getStatus();
			if (statusCode == 204){				
				//System.out.println("tryCreateNodeLabel: Ok 204"); 
			}
			else if(statusCode == 400){
				System.out.println("tryCreateNodeLabel: Bad Request"); 
				throw new ServiceException();
			}
			else if(statusCode == 404){
				System.out.println("tryCreateNodeLabel: File Not Found"); 
				throw new ServiceException();
			}
			else if(statusCode == 500){
				System.out.println("tryCreateNodeLabel: Internal Server Error"); 
				throw new ServiceException();
			}
			
		}	
		
	}
	
	// method used to create for each nffgNode a relationship "ForwardsTo"
	private void tryCreateNodeRelation(String nffgName) throws UnknownNameException, ServiceException{
		
		// get the nffg
		NffgReader nffgReader = nffgMap.get(nffgName);
		
		// create set of relationships
		Set<Relationship> forwardRelationshipSet = new HashSet<Relationship>();
		
		// for each node of nffg
		for (NodeReader nodeReader : nffgReader.getNodes()){
				
			// for each link of each node
			for(LinkReader linkReader : nodeReader.getLinks() ){
				
				// create a relationship from srcNode to dstNode
				Relationship relationshipToPost = new Relationship();
				String srcNodeId = getNodeId(linkReader.getSourceNode(),nffgReader);
				String dstNodeId = getNodeId(linkReader.getDestinationNode(), nffgReader);
				
				if(srcNodeId == null || dstNodeId == null){
					throw new UnknownNameException();
				}
				relationshipToPost.setDstNode(dstNodeId);
				relationshipToPost.setType("ForwardsTo");

				try{
					Relationship relationshipToGet = null; 
					if ((relationshipToGet = tryCreateNodeRelationship(relationshipToPost, srcNodeId)) != null)
						forwardRelationshipSet.add(relationshipToGet);

				}
				catch ( ServiceException e ){
					throw new ServiceException();
				}
			} // close linkReader
			
		} // close nodeReader
		
		// add the relation to the global map
		relationshipNodeMap.put(nffgReader.getName(),forwardRelationshipSet);

	}
	

	
	// method used to create for each nffgHost a relationship "AllocatedOn"
	private void tryCreateHostRelation(String nffgName) throws ServiceException{
		
		// get the nffg
		NffgReader nffgReader = nffgMap.get(nffgName);
		
		// create the set of relationships
		Set<Relationship> allocatedRelationshipSet = new HashSet<Relationship>();
		
		// for each node of the nffg
		for(NodeReader nodeReader : nffgReader.getNodes()){
			
			// get nodeId
			String nodeId = getNodeId(nodeReader, nffgReader);
			// create a relationship
			Relationship relationshipToPost = new Relationship();
			String hostId = getHostId(nodeReader.getHost(), nffgReader);
			relationshipToPost.setDstNode(hostId);
			relationshipToPost.setType("AllocatedOn");

			try{ // associate the relation  nffgNode(Node) AllocatedOn host(Node)

				Relationship relationshipToGet = null; 
				if ((relationshipToGet = tryCreateNodeRelationship(relationshipToPost,nodeId)) != null)
					allocatedRelationshipSet.add(relationshipToGet);	

			}catch (ServiceException e){
				throw new ServiceException();
			}
		}
		
		// add relation to the global map
		relationshipHostMap.put(nffgName , allocatedRelationshipSet);

	}
	
	// method used to post a generic relationShip of a Node
	private Relationship tryCreateNodeRelationship(Relationship relationship, String nodeId) throws ServiceException {
		
		//perform the request to the web target
		Response response = webTarget.path("/data/node/" + nodeId + "/relationships")
									 .request("application/xml")
									 .post(Entity.xml(relationship),Response.class);
				
		//check response code 
		int statusCode = response.getStatus();
		if (statusCode == 201 || statusCode == 200){ // NB different from slide, debugging issue
			return response.readEntity(Relationship.class);
		}
		else if ( statusCode == 400 ){
			System.out.println("tryCreateNodeRelationship: Bad Request"); 
			throw new ServiceException();
		}
		else if ( statusCode == 404 ){
			System.out.println("tryCreateNodeRelationship: Not Found"); 
			throw new ServiceException();
		}
		else if ( statusCode == 500 ){
			System.out.println("tryCreateNodeRelationship: Internal Server Error"); 
			throw new ServiceException();
		}
		return null;
		
	}
	
	// method used to retrieve the node
	private Node getNode(NodeReader destinationNode, String nffgName){

		Set<Node> nodeSet = nodeMap.get(nffgName);

		// for each node in the nffg
		for (Node node : nodeSet) {
			// found the node that match the name of destinationNode (i have
			// only one property)
			if (node.getProperties().getProperty().get(0).getValue().equals(destinationNode.getName()))
				return node;
		}
		

		return null;
	}
	
	// method used to retrieve the node's id
	private String getNodeId(NodeReader destinationNode, NffgReader nffgReader) {

		Set<Node> nodeSet = nodeMap.get(nffgReader.getName());

		// for each node in the nffg
		for (Node node : nodeSet){
			// found the node that match the name of destinationNode (i have only one property)
			if (node.getProperties().getProperty().get(0).getValue().equals(destinationNode.getName()))
				return node.getId();
		}

		return null;
	}
	
	private String getHostId(HostReader hostReader, NffgReader nffgReader) {
		
		Node hostNode = hostMap.get(hostReader.getName());

		// found the node that match the name of destinationNode (i have only one property)
		if (hostNode.getProperties().getProperty().get(0).getValue().equals(hostReader.getName()))
			return hostNode.getId();

		return null;
	}
	
	// check if the host was already posted
	private boolean verifyHostExistance(String hostName){
		
		if(hostMap.containsKey(hostName))
			return true;
		else
			return false;
	}


}

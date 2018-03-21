package it.polito.dp2.NFV.sol1;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.math.*;
import java.rmi.MarshalException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.FactoryConfigurationError;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
//import the generated classes from JAXB (Xml Schema)
import it.polito.dp2.NFV.sol1.jaxb.*;
import sun.misc.FloatingDecimal;




public class NfvInfoSerializer {
	
	public static final String XSD_NAME = "xsd/nfvInfo.xsd";
	public static final String XSD_LOCATION = "http://www.example.com/nfvInfo";
	public static final String PACKAGE = "it.polito.dp2.NFV.sol1.jaxb";
	
	private ObjectFactory objFactory;
	private NfvReader nfvReader;
	
	// set used to check the name's reference
	Map<String,Nffg> globalNffgs = new HashMap<String,Nffg>();
	Map<String,Host> globalHosts = new HashMap<String,Host>();
	Map<String,Node> globalNodes = new HashMap<String,Node>();
	Map<String,Vnft> globalVnfts = new HashMap<String,Vnft>();
	
	public static void main(String[] args) {
		// This class should receive the name of the output file.
		if(args.length != 1) {
			System.err.println("Error! Usage: <program_name> <output.xml>");
			System.err.println("args.length is equal to "+args.length);
			return;
		}
		System.out.println("This program will serialize your WorkflowMonitor into an XML file!");
		
		try {
			NfvInfoSerializer serializer = new NfvInfoSerializer();
			Virtualnetworkmanager root = serializer.createVirtualnetworkmanager();
			System.out.println("The data structures were created!\n");
			
			serializer.marshallDocument(root, System.out);
			
			PrintStream fpout = new PrintStream(new File(args[0]));
			serializer.marshallDocument(root, fpout);
			fpout.close();
		}
		catch (FactoryConfigurationError e) {
			System.err.println("Could not create a DocumentBuilderFactory: "+e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}		
		catch (NfvReaderException e) {
			System.err.println("Could not instantiate the manager class: "+e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
		catch (JAXBException e) {
			System.err.println("Error creating the new instance of the JAXBContent");
			e.printStackTrace();
			System.exit(2);
		}
		catch(IllegalArgumentException e) {
			System.err.println("Error, some argument are wrong!");
			e.printStackTrace();
			System.exit(3);
		}
		catch (FileNotFoundException e) {
			System.err.println("Error! The file: "+args[0]+" does not exists!");
			e.printStackTrace();
			System.exit(4);
		}
		catch (SAXException e) {
			System.err.println("Error creating the XML Schema object");
			e.printStackTrace();
			System.exit(5);
		} catch (DatatypeConfigurationException e) {
			System.err.println("Error data type configuration");
			e.printStackTrace();
			System.exit(6);
		}
		
	}
	
	// serializer constructor
	public NfvInfoSerializer() throws NfvReaderException, FactoryConfigurationError {	
		
		// creating the root element
		nfvReader = NfvReaderFactory.newInstance().newNfvReader();
		
		// creating the object factory
		objFactory = new ObjectFactory();
	}

	// this method perform the marshall operation
	private void marshallDocument(Virtualnetworkmanager root, PrintStream outputFile) throws JAXBException, SAXException {
		
		// Creating the JAXB context to perform a validation 
		JAXBContext jc;
		// Creating an instance of the XML Schema 
		Schema schema;
				
		try {
			jc = JAXBContext.newInstance(PACKAGE);
			schema = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI).newSchema(new File(XSD_NAME));
		}
		catch(IllegalArgumentException e) {
			System.err.println("Error! No implementation of the schema language is available");
			throw e;
		}
		catch(NullPointerException e) {
			System.err.println("Error! The instance of the schema or the file of the schema is not well created!\n");
			throw new SAXException("The schema file is null!");
		}
		
		// Creating the XML document 		
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, XSD_LOCATION+" "+XSD_NAME);
		m.setSchema(schema);
		m.marshal(root, outputFile);
		
	}

	// this method populate the structure used during the marshall operation
	private Virtualnetworkmanager createVirtualnetworkmanager() throws DatatypeConfigurationException {
		
		Virtualnetworkmanager root = objFactory.createVirtualnetworkmanager();
			
		// create catalog
		Catalog catalog = createCatalog();
		// create nffgs
		Set<Nffg> nffgs = createNffgs();
		// create in
		In in = createIn();
		
		// populate the root element
		root.setCatalog(catalog);
		for(Nffg nffg : nffgs){
			root.getNffg().add(nffg);
		}			
		root.setIn(in);
		
		return root;
		
	}
	
	private Catalog createCatalog(){
		
		Catalog catalog = objFactory.createCatalog();
		
		// get set of vnft
		Set<VNFTypeReader> setVnft = nfvReader.getVNFCatalog();
		
		for (VNFTypeReader vnfType_r: setVnft) {
			
			// create a vnft and set attributes
			Vnft vnft = objFactory.createVnft();
			vnft.setName(vnfType_r.getName());
			vnft.setType(vnfType_r.getFunctionalType().value());
			vnft.setMemory(BigInteger.valueOf(vnfType_r.getRequiredMemory()));
			vnft.setDisk(BigInteger.valueOf(vnfType_r.getRequiredStorage()));
						
			// insert a vnft into the catalog
			catalog.getVnft().add(vnft);
			// save vnft into a global hashmap
			globalVnfts.put(vnft.getName(), vnft);
						
		}
				
		return catalog;		
	}
	
	private Set<Nffg> createNffgs() throws DatatypeConfigurationException {
		
		Set<Nffg> nffgs = new HashSet<Nffg>();
				
		// get set of nffgs
		Set<NffgReader> setNffg = nfvReader.getNffgs(null);
		
		for (NffgReader nffg_r: setNffg) {
			
			// set nffg name and deployTime
			Nffg nffg = objFactory.createNffg();
			nffg.setName(nffg_r.getName());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(nffg_r.getDeployTime().getTimeInMillis());
			XMLGregorianCalendar xmlc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
			nffg.setDeployTime(xmlc);
			
			// save all node's name in order to match reference into link src and dst
			Map<String, Node> allocatedNodes = new HashMap<String, Node>();
			
			// get nodes of nffg
			Set<NodeReader> nodeSet = nffg_r.getNodes();
			
			for (NodeReader node_r: nodeSet) {
				
				// create node
				Node node = objFactory.createNode();
				node.setName(node_r.getName());
				node.setVnft(node_r.getFuncType().getName());
				
				// add node to nffg
				nffg.getNode().add(node);
				// save node name in two HashMap one local for nffg, one global
				allocatedNodes.put(node.getName(),node);
				globalNodes.put(node.getName(),node);
															
			} // close nodes
			
			// iterate again on nodes in order to populate links
			for (NodeReader node_r: nodeSet) {
				// get links
				Set<LinkReader> linkSet = node_r.getLinks();
				
				for (LinkReader link_r: linkSet){
					
					Link link = objFactory.createLink();
					link.setName(link_r.getName());
					link.setSrc(globalNodes.get(link_r.getSourceNode().getName()));
					link.setDst(globalNodes.get(link_r.getDestinationNode().getName()));
					link.setMaxL(BigInteger.valueOf(link_r.getLatency()));
					link.setThroughput(Double.parseDouble(new Float(link_r.getThroughput()).toString()));
										
					// add link to nffg
					nffg.getLink().add(link);
										
				} // close links
			}// close nodes
			

			// add nffg to the nffgs
			nffgs.add(nffg);
			globalNffgs.put(nffg.getName(), nffg);
					
		} //close nffgs
		
		
		return nffgs;
	}
	
	private In createIn() {
		
		In in = objFactory.createIn();
		
		// get host
		Set<HostReader> hostset = nfvReader.getHosts();
		
		for (HostReader host_r: hostset) {
			
			// create host
			Host host = objFactory.createHost();
			host.setName(host_r.getName());
			host.setMaxVNF(BigInteger.valueOf(host_r.getMaxVNFs()));
			host.setMemory(BigInteger.valueOf(host_r.getAvailableMemory()));
			host.setDisk(BigInteger.valueOf(host_r.getAvailableStorage()));
			
			// get allocated nodes
			Set<NodeReader> nodeSet = host_r.getNodes();
			
			for (NodeReader node_r: nodeSet){
				// add the node name to the list of nodes allocated
				host.getNodes().add(globalNodes.get(node_r.getName()));
			}
			

			// add host to in
			in.getHost().add(host);
			globalHosts.put(host.getName(), host);

			
		}
		
		// get hosts
		Set<HostReader> hostset2 = nfvReader.getHosts();
		
		
		for (HostReader sri: hostset2) {
			
			for(HostReader srj: hostset2){
				
				Connection connection = objFactory.createConnection();
				ConnectionPerformanceReader cpr = nfvReader.getConnectionPerformance(sri, srj);
				connection.setH1(sri.getName());
				connection.setH2(srj.getName());
				connection.setLatency(BigInteger.valueOf(cpr.getLatency()));
				connection.setThroughput(Double.parseDouble(new Float(cpr.getThroughput()).toString()));
							
				in.getConnection().add(connection);

				
			} //close srj
		
		} //close sri
		
		return in;
	}
	

}

package it.polito.dp2.NFV.sol1;

import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.ConnectionPerformanceReader;
import it.polito.dp2.NFV.HostReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.*;


public class ConcreteNfvReader implements NfvReader {

	public static final String XSD_NAME = "xsd/nfvInfo.xsd";
	public static final String PACKAGE = "it.polito.dp2.NFV.sol1.jaxb";
	public static final String FILE = "it.polito.dp2.NFV.sol1.NfvInfo.file";
	
	private HashMap<String, VNFTypeReader> vnfts = new HashMap<String, VNFTypeReader>();
	private HashMap<String, NffgReader> nffgs = new HashMap<String, NffgReader>();
	private HashMap<String, HostReader> hosts = new HashMap<String, HostReader>();
	private HashMap<String, ConnectionPerformanceReader> connections = new HashMap<String, ConnectionPerformanceReader>();
	
	
	public ConcreteNfvReader() throws  SAXException, JAXBException{
		
		String fileName = System.getProperty(FILE);
		Virtualnetworkmanager root = null;
		
		try {
			root = unmarshallDocument(new File(fileName));
		}
		catch (SAXException | JAXBException e) {
			System.err.println("Error during the unmarshalling of the XML document...\n"
					+e.getMessage());
			throw e;
		}
		catch (IllegalArgumentException e) {
			System.err.println("Error during the unmarshalling of the XML document...\n"
					+e.getMessage());
			throw new JAXBException(e.getMessage());
		}
		
		// - Vnfts - //
		System.out.println("In the document there are "+root.getCatalog().getVnft().size()+" Vnfts");
		
		for(Vnft vnft : root.getCatalog().getVnft()){
			VNFTypeReader vnftReader = new ConcreteVNFTypeReader(vnft);
			if(vnftReader != null)
				vnfts.put(vnftReader.getName(), vnftReader);
		}
		// - Nffgs - //
		System.out.println("In the document there are "+root.getNffg().size()+" Nffgs");
		
		for(Nffg nffg : root.getNffg()){
			NffgReader nffgReader = new ConcreteNffgReader(nffg, vnfts);
			if(nffgReader != null)
				nffgs.put(nffgReader.getName(), nffgReader);
		}
		
		// - Hosts - //
		System.out.println("In the document there are "+root.getIn().getHost().size()+" Hosts");
		
		for(Host host : root.getIn().getHost()){
			HostReader hostReader = new ConcreteHostReader(host, nffgs);
			if(hostReader != null)
				hosts.put(hostReader.getName(), hostReader);
		}
		
		// - Associate host to Node - //
		for (NffgReader nffgReader : nffgs.values()){			
			for(NodeReader nodeReader : nffgReader.getNodes()){
				if(nodeReader instanceof ConcreteNodeReader){
					((ConcreteNodeReader)nodeReader).setHostReader(hosts);
				}
			}
		}
		
		// - Connection Performance - //
		
		for(Connection connection : root.getIn().getConnection()){
			String key = connection.getH1() +"-"+ connection.getH2();
			ConnectionPerformanceReader connectionPerformanceReader = new ConcreteConnectionPerformanceReader(connection);
			
			if(connectionPerformanceReader != null)
				connections.put(key, connectionPerformanceReader);
		}
		
	}

	// unmarshall method
	private Virtualnetworkmanager unmarshallDocument(File inputFile) throws JAXBException, SAXException {
		
		JAXBContext jc = JAXBContext.newInstance(PACKAGE);
		/* - creating the XML schema to validate the XML file before read it - */
		Schema schema;
		try {
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
		
		Unmarshaller u = jc.createUnmarshaller();
		u.setSchema(schema);
		return (Virtualnetworkmanager) u.unmarshal(inputFile);

	}

	@Override
	public ConnectionPerformanceReader getConnectionPerformance(HostReader arg0, HostReader arg1) {
		// TODO Auto-generated method stub
		
		//create the key
		String key = arg0.getName()+"-"+arg1.getName();
		
		return connections.get(key);
	}

	@Override
	public HostReader getHost(String arg0) {
		// TODO Auto-generated method stub
		return hosts.get(arg0);
	}

	@Override
	public Set<HostReader> getHosts() {
		// TODO Auto-generated method stub
		Set<HostReader> set = new HashSet<HostReader>(hosts.values());
		return set;
	}

	@Override
	public NffgReader getNffg(String arg0) {
		// TODO Auto-generated method stub
		return nffgs.get(arg0);
	}

	@Override
	public Set<NffgReader> getNffgs(Calendar arg0) {
		// TODO Auto-generated method stub
		Set<NffgReader> set = new HashSet<NffgReader>(nffgs.values());
		return set;
	}

	@Override
	public Set<VNFTypeReader> getVNFCatalog() {
		// TODO Auto-generated method stub
		Set<VNFTypeReader> set = new HashSet<VNFTypeReader>(vnfts.values());
		return set;
	}

}

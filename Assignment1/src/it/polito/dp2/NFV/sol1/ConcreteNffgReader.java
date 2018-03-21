package it.polito.dp2.NFV.sol1;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

import it.polito.dp2.NFV.LinkReader;
import it.polito.dp2.NFV.NffgReader;
import it.polito.dp2.NFV.NodeReader;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.Link;
import it.polito.dp2.NFV.sol1.jaxb.Nffg;
import it.polito.dp2.NFV.sol1.jaxb.Node;
import it.polito.dp2.NFV.sol1.jaxb.Virtualnetworkmanager;

public class ConcreteNffgReader implements NffgReader {

	private String name;
	private Calendar deployTime;
	private Map<String,NodeReader> nodes = new HashMap<String,NodeReader>();
	// internal use
	private HashMap<String,LinkReader> links = new HashMap<String,LinkReader>();
	private HashMap<String,VNFTypeReader> vnfts;

	
	
	public ConcreteNffgReader(Nffg nffg, HashMap<String, VNFTypeReader> vnfts) {
		// TODO Auto-generated constructor stub
		if(nffg == null) return; //safety lock
		
		this.name = nffg.getName();
		//convert XMLGregorianCalendar to Calendar with TimeZone
		XMLGregorianCalendar xmlCalendar = nffg.getDeployTime();
		TimeZone timeZone = xmlCalendar.getTimeZone(xmlCalendar.getTimezone());        
	    Calendar calendar = Calendar.getInstance(timeZone);
	    calendar.set(Calendar.YEAR,xmlCalendar.getYear());
	    calendar.set(Calendar.MONTH,xmlCalendar.getMonth()-1);
	    calendar.set(Calendar.DATE,xmlCalendar.getDay());
	    calendar.set(Calendar.HOUR_OF_DAY,xmlCalendar.getHour());
	    calendar.set(Calendar.MINUTE,xmlCalendar.getMinute());
	    calendar.set(Calendar.SECOND,xmlCalendar.getSecond());
		this.deployTime = calendar;
		
		// save the catalog
		this.vnfts = new HashMap<String, VNFTypeReader>(vnfts);
		
		// - Nodes - //
		for(Node node : nffg.getNode()){
			VNFTypeReader vnfTypeReader = this.vnfts.get(node.getVnft());
			NodeReader nodeReader = new ConcreteNodeReader(node, this, vnfTypeReader);
			nodes.put(nodeReader.getName(), nodeReader);
		}
		
		// - Links - //
		for(Link link : nffg.getLink()){
			// take src and dst
			Node src = (Node) link.getSrc();
			Node dst = (Node) link.getDst();
			
			String srcName = src.getName();
			String dstName = dst.getName();
			
			System.out.println(link.getName() +" - " +srcName + " - "+ dstName);
			NodeReader srcNode = nodes.get(srcName);
			NodeReader dstNode = nodes.get(dstName);
			
			LinkReader linkReader = new ConcreteLinkReader(link, srcNode, dstNode);
			links.put(linkReader.getName(), linkReader);
			
			// add link to node (only src node)
			for(NodeReader nodeReader : nodes.values()){
				// if match with src
				if(nodeReader.getName().equals(srcName)){
					if(nodeReader instanceof ConcreteNodeReader){
						((ConcreteNodeReader)nodeReader).setLinkReader(linkReader);
					}
				}
			}
		} // close links
				
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public Calendar getDeployTime() {
		// TODO Auto-generated method stub
		return deployTime;
	}

	@Override
	public NodeReader getNode(String arg0) {
		// TODO Auto-generated method stub
		
		// match		
		if(nodes.containsKey(arg0))
			return nodes.get(arg0);
		else // no match
			return null;
	}

	@Override
	public Set<NodeReader> getNodes() {
		// TODO Auto-generated method stub
		Set<NodeReader> set = new HashSet<NodeReader>(nodes.values());
		return set;
		
	}

}

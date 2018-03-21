package it.polito.dp2.NFV.sol1;

import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import it.polito.dp2.NFV.NfvReader;
import it.polito.dp2.NFV.NfvReaderException;


public class NfvReaderFactory extends it.polito.dp2.NFV.NfvReaderFactory {

	@Override
	public NfvReader newNfvReader() throws NfvReaderException {
		

		NfvReader myReader = null;
		try {
			myReader = new ConcreteNfvReader();
		} catch (JAXBException | SAXException e) {
			System.err.println("Error: "+e.getMessage());
			e.printStackTrace();
			throw new NfvReaderException(e.getMessage());
		}
		
		return myReader;
	}
	
	//toString() implemented for debugging purposes
		@Override
	public String toString(){
		return "This is a custom NfvReaderFactory implementation for the assignment 1.";
	}

}

package it.polito.dp2.NFV.sol1;

import it.polito.dp2.NFV.FunctionalType;
import it.polito.dp2.NFV.VNFTypeReader;
import it.polito.dp2.NFV.sol1.jaxb.Vnft;


public class ConcreteVNFTypeReader implements VNFTypeReader{
	
	private String name;
	private FunctionalType type;
	private int memory;
	private int storage;

	public ConcreteVNFTypeReader(Vnft vnft) {
		// TODO Auto-generated constructor stub
		
		if(vnft == null) return; // safety lock
		
		this.name = vnft.getName();
		this.type = FunctionalType.fromValue(vnft.getType());
		this.memory = vnft.getMemory().intValue();
		this.storage = vnft.getDisk().intValue();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public FunctionalType getFunctionalType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public int getRequiredMemory() {
		// TODO Auto-generated method stub
		return memory;
	}

	@Override
	public int getRequiredStorage() {
		// TODO Auto-generated method stub
		return storage;
	}

}

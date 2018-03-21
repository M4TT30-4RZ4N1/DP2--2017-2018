package it.polito.dp2.NFV.sol3.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNode;
import it.polito.dp2.NFV.sol3.service.nfvdeployer.RNodes;

@Api(hidden = true, tags = {NfvDeployer.nodesUri})
@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
public class ResourceNodes {

	private Map<String, ResourceNode> subNodes = new HashMap<>();
	private NfvDeployer nfvDeployer = null;
	private String nffgName;
	
	
	public ResourceNodes(String resourceName) {
		
		nffgName = resourceName;
		nfvDeployer = NfvDeployer.getInstance();
		
	}
	
	@GET
	@ApiOperation(value = "Get nodes of the nffg")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RNodes.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RNodes getNodes() {
		
		try {
			return nfvDeployer.getAllNodesOfNffg(nffgName);
		} 
		catch (UnknownEntityException e) {
			throw new NotFoundException(e);
		}
	}
	
	@POST
	@ApiOperation(value = "Create a new node inside the nffg")
    @ApiResponses(value = {
    		@ApiResponse(code = 200, message = "OK", response = RNode.class),
    		@ApiResponse(code = 404, message = "Not Found"),
    		@ApiResponse(code = 409, message = "Conflict"),
    		@ApiResponse(code = 422, message = "Unprocessable Entity"),
    		@ApiResponse(code = 500, message = "Internal Server Error")})
	public RNode postNode(RNode node) {
		
		if (node == null){
			throw new BadRequestException("RNode not provided");
		}
		if (!nfvDeployer.checkDeployment(nffgName)){
			throw new My409Exception("The nffg selected is not deployed");
		}
		if (node.getRVnft() == null) {
			throw new My422Exception("VNF type not valid");
		}
		if (node.getName() == null){
			throw new My422Exception("Node name not defined");
		}
		
		String host_tmp = null;
		if(node.getRHost() != null) {
			host_tmp = node.getRHost().getName();
		}
		
		try {
			// try the creation of a RNode
			RNode rNode = nfvDeployer.newNode(nffgName, node.getName(), node.getRVnft().getName(), false);

		} 
		catch (MyAlreadyLoadedException e) {
			
			throw new My409Exception(e);
		} 
		catch (UnknownEntityException e) {
			
			throw new My422Exception(e);
		}
		catch (ServiceException e) {
			
			// in case of error perform rollback on node
			try {
				nfvDeployer.rollbackOnNode(nffgName, node.getName());
			}
			catch (UnknownEntityException ex) {
				
				throw new InternalServerErrorException(ex);
			} 
			catch (ServiceException ex) {
				
				throw new InternalServerErrorException(ex);
			}
			
			throw new InternalServerErrorException(e);
		} 
		catch (MyNotValidExeption e) {
			
			throw new My422Exception(e);
		}
		
		
		// if all fine and no rollback performed, we can try to allocate the node
		try {
			// try node allocation
			nfvDeployer.allocateNode(node.getName(), host_tmp);

		} 
		catch (AllocationException e) {
			
			// error ? --> perform a rollback
			try {
				nfvDeployer.rollbackOnNode(nffgName, node.getName());
				
			} catch (UnknownEntityException ex) {
				
				throw new InternalServerErrorException(ex);
				
			} catch (ServiceException ex) {
				
				throw new InternalServerErrorException(ex);
			}
			throw new My409Exception(e);
			
			
			
		} 
		catch (UnknownEntityException e) {
			
			// error ? --> perform rollback on node
			
			try {
				
				nfvDeployer.rollbackOnNode(nffgName, node.getName());
			} 
			catch (UnknownEntityException ex) {
				
				throw new InternalServerErrorException(ex);
			} 
			catch (ServiceException ex) {
				
				throw new InternalServerErrorException(ex);
			}
			
			throw new My422Exception(e);
		}
		
		// if all is fine we can try get the node in order to fill it in the xml response body of http 
		try {
			return nfvDeployer.getNode(nffgName, node.getName());
			
		} 
		catch (UnknownEntityException e) {
			
			throw new InternalServerErrorException(e);
		}

	}
	
	@Path("{nodeName}")
	public ResourceNode getNode(@PathParam("nodeName") String nodeName) {
		return subNodes.getOrDefault(nodeName, new ResourceNode(nffgName, nodeName));
	}

}

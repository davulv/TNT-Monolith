package com.autentia.tnt.util;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RestUtil<T> {
	
	private String baseURI;
	private Client client;
	private Class<T> responseEntityClass;
	
	public RestUtil(String baseURI, Class<T> responseEntityClass){
		this.baseURI = baseURI;
		this.responseEntityClass = responseEntityClass;
		client = Client.create();
	}
	
	public T get(String path){
		ClientResponse response = client
				   .resource(baseURI +path).accept(MediaType.APPLICATION_JSON)
                   .type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
	
	public T post(String path, T requestEntity){
		ClientResponse response = client
				   .resource(baseURI +path).accept(MediaType.APPLICATION_JSON)
                   .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, requestEntity);
		
		
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
	
	public void delete(String path){
		WebResource webResource = client
				   .resource(baseURI +path);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);
		
		
		
	}
	public T put(String path, T requestEntity){
		WebResource webResource = client
				   .resource(baseURI +path);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).put(ClientResponse.class, requestEntity);
		/*if (response.getStatus() > 201) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}*/
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
	
	public List<T> postToGetList(String path, Object searchRequest){
		ClientResponse response = client
				   .resource(baseURI +path).accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).post(ClientResponse.class, searchRequest);
		
		
		
		List<T> responseEntity = response.getEntity(List.class);	
		return responseEntity; 
	}
	
	public ClientResponse getList(String path){
		
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client restClient = Client.create(cfg);
		ClientResponse response = restClient
				   .resource(baseURI +path).accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON).get(ClientResponse.class);		
		
			
		return response;
	}
}

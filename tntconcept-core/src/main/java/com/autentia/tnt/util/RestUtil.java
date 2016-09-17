package com.autentia.tnt.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
		WebResource webResource = client
				   .resource(baseURI +path);
		ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
		if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
	
	public T post(String path, T requestEntity){
		WebResource webResource = client
				   .resource(baseURI +path);
		ClientResponse response = webResource.accept("application/json")
                .post(ClientResponse.class, requestEntity);
		/*if (response.getStatus() != 201) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}*/
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
	
	public T delete(String path){
		WebResource webResource = client
				   .resource(baseURI +path);
		ClientResponse response = webResource.accept("application/json")
                .delete(ClientResponse.class);
		/*if (response.getStatus() > 201) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}*/
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
	public T put(String path, T requestEntity){
		WebResource webResource = client
				   .resource(baseURI +path);
		ClientResponse response = webResource.accept("application/json")
                .put(ClientResponse.class, requestEntity);
		/*if (response.getStatus() > 201) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}*/
		
		T responseEntity = response.getEntity(responseEntityClass);
		return responseEntity;
	}
}

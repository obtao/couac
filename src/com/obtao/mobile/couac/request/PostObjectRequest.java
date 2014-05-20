package com.obtao.mobile.couac.request;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.Gson;
import com.obtao.mobile.couac.GenericResponse;
import com.obtao.mobile.couac.Resource;

public class PostObjectRequest<E extends Resource> extends Request<GenericResponse> {

	private Listener<GenericResponse> listener;
	private E resource;
	
	public PostObjectRequest(E resource, String url, Listener<GenericResponse> listener,
			ErrorListener errorListener) {
		super(Method.POST, url, errorListener);
		this.resource = resource;
	}
	
	@Override
	public byte[] getBody() throws AuthFailureError {
		return resource.getJsonBody();
	}

	@Override
	protected void deliverResponse(GenericResponse response) {
		listener.onResponse(response);
	}

	@Override
	protected Response<GenericResponse> parseNetworkResponse(NetworkResponse response) {
		String json = new String(response.data);
		Gson gson = new Gson();
		GenericResponse postResponse = gson.fromJson(json, GenericResponse.class);
		return Response.success(postResponse, getCacheEntry());
	}
}

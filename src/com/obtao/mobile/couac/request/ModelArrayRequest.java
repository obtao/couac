package com.obtao.mobile.couac.request;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.google.gson.Gson;
import com.obtao.mobile.couac.Resource;

public class ModelArrayRequest<T extends Resource> extends Request<T[]> {

	private Listener<T[]> listener;
	private Gson gson;
	private Class<T[]> clazz;

	public ModelArrayRequest(int method, String url, Listener<T[]> listener, ErrorListener errorListener, Class<T[]> clazz) {
		super(method, url, errorListener);
		this.listener = listener;
		this.gson = new Gson();
		this.clazz = clazz;
	}

	@Override
	protected Response<T[]> parseNetworkResponse(NetworkResponse response) {
		String json = new String(response.data);
		T[] models = gson.fromJson(json, clazz);
		return Response.success(models, getCacheEntry());
	}

	@Override
	protected void deliverResponse(T[] models) {
		listener.onResponse(models);		
	}
}

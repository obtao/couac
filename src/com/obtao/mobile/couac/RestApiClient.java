package com.obtao.mobile.couac;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.obtao.mobile.couac.exception.ClientNotInitializedException;


public class RestApiClient {
	
	private static final String LOG = "RestApiClient";

	private static RestApiClient instance = null;
	private String baseUrl;
	private RequestQueue queue;

	
	public RequestQueue getQueue() {
		return queue;
	}

	private RestApiClient(Context context, String baseUrl) {
		this.baseUrl = baseUrl;
		Log.d(LOG, "Initializing new Volley queue...");
		queue = Volley.newRequestQueue(context);
		queue.start();
	}
	
	public static RestApiClient getInstance() throws ClientNotInitializedException {
		if(instance == null) {
			throw new ClientNotInitializedException("You forgot to call RestApiClient.init()");
		}
		return instance;
	}
	
	public static void init(Context context, String baseUrl) {
		if(instance == null) {
			instance = new RestApiClient(context, baseUrl);
		}
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
}

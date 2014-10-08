package com.obtao.mobile.couac;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.obtao.mobile.couac.exception.ClientNotInitializedException;


public class RestApiClient {
	
	private static final String LOG = "RestApiClient";

	private static RestApiClient instance = null;
	private RequestQueue queue;

	
	public RequestQueue getQueue() {
		return queue;
	}

	private RestApiClient(Context context) {
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
	
	public static void init(Context context) {
		if(instance == null) {
			instance = new RestApiClient(context);
		}
	}
}

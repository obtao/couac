package com.obtao.mobile.couac;

import android.util.Log;

import com.android.volley.Cache.Entry;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.obtao.mobile.couac.exception.ClientNotInitializedException;
import com.obtao.mobile.couac.request.GetArrayRequest;
import com.obtao.mobile.couac.request.GetObjectRequest;
import com.obtao.mobile.couac.request.PostObjectRequest;

public abstract class ResourceHelper<T extends Resource> {

	private static final String LOG = "ResourceHelper";
	private RestApiClient client;

	protected ResourceHelper() {
		try {
			this.client = RestApiClient.getInstance();
		} catch (ClientNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void doGetArrayRequest(String url, Listener<T[]> listener, ErrorListener errorListener, Class<T[]> clazz) {
		Entry entry = client.getQueue().getCache().get(url);
		if(entry != null) {
			Log.i(LOG, "Notifications : get data in cache");
			String cachedResponse = new String(entry.data);
			//TODO gerer le cache...
		} else {

			GetArrayRequest<T> request = new GetArrayRequest<T>(
					url, 
					listener, 
					errorListener,
					clazz);
			request.setTag(this.getTag());
			client.getQueue().add(request);
		}

	}

	protected void doGetObjectRequest(String url, Listener<T> listener, ErrorListener errorListener, Class<T> clazz) {
		Entry entry = client.getQueue().getCache().get(url);
		if(entry != null) {
			Log.i(LOG, "Notifications : get data in cache");
			String cachedResponse = new String(entry.data);
			//TODO gerer le cache...
		} else {
			GetObjectRequest<T> request = new GetObjectRequest<T>(
					url, 
					listener, 
					errorListener,
					clazz);
			request.setTag(this.getTag());
			client.getQueue().add(request);
		}

	}

	protected void doPostObjectRequest(String url,T resource, Listener<GenericResponse> listener, ErrorListener errorListener) {
		PostObjectRequest<T> request = new PostObjectRequest<T>(resource, url, listener, errorListener);
		request.setTag(this.getTag());
		client.getQueue().add(request);
	}

	public void cancelRequests() {
		client.getQueue().cancelAll(this.getTag());

	}

	/**
	 * <p>This tag is used by volley to tag requests this helper
	 * will add in queue.</p>
	 * <p>Very usefull when you need to cancel a group of tagged requests
	 * (when your activity is paused, it's a good idea to cancel pending requests)</p>
	 * 
	 * @return the tag
	 */
	public abstract String getTag();
}

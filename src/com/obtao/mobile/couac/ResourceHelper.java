package com.obtao.mobile.couac;

import android.util.Log;

import com.android.volley.Cache.Entry;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.obtao.mobile.couac.exception.ClientNotInitializedException;
import com.obtao.mobile.couac.request.ModelArrayRequest;
import com.obtao.mobile.couac.request.ModelObjectRequest;

public abstract class ResourceHelper<T extends Resource> {

	private static final String LOG = "ResourceHelper";

	public void processArrayRequest(int method, String url, Listener<T[]> listener, ErrorListener errorListener, Class<T[]> clazz) {
		try {
			Entry entry = RestApiClient.getInstance().getQueue().getCache().get(url);
			if(entry != null) {
				Log.i(LOG, "Notifications : get data in cache");
				String cachedResponse = new String(entry.data);
				//TODO gerer le cache...
			} else {
				
				ModelArrayRequest<T> request = new ModelArrayRequest<T>(
						method, 
						url, 
						listener, 
						errorListener,
						clazz);
				request.setTag(this.getTag());
				RestApiClient.getInstance().getQueue().add(request);
			}
		} catch(ClientNotInitializedException e) {
			Log.e(LOG, "You must initialize the RestApiClient");
			e.printStackTrace();
		}
	}
	
	protected void processObjectRequest(int method, String url, Listener<T> listener, ErrorListener errorListener, Class<T> clazz) {
		try {
			Entry entry = RestApiClient.getInstance().getQueue().getCache().get(url);
			if(entry != null) {
				Log.i(LOG, "Notifications : get data in cache");
				String cachedResponse = new String(entry.data);
				//TODO gerer le cache...
			} else {
				
				ModelObjectRequest<T> request = new ModelObjectRequest<T>(
						method, 
						url, 
						listener, 
						errorListener,
						clazz);
				request.setTag(this.getTag());
				RestApiClient.getInstance().getQueue().add(request);
			}
		} catch(ClientNotInitializedException e) {
			Log.e(LOG, "You must initialize the RestApiClient");
			e.printStackTrace();
		}
	}
	
	public void cancelRequests() {
		try {
			RestApiClient.getInstance().getQueue().cancelAll(this.getTag());
		} catch (ClientNotInitializedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

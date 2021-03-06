package com.obtao.mobile.couac;

import com.obtao.mobile.couac.exception.ClientNotInitializedException;


/**
 * A simple class to build String URL with parameters
 * @author jb
 *
 */
public class URLBuilder {
	
	private StringBuffer fullpath;
	private StringBuffer parameters;
	private StringBuffer url;
	private boolean firstParam = true;

	public URLBuilder() {
		parameters = new StringBuffer();
		fullpath = new StringBuffer();
	}
	
	/**
	 * Add path to the end of current building url
	 * @param path to add
	 * @return {@link URLBuilder}
	 */
	public URLBuilder addPath(String path) {
		fullpath.append(path);
		return this;
	}
	
	/**
	 * Add parameter in url
	 * @param key
	 * @param value
	 * @return {@link URLBuilder}
	 */
	public URLBuilder addParameter(String key, String value) {
		if(firstParam) {
			parameters.append("?");
			firstParam = false;
		} else {
			parameters.append("&");
		}
		parameters.append(key);
		parameters.append("=");
		parameters.append(value);
		return this;
	}
	
	/**
	 * Build the string url
	 * @return final url as string
	 */
	public String build() {
		url = new StringBuffer();
		url.append(fullpath);
		url.append(parameters);
		clear();
		return new String(url);
	}
	
	private void clear() {
		parameters = new StringBuffer();
		fullpath = new StringBuffer();
		firstParam = true;
	}	
}

package com.obtao.mobile.couac;

import java.nio.charset.Charset;

import com.google.gson.Gson;

public abstract class Resource {
		
	public byte[] getJsonBody() {
		Gson gson = new Gson();
		//getBytes() require API 9 minimum
		return gson.toJson(this).getBytes(Charset.forName("UTF-8"));
	}

}

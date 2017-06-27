package com.example.jyxmyt.http_url;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;

public class WebServiceUtilThreadInputStream extends Thread {
	private Handler handler;
	private String method;
	private List<NameValuePair> list;
	private int what = -1; 

	public InputStream getAllProvince(String method, List<NameValuePair> list){
		try{
			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 30000);
			HttpConnectionParams.setSoTimeout(httpParams, 30000);
			HttpPost httpPost = new HttpPost(method);
			httpPost.setEntity(new UrlEncodedFormEntity(list, HTTP.UTF_8));
			System.out.println(httpPost.getEntity() + "getEntity");
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=utf-8");
			DefaultHttpClient client = new DefaultHttpClient(httpParams);
			client.setHttpRequestRetryHandler(requestRetryHandler);
			HttpResponse httpResponse = client.execute(httpPost);
			System.out.println(httpResponse.getStatusLine().getStatusCode()
					+ "code");
			if(httpResponse.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK){
				return httpResponse.getEntity().getContent();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return null;
	}

	/**
	 *  * 设置重连机制和异常自动恢复处理 
	 * */
	private static HttpRequestRetryHandler requestRetryHandler = new HttpRequestRetryHandler() {
		// 自定义的恢复策略 
		public boolean retryRequest(IOException exception, int executionCount,
				HttpContext context){

			// 设置恢复策略，在Http请求发生异常时候将自动重试3次 
			if(executionCount >= 3){
				// Do not retry if over max retry count 
				return false;
			}
			if(exception instanceof NoHttpResponseException){
				// Retry if the server dropped connection on us 
				return true;
			}
			if(exception instanceof SSLHandshakeException){
				// Do not retry on SSL handshake exception 
				return false;
			}
			HttpRequest request = (HttpRequest) context
					.getAttribute(ExecutionContext.HTTP_REQUEST);
			boolean idempotent = (request instanceof HttpEntityEnclosingRequest);
			return !idempotent;
		}
	};

	// 使用ResponseHandler接口处理响应，HttpClient使用ResponseHandler会自动管理连接的释放， 
	// 解决对连接的释放管理 
	private static ResponseHandler<String> strResponseHandler = new ResponseHandler<String>() {
		// 自定义响应处理 
		public String handleResponse(HttpResponse response)
				throws IOException{
			HttpEntity entity = response.getEntity();
			if(entity != null){
				String charset = EntityUtils.getContentCharSet(entity) == null ? "UTF_8"
						: EntityUtils.getContentCharSet(entity);
				return new String(EntityUtils.toByteArray(entity), charset);
			}
			else{
				return null;
			}
		}
	};

	public void run(){
		InputStream inputStream = getAllProvince(method, list);
		if(handler == null){
			return;
		}
		Message message = handler.obtainMessage();
		message.obj = inputStream;
		message.what = this.what;
		handler.sendMessage(message);
	}

	public WebServiceUtilThreadInputStream(Handler handler, String method,
			List<NameValuePair> list, int what){
		super();
		this.handler = handler;
		this.method = method;
		this.list = list;
		this.what = what;
	}

	public WebServiceUtilThreadInputStream(Handler handler, String method,
			List<NameValuePair> list, int what, int id){
		super();
		this.handler = handler;
		this.method = method;
		this.list = list;
		this.what = what;
	}

}

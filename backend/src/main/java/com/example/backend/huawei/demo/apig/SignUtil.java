//package name of the demo.
package com.example.backend.huawei.demo.apig;

//Import the external dependencies.

import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import com.example.backend.huawei.util.HttpUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class SignUtil {

    public static void main(String[] args) {
        //Create a new request.
        Request request = new Request();
        try {
            //Set the AK/SK to sign and authenticate the request.
            request.setKey("QTWAOYTTINDUT2QVKYUC");
            request.setSecret("MFyfvK41ba2giqM7**********KGpownRZlmVmHc");

            //The following example shows how to set the request URL and parameters to query a VPC list.

            //Specify a request method, such as GET, PUT, POST, DELETE, HEAD, and PATCH.
            request.setMethod("GET");

            //Set a request URL in the format of https://{Endpoint}/{URI}.
            request.setUrl("https://endpoint.example.com/v1/77b6a44cba5143ab91d13ab9a8ff44fd/vpcs?limit=2");

            //Add header parameters, for example, x-domain-id for invoking a global service and x-project-id for invoking a project-level service.
            request.addHeader("Content-Type", "application/json");

            //Add a body if you have specified the PUT or POST method. Special characters, such as the double quotation mark ("), contained in the body must be escaped.
            //request.setBody("demo");

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        CloseableHttpClient client = null;
        try {
            //Sign the request.
            HttpRequestBase signedRequest = Client.sign(request);

            //Send the request.
            client = HttpClients.custom().build();
            HttpResponse response = client.execute(signedRequest);

            //Print the status line of the response.
            System.out.println(response.getStatusLine().toString());

            //Print the header fields of the response.
            Header[] resHeaders = response.getAllHeaders();
            for (Header h : resHeaders) {
                System.out.println(h.getName() + ":" + h.getValue());
            }

            //Print the body of the response.
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                System.out.println(System.getProperty("line.separator") + EntityUtils.toString(resEntity, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static HttpRequestBase signRequest(String url, String method, Map<String, String> header, String body, Map<String, String> params) {
        try {
            Request request = new Request();
            request.setKey("LDGR8REAUYQKJR0ZAN6T");
            request.setSecret("fXf6ApZgR7******************Ag5aG64sH7FI");
            if (body != null && body.length() > 0) {
                request.setBody(body);
            }
            if (params != null && !params.isEmpty()) {
                HttpUtils httpUtils = new HttpUtils();
                url = httpUtils.constructUri(url, params);
            }
            request.setUrl(url);
            for (String key : header.keySet()) {
                request.addHeader(key, header.get(key));
            }
            request.setMethod(method);
            return Client.sign(request);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
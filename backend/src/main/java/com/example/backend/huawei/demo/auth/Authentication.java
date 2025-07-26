package com.example.backend.huawei.demo.auth;

import com.example.backend.huawei.pojo.auth.*;
import com.example.backend.huawei.util.Constants;
import com.example.backend.huawei.util.HttpUtils;
import com.example.backend.huawei.util.JsonUtils;
import com.example.backend.huawei.util.StreamClosedHttpResponse;
import org.apache.http.Header;

import java.io.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Authentication {

    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException {
        System.out.println(getToken());
    }

    public static String getToken() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        File file = new File("token.text");
        String token = "";
        String str = "";
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(new FileInputStream(file));
                bufferedReader = new BufferedReader(inputStreamReader);
                while ((str = bufferedReader.readLine()) != null) {
                    token = token + str;
                }
            } catch (IOException e) {
                System.out.println("token exist.");
            } finally {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("close stream faild");
                }
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    System.out.println("close stream faild");
                }
            }
        }

        if (token.trim().length() > 0) {
            return token;
        }

        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        AuthDTO authDTO = new AuthDTO();
        DomainDTO domainDTO = new DomainDTO();
        IdentityDTO identityDTO = new IdentityDTO();
        PasswordDTO passwordDTO = new PasswordDTO();
        UserDTO userDTO = new UserDTO();
        ProjectDTO projectDTO = new ProjectDTO();
        ScopeDTO scopeDTO = new ScopeDTO();
        projectDTO.setName("cn-north-4");
        scopeDTO.setProject(projectDTO);

        domainDTO.setName("lm012121");
        userDTO.setName("yishan");
        userDTO.setPassword("Zjhw@12345");

        userDTO.setDomain(domainDTO);
        passwordDTO.setUser(userDTO);
        List<String> method = new ArrayList<String>();
        method.add("password");
        identityDTO.setMethods(method);
        identityDTO.setPassword(passwordDTO);

        authDTO.setIdentity(identityDTO);
        authDTO.setScope(scopeDTO);

        accessTokenDTO.setAuth(authDTO);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.initClient();

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        StreamClosedHttpResponse httpResponse = null;
        try {
            httpResponse = httpUtils.doPost(Constants.TOKEN_ACCESS_URL, header, JsonUtils.Obj2String(accessTokenDTO));
        } catch (Exception exception) {
            System.err.println("please check your network.");
            return null;
        }

        Header[] headers = httpResponse.getHeaders("X-Subject-Token");
        token = headers[0].getValue();

        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(token.getBytes());
        fileOutputStream.close();
        return token;
    }
}

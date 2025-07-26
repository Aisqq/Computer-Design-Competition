package com.example.backend.huawei.demo.device;

import com.example.backend.huawei.demo.auth.Authentication;
import com.example.backend.huawei.util.Constants;
import com.example.backend.huawei.util.HttpUtils;
import com.example.backend.huawei.util.JDBCUtil;
import com.example.backend.huawei.util.StreamClosedHttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class QueryDeviceList {
    //当程序开启后，每分钟拿取一次数据不间断。
    public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException, IOException, URISyntaxException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() ->{
            JDBCUtil jdbc=new JDBCUtil();
            String token = null;
            try {
                token = Authentication.getToken();
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String project_id = "840b5077cd664026a40a9b12704c426d";
        String device_id = "6672a2ca7dbfd46fabc1076e_nbiot";
//        String url = Constants.DEVICE_COMMAND_URL;        //查询设备
        String url =Constants.DEVICE_SHADOW_URL;            //查询设备影子
        url = String.format(url, project_id, device_id);

        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/json");
        header.put("X-Auth-Token", token);

        Map<String, String> params = new HashMap<String, String>();
        //params.put("gateway_id", "5e09f371334dd4f337056da0_gaoshang_001");
        params.put("node_id", "gaoshang_001");

        HttpUtils httpUtils = new HttpUtils();
            try {
                httpUtils.initClient();
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            StreamClosedHttpResponse httpResponse = null;
            try {
                httpResponse = httpUtils.doGet(url, header, params);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
            System.out.println(httpResponse.getStatusLine());
        System.out.println(httpResponse.getContent());

        //查询设备是否在线信息
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode jsonNode = objectMapper.readValue(httpResponse.getContent(),JsonNode.class);
//        JsonNode statusNode = jsonNode.get("status");
//        String statusstr = statusNode.asText();
//        System.out.println("status = "+statusstr);
//        if(statusstr.equals("OFFLINE")) System.out.println("设备离线");
//        else if (statusstr.equals("ONLINE")) System.out.println("设备在线");

        //存入人的坐标
        ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readValue(httpResponse.getContent(), JsonNode.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            JsonNode ManLocationNodex = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Man_x");
        JsonNode ManLocationNodey = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Man_y");
        JsonNode ManLocationNodez = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Man_z");
        String ManLocationNodexstr = ManLocationNodex.asText();
        String ManLocationNodeystr = ManLocationNodey.asText();
        String ManLocationNodezstr = ManLocationNodez.asText();
        System.out.println("Location_Man = "+ "("+ManLocationNodex + "," + ManLocationNodey + "," + ManLocationNodez + ")");
        System.out.println("人的坐标为"+ "("+ManLocationNodex + "," + ManLocationNodey + "," + ManLocationNodez + ")");
        try {
            String sql="UPDATE employee SET geom_x = ?, geom_y = ?, geom_z = ? WHERE employeeid ='1101'";
            jdbc.executeSql(sql,new Object[] {ManLocationNodexstr, ManLocationNodeystr, ManLocationNodezstr});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //存入物料坐标
        JsonNode MaterLocationNodex = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Material_x");
        JsonNode MaterLocationNodey = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Material_y");
        JsonNode MaterLocationNodez = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Material_z");
        String MaterLocationNodexstr = ManLocationNodex.asText();
        String MaterLocationNodeystr = ManLocationNodey.asText();
        String MaterLocationNodezstr = ManLocationNodez.asText();
        System.out.println("Location_Material = "+ "("+MaterLocationNodex + "," + MaterLocationNodey + "," + MaterLocationNodez + ")");
        System.out.println("物料的坐标为"+ "("+MaterLocationNodex + "," + MaterLocationNodey + "," + MaterLocationNodez + ")");
        try {
            String sql="UPDATE material SET geom_x = ?, geom_y = ?, geom_z = ? WHERE materialid =27";
            jdbc.executeSql(sql,new Object[] {MaterLocationNodexstr, MaterLocationNodeystr, MaterLocationNodezstr});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //存入小车坐标
        JsonNode CarLocationNodex = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Car_x");
        JsonNode CarLocationNodey = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Car_y");
        JsonNode CarLocationNodez = jsonNode.get("shadow").get(0).get("reported").get("properties").get("Locations_Car_z");
        String CarLocationNodexstr = ManLocationNodex.asText();
        String CarLocationNodeystr = ManLocationNodey.asText();
        String CarLocationNodezstr = ManLocationNodez.asText();
        System.out.println("Location_Car = "+ "("+CarLocationNodex + "," + CarLocationNodey + "," + CarLocationNodez + ")");
        System.out.println("小车的坐标为"+ "("+CarLocationNodex + "," + CarLocationNodey + "," + CarLocationNodez + ")");
        try {
            String sql="UPDATE car SET geom_x = ?, geom_y = ?, geom_z = ? WHERE carid =2101";
            jdbc.executeSql(sql,new Object[] {CarLocationNodexstr, CarLocationNodeystr, CarLocationNodezstr});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        }, 0, 1, TimeUnit.MINUTES);
    }
}


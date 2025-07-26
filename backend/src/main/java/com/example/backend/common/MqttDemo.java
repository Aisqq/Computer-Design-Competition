package com.example.backend.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

public class MqttDemo {
    //IoT平台mqtt接入地址，替换成"连接配置说明中"的"MQTT接入域名"
    private String serverAddress = "698f2c6548.st1.iotda-app.cn-north-4.myhuaweicloud.com";
    private int qosLevel = 0;
    private MqttAsyncClient client;
    //接入凭证，替换成"获取MQTT接入凭证"中获取的接入凭证
    private static String accessKey = "h9PWfyPJ";
    private static String accessCode = "qDo7XnQBlFF8JXiw0GSMmBT5DDmRNDyu";
    //接收数据的Topic，替换成"创建规则动作"中的Topic
    private static String subscribeTopic = "yishan";
    private static String shareSubscribeTopic = "yishan";
    private long minBackoff = 1000;
    private long maxBackoff = 30 * 1000; //30 seconds
    private long defaultBackoff = 1000;
    private static int retryTimes = 0;
    private SecureRandom random = new SecureRandom();
    JDBCUtil jdbc=new JDBCUtil();

    public static void main(String[] args) throws MqttException {
        MqttDemo mqttDemo = new MqttDemo();
        mqttDemo.connect();
    }


    private void connect() {
        String url = "ssl://" + serverAddress + ":" + 8883;
        String userName = "accessKey=" + accessKey + "|timestamp=" + System.currentTimeMillis();
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setKeepAliveInterval(120);//心跳时间限定为30至1200秒
            options.setConnectionTimeout(5000);
            options.setAutomaticReconnect(true);
            options.setUserName(userName);
            options.setPassword(accessCode.toCharArray());
            options.setSocketFactory(getOptionSocketFactory(MqttDemo.class.getClassLoader().getResource("DigiCertGlobalRootCA.jks").getPath()));
            options.setHttpsHostnameVerificationEnabled(false);

            //设置MqttClient
            client = new MqttAsyncClient(url, userName, new MemoryPersistence());
            client.setCallback(callback);
            //建立连接
            client.connect(options, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    retryTimes = 0;
                    System.out.println("Mqtt connect success.");
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    System.out.println("Mqtt connect fail:" + throwable);

                    //退避重连
                    int lowBound = (int) (defaultBackoff * 0.8);
                    int highBound = (int) (defaultBackoff * 1.2);
                    long randomBackOff = random.nextInt(highBound - lowBound);
                    long backOffWithJitter = (int) (Math.pow(2.0, (double) retryTimes)) * (randomBackOff + lowBound);
                    long waitTImeUntilNextRetry = (int) (minBackoff + backOffWithJitter) > maxBackoff ? maxBackoff : (minBackoff + backOffWithJitter);
                    System.out.println("----  " + waitTImeUntilNextRetry);
                    try {
                        Thread.sleep(waitTImeUntilNextRetry);
                    } catch (InterruptedException e) {
                        System.out.println("sleep failed, the reason is" + e.getMessage().toString());
                    }
                    retryTimes++;
                    MqttDemo.this.connect();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * Mqtt回调
     */
    private MqttCallback callback = new MqttCallbackExtended() {
        @Override
        public void connectComplete(boolean reconnect, String serviceURI) {
            System.out.println("Mqtt client connected, address:" + serviceURI);
            subScribeTopic();
        }

        @Override
        public void connectionLost(Throwable throwable) {
            System.out.println("Connection lost.");
            //可在此处实现重连
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            System.out.println("Receive mqtt topic:" + topic + ", message:" + message);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = null;
            try {
                jsonNode = objectMapper.readTree(new String(message.getPayload()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to parse JSON", e);
            }
            //获取数据
            JsonNode ManLocation=jsonNode.get("notify_data").get("body").get("services").get(0).get("properties").get("Locations_Man");
            JsonNode MaterLocation=jsonNode.get("notify_data").get("body").get("services").get(0).get("properties").get("Locations_Material");
            JsonNode CarLocation=jsonNode.get("notify_data").get("body").get("services").get(0).get("properties").get("Locations_Car");
            //转换数据并且存入数据库

            //人的坐标
            String ManLocationstr=ManLocation.asText();
            String[] dataParts1 = ManLocationstr.substring(1, ManLocationstr.length() - 1).split(",");
            if(dataParts1.length ==3){
                String data1 = dataParts1[0].trim();
                String data2 = dataParts1[1].trim();
                String data3 = dataParts1[2].trim();
                System.out.println("人的坐标为"+ "("+data1 + "," + data2+ "," +data3 + ")");
                String sql1="UPDATE employee SET geom_x = ?, geom_y = ?, geom_z = ? WHERE employeeid ='1'";
                jdbc.executeSql(sql1,dataParts1);
            }else{
                System.out.println("1Received data format is incorrect.");
            }

            //物的坐标
            String MaterLocationstr=MaterLocation.asText();
            String[] dataParts2 = MaterLocationstr.substring(1, MaterLocationstr.length() - 1).split(",");
            if(dataParts2.length ==3){
                String data1 = dataParts2[0].trim();
                String data2 = dataParts2[1].trim();
                String data3 = dataParts2[2].trim();
                System.out.println("物料的坐标为"+ "("+data1 + "," + data2+ "," +data3 + ")");
                String sql2="UPDATE material SET geom_x = ?, geom_y = ?, geom_z = ? WHERE materialid =1";
                jdbc.executeSql(sql2,dataParts2);
            }else{
                System.out.println("2Received data format is incorrect.");
            }

            //车的坐标
            String CarLocationstr=CarLocation.asText();
            String[] dataParts3 = CarLocationstr.substring(1, CarLocationstr.length() - 1).split(",");
            if(dataParts3.length ==3){
                String data1 = dataParts3[0].trim();
                String data2 = dataParts3[1].trim();
                String data3 = dataParts3[2].trim();
                System.out.println("车的坐标为"+ "("+data1 + "," + data2+ "," +data3 + ")");
                String sql3="UPDATE car SET geom_x = ?, geom_y = ?, geom_z = ? WHERE carid =101";
                jdbc.executeSql(sql3,dataParts3);
            }else{
                System.out.println("3Received data format is incorrect.");
            }

            //可在此处实现收到数据后的处理
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
            System.out.println("Mqtt message deliver complete.");
        }
    };

    /**
     * 订阅topic
     */
    private void subScribeTopic() {
        try {
            client.subscribe(shareSubscribeTopic, qosLevel, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken iMqttToken) {
                    System.out.println("Subscribe mqtt topic onSuccess qos:" + iMqttToken.getGrantedQos()[0]);
                    if (iMqttToken.getGrantedQos()[0] > 2 ) { //AT_MOST_ONCE(0), AT_LEAST_ONCE(1), EXACTLY_ONCE(2), FAILURE(128)
                        System.out.println("Subscribe mqtt topic faild");
                    }
                }

                @Override
                public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                    System.out.println("Subscribe mqtt topic faild");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载SSL证书
     *
     * @param certPath 证书存放的相对路径
     * @return
     */
    private SocketFactory getOptionSocketFactory(String certPath) {
        SSLContext sslContext;

        InputStream stream = null;
        try {
            stream = new FileInputStream(certPath);
            sslContext = SSLContext.getInstance("TLS");
            KeyStore ts = KeyStore.getInstance("JKS");
            ts.load(stream, null);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ts);
            TrustManager[] tm = tmf.getTrustManagers();
            sslContext.init(null, tm, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sslContext.getSocketFactory();
    }

    private void close() {
        try {
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

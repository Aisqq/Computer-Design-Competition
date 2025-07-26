package com.example.backend.huawei.demo.device;

import com.huaweicloud.sdk.core.auth.ICredential;
import com.huaweicloud.sdk.core.auth.AbstractCredentials;
import com.huaweicloud.sdk.core.auth.BasicCredentials;
import com.huaweicloud.sdk.core.exception.ConnectionException;
import com.huaweicloud.sdk.core.exception.RequestTimeoutException;
import com.huaweicloud.sdk.core.exception.ServiceResponseException;
import com.huaweicloud.sdk.core.region.Region;
import com.huaweicloud.sdk.iotda.v5.*;
import com.huaweicloud.sdk.iotda.v5.model.*;

import static com.example.backend.huawei.util.Constants.CLOUD_SDK_AK;
import static com.example.backend.huawei.util.Constants.CLOUD_SDK_SK;


public class CreateMessageSolution {

    public static void main(String[] args) {
        // The AK and SK used for authentication are hard-coded or stored in plaintext, which has great security risks. It is recommended that the AK and SK be stored in ciphertext in configuration files or environment variables and decrypted during use to ensure security.
        // In this example, AK and SK are stored in environment variables for authentication. Before running this example, set environment variables CLOUD_SDK_AK and CLOUD_SDK_SK in the local environment
        String ak = CLOUD_SDK_AK;
        String sk = CLOUD_SDK_SK;
        // ENDPOINT：请在控制台的"总览"界面的"平台接入地址"中查看“应用侧”的https接入地址。
        String iotdaEndpoint = "https://698f2c6548.st1.iotda-app.cn-north-4.myhuaweicloud.com";

        ICredential auth = new BasicCredentials()
                // 标准版/企业版需要使用衍生算法，基础版请删除配置"withDerivedPredicate";
                .withDerivedPredicate(AbstractCredentials.DEFAULT_DERIVED_PREDICATE) // Used in derivative ak/sk authentication scenarios
                .withAk(ak)
                .withSk(sk);

        IoTDAClient client = IoTDAClient.newBuilder()
                .withCredential(auth)
                // 标准版/企业版：需自行创建Region对象，基础版：请使用IoTDARegion的region对象，如"withRegion(IoTDARegion.CN_NORTH_4)"
                .withRegion(new Region("cn-north-4", iotdaEndpoint))
                .build();
        CreateMessageRequest request = new CreateMessageRequest();
        request.withDeviceId("668924855830dc113ecb2cda_860059074965505");
        request.withInstanceId("9f5a7eca-c5cf-49af-8902-1addda6fd0f9");
        DeviceMessageRequest body = new DeviceMessageRequest();
        body.withMessage("{\"taskname\":\"A\"}");
        request.withBody(body);
        try {
            CreateMessageResponse response = client.createMessage(request);
            System.out.println(response.toString());
        } catch (ConnectionException e) {
            e.printStackTrace();
        } catch (RequestTimeoutException e) {
            e.printStackTrace();
        } catch (ServiceResponseException e) {
            e.printStackTrace();
            System.out.println(e.getHttpStatusCode());
            System.out.println(e.getRequestId());
            System.out.println(e.getErrorCode());
            System.out.println(e.getErrorMsg());
        }
    }
}
package com.example.backend.common;

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

public class MessageSender {

    private static final String AK = CLOUD_SDK_AK; // 从配置文件或环境变量获取
    private static final String SK = CLOUD_SDK_SK; // 从配置文件或环境变量获取
    private static final String DEVICE_ID = "6672a2ca7dbfd46fabc1076e_nbiot";
    private static final String INSTANCE_ID = "9f5a7eca-c5cf-49af-8902-1addda6fd0f9";
    private static final String ENDPOINT = "https://698f2c6548.st1.iotda-app.cn-north-4.myhuaweicloud.com";

    public static void sendMessage(String messageContent) {
        ICredential auth = new BasicCredentials()
                // 标准版/企业版需要使用衍生算法，基础版请删除配置"withDerivedPredicate";
                .withDerivedPredicate(AbstractCredentials.DEFAULT_DERIVED_PREDICATE) // Used in derivative ak/sk authentication scenarios
                .withAk(AK)
                .withSk(SK);
        IoTDAClient client = IoTDAClient.newBuilder()
                .withCredential(auth)
                .withRegion(new Region("cn-north-4", ENDPOINT))
                .build();

        CreateAsyncCommandRequest request = new CreateAsyncCommandRequest();
        request.withDeviceId(DEVICE_ID)
                .withInstanceId(INSTANCE_ID);
        AsyncDeviceCommandRequest body = new AsyncDeviceCommandRequest();
        body.withSendStrategy("delay");
        body.withCommandName("task");
        body.withParas("{\"taskname\":\"" + messageContent + "\"}");
        request.withBody(body);

        try {
            CreateAsyncCommandResponse response = client.createAsyncCommand(request);
            System.out.println("Command sent successfully: " + response.toString());
        } catch (ServiceResponseException | RequestTimeoutException | ConnectionException e) {
            e.printStackTrace();
            System.err.println("Failed to send command: " + e.getMessage());
        }
    }
}

package com.example.backend.huawei.util;

public class Constants {


    public static final String CLOUD_SDK_AK = "LRG0CGYZ3J1NWWQ6IOT4";
    public static final String CLOUD_SDK_SK = "QvDqM1biu1ujndvS5yRaLF3mxXSZIkOpulJ0qeSN";

    public static final String TOKEN_BASE_URL = "https://iam.cn-north-4.myhuaweicloud.com";
    public static final String IOTDM_BASE_URL = "https://698f2c6548.st1.iotda-app.cn-north-4.myhuaweicloud.com";

    public static final String TOKEN_ACCESS_URL = TOKEN_BASE_URL + "/v3/auth/tokens";

    public static final String DEVICE_COMMAND_URL = IOTDM_BASE_URL + "/v5/iot/840b5077cd664026a40a9b12704c426d/devices/6672a2ca7dbfd46fabc1076e_nbiot";
    public static final String PRODUCT_COMMAND_URL = IOTDM_BASE_URL + "/v5/iot/%s/products";
    //查询设备影子
    public static final String DEVICE_SHADOW_URL = IOTDM_BASE_URL + "/v5/iot/840b5077cd664026a40a9b12704c426d/devices/6672a2ca7dbfd46fabc1076e_nbiot/shadow";
}

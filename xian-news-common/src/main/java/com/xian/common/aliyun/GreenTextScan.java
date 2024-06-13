package com.xian.common.aliyun;


import com.alibaba.fastjson.JSON;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.green20220302.models.TextModerationRequest;
import com.aliyun.green20220302.models.TextModerationResponse;
import com.aliyun.green20220302.Client;
import com.aliyun.green20220302.models.TextModerationResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "aliyun")
public class GreenTextScan {

    private String accessKeyId;
    private String secret;

    public Map greeTextScan(String content) throws Exception {
        Config config = new Config();
        config.setAccessKeyId(accessKeyId);
        config.setAccessKeySecret(secret);
        //接入区域和地址请根据实际情况修改
        config.setRegionId("cn-shanghai");
        config.setEndpoint("green-cip.cn-shanghai.aliyuncs.com");
        //连接时超时时间，单位毫秒（ms）。
        config.setReadTimeout(6000);
        //读取时超时时间，单位毫秒（ms）。
        config.setConnectTimeout(3000);
        Client client = new Client(config);

        // 创建RuntimeObject实例并设置运行参数。
        RuntimeOptions runtime = new RuntimeOptions();
        runtime.readTimeout = 10000;
        runtime.connectTimeout = 10000;

        //检测参数构造
        JSONObject serviceParameters = new JSONObject();
        serviceParameters.put("content", content);

        //检测结果构造
        Map<String,String> resultMap = new HashMap<>();
        if (serviceParameters.get("content") == null || serviceParameters.getString("content").trim().length() == 0) {
            resultMap.put("suggestion","检测内容为空");
            System.out.println("text moderation content is empty");
            return resultMap;
        }
        TextModerationRequest textModerationRequest = new TextModerationRequest();
        /*
        文本检测service：内容安全控制台文本增强版规则配置的serviceCode，示例：chat_detection
        */
        textModerationRequest.setService("comment_detection");
        textModerationRequest.setServiceParameters(serviceParameters.toJSONString());

        try {
            // 调用方法获取检测结果。
            TextModerationResponse response = client.textModerationWithOptions(textModerationRequest, runtime);

            // 自动路由。
            if (response != null) {
                // 服务端错误，区域切换到cn-beijing。
                if (500 == response.getStatusCode() || (response.getBody() != null && 500 == (response.getBody().getCode()))) {
                    // 接入区域和地址请根据实际情况修改。
                    config.setRegionId("cn-beijing");
                    config.setEndpoint("green-cip.cn-beijing.aliyuncs.com");
                    client = new Client(config);
                    response = client.textModerationWithOptions(textModerationRequest, runtime);
                }

            }
            // 打印检测结果。
            if (response != null) {
                if (response.getStatusCode() == 200) {
                    TextModerationResponseBody result = response.getBody();
                    System.out.println(JSON.toJSONString(result));
                    Integer code = result.getCode();
                    if (code != null && code == 200) {
                        TextModerationResponseBody.TextModerationResponseBodyData data = result.getData();
                        if (data.getLabels().isEmpty() && data.getReason().isEmpty()) {
                            resultMap.put("suggestion", "pass");
                        }else {
                            resultMap.put("suggestion","block");
                            resultMap.put("labels",data.getLabels());
                            resultMap.put("reason", data.getReason());
                        }
                        System.out.println("labels = [" + data.getLabels() + "]");
                        System.out.println("reason = [" + data.getReason() + "]");
                    } else {
                        System.out.println("text moderation not success. code:" + code);
                    }
                } else {
                    System.out.println("response not success. status:" + response.getStatusCode());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

}


//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.exceptions.ServerException;
//import com.aliyuncs.green.model.v20180509.TextScanRequest;
//import com.aliyuncs.http.FormatType;
//import com.aliyuncs.http.HttpResponse;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.*;
//
//@Getter
//@Setter
//@Component
//@ConfigurationProperties(prefix = "aliyun")
//public class GreenTextScan {
//
//    private String accessKeyId;
//    private String secret;
//
//    public Map greeTextScan(String content) throws Exception {
//        System.out.println(accessKeyId);
//        IClientProfile profile = DefaultProfile
//                .getProfile("cn-shanghai", accessKeyId, secret);
//        DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
//        IAcsClient client = new DefaultAcsClient(profile);
//        TextScanRequest textScanRequest = new TextScanRequest();
//        textScanRequest.setAcceptFormat(FormatType.JSON); // 指定api返回格式
//        textScanRequest.setHttpContentType(FormatType.JSON);
//        textScanRequest.setMethod(com.aliyuncs.http.MethodType.POST); // 指定请求方法
//        textScanRequest.setEncoding("UTF-8");
//        textScanRequest.setRegionId("cn-shanghai");
//        List<Map<String, Object>> tasks = new ArrayList<Map<String, Object>>();
//        Map<String, Object> task1 = new LinkedHashMap<String, Object>();
//        task1.put("dataId", UUID.randomUUID().toString());
//        /**
//         * 待检测的文本，长度不超过10000个字符
//         */
//        task1.put("content", content);
//        tasks.add(task1);
//        JSONObject data = new JSONObject();
//
//        /**
//         * 检测场景，文本垃圾检测传递：antispam
//         **/
//        data.put("scenes", Arrays.asList("antispam"));
//        data.put("tasks", tasks);
//        System.out.println(JSON.toJSONString(data, true));
//        textScanRequest.setHttpContent(data.toJSONString().getBytes("UTF-8"), "UTF-8", FormatType.JSON);
//        // 请务必设置超时时间
//        textScanRequest.setConnectTimeout(3000);
//        textScanRequest.setReadTimeout(6000);
//
//        Map<String, String> resultMap = new HashMap<>();
//        try {
//            HttpResponse httpResponse = client.doAction(textScanRequest);
//            if (httpResponse.isSuccess()) {
//                JSONObject scrResponse = JSON.parseObject(new String(httpResponse.getHttpContent(), "UTF-8"));
//                System.out.println(JSON.toJSONString(scrResponse, true));
//                if (200 == scrResponse.getInteger("code")) {
//                    JSONArray taskResults = scrResponse.getJSONArray("data");
//                    for (Object taskResult : taskResults) {
//                        if (200 == ((JSONObject) taskResult).getInteger("code")) {
//                            JSONArray sceneResults = ((JSONObject) taskResult).getJSONArray("results");
//                            for (Object sceneResult : sceneResults) {
//                                String scene = ((JSONObject) sceneResult).getString("scene");
//                                String label = ((JSONObject) sceneResult).getString("label");
//                                String suggestion = ((JSONObject) sceneResult).getString("suggestion");
//                                System.out.println("suggestion = [" + label + "]");
//                                if (!suggestion.equals("pass")) {
//                                    resultMap.put("suggestion", suggestion);
//                                    resultMap.put("label", label);
//                                    return resultMap;
//                                }
//
//                            }
//                        } else {
//                            return null;
//                        }
//                    }
//                    resultMap.put("suggestion", "pass");
//                    return resultMap;
//                } else {
//                    return null;
//                }
//            } else {
//                return null;
//            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
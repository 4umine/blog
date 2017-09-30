package com.coderbike.utils.http;

import com.squareup.okhttp.*;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.Map;

/**
 * 描述
 * @author LBG - 2017/9/29 0029
 */
public class HttpUtils {

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String okPost(String url, String postBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }
        return response.body().string();
    }

    public static String okPostForm(String url, Map<String, Object> params) throws IOException {
        OkHttpClient client = new OkHttpClient();
        FormEncodingBuilder builder = new FormEncodingBuilder();
        if (MapUtils.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.add(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }
        return response.body().string();
    }
}

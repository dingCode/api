package com.knowlegene.test;

/**
 * Created by hongkai on 2016/12/22.
 */

import com.rop.Constants;
import com.rop.RopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.*;

public class CopUtils {

    private static final Logger logger = LoggerFactory.getLogger(CopUtils.class);

    /**
     * ʹ��<code>secret</code>��paramValues�������㷨����ǩ���� <br/>
     * uppercase(hex(sha1(secretkey1value1key2value2...secret))
     *
     * @param paramValues �����б�
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, String secret) {
        return sign(paramValues,null,secret);
    }

    /**
     * ��paramValues����ǩ��������ignoreParamNames��Щ����������ǩ��
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames,String secret) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if(ignoreParamNames != null && ignoreParamNames.size() > 0){
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            throw new RopException(e);
        }
    }

    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), Constants.UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes(Constants.UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes(Constants.UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    /**
     * ������תʮ�������ַ�
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }

    /**
     *
     * @param form
     * @param serverUrl
     * @return
     */
    public static  String buildGetUrl(Map<String, String> form,String serverUrl) {
        StringBuilder requestUrl = new StringBuilder();
        requestUrl.append(serverUrl);
        requestUrl.append("?");
        String joinChar = "";
        for (Map.Entry<String, String> entry : form.entrySet()) {
            requestUrl.append(joinChar);
            requestUrl.append(entry.getKey());
            requestUrl.append("=");
            requestUrl.append(entry.getValue());
            joinChar = "&";
        }
        return requestUrl.toString();
    }

    /**
     *
     * @param form
     * @return
     */
    public static  MultiValueMap<String, String> toMultiValueMap(Map<String, String> form) {
        MultiValueMap<String, String> mvm = new LinkedMultiValueMap<String, String>();
        for (Map.Entry<String, String> entry : form.entrySet()) {
            mvm.add(entry.getKey(), entry.getValue());
        }
        return mvm;
    }
}


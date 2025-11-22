package com.cheshun.common.tools.utils;


import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author 阿隆
 * Created on 2021/2/19 10:19.
 */
public class HttpUtils {
    private static final String CR_LF = "\r\n";
    private static final String TWO_DASHES = "--";

    /**
     * GET请求
     *
     * @param urlStr 请求链接
     * @param header 请求头
     * @param query  请求参数
     * @return 请求结果
     * @throws IOException IO异常
     */
    public static HttpResponse get(String urlStr, Map<String, String> header, Map<String, String> query) throws IOException {
        HttpURLConnection httpURLConnection = openGetConnection(buildUrl(urlStr, query), header);
        HttpResponse response = new HttpResponse();
        response.setStatusCode(httpURLConnection.getResponseCode());
        response.setData(readString(httpURLConnection.getInputStream()));
        response.setHeader(httpURLConnection.getHeaderFields());
        return response;
    }

    /**
     * POST请求
     *
     * @param urlStr 请求链接
     * @param header 请求头
     * @param body   请求体
     * @return 请求结果
     * @throws IOException IO异常
     */
    public static HttpResponse post(String urlStr, Map<String, String> header, String body) throws IOException {
        HttpURLConnection httpURLConnection = openPostConnection(buildUrl(urlStr, null), header, body);
        HttpResponse response = new HttpResponse();
        response.setStatusCode(httpURLConnection.getResponseCode());
        response.setData(readString(httpURLConnection.getInputStream()));
        response.setHeader(httpURLConnection.getHeaderFields());
        return response;
    }

    /**
     * HTTPS请求
     *
     * @param urlStr       请求链接
     * @param header       请求头
     * @param body         请求体
     * @param certStream   HTTPS证书文件流
     * @param certPassword HTTPS证书密码
     * @return 请求结果
     * @throws Exception 异常
     */
    public static HttpResponse postWithCert(String urlStr, Map<String, String> header, String body, InputStream certStream, String certPassword) throws Exception {
        HttpsURLConnection httpURLConnection = openHttpsConnection(buildUrl(urlStr, null), header, body, certStream, certPassword);
        HttpResponse response = new HttpResponse();
        response.setStatusCode(httpURLConnection.getResponseCode());
        response.setData(readString(httpURLConnection.getInputStream()));
        response.setHeader(httpURLConnection.getHeaderFields());
        return response;
    }

    /**
     * GET下载文件
     *
     * @param urlStr 请求地址
     * @param header 请求头
     * @param query  请求参数
     * @return 文件byte数组
     * @throws IOException IO异常
     */
    public static byte[] download(String urlStr, Map<String, String> header, Map<String, String> query) throws IOException {
        HttpURLConnection httpURLConnection = openGetConnection(buildUrl(urlStr, query), header);
        return readBytes(httpURLConnection.getInputStream());
    }

    /**
     * POST下载文件
     *
     * @param urlStr 请求地址
     * @param header 请求头
     * @param body   请求体
     * @return 文件byte数组
     * @throws Exception 异常
     */
    public static byte[] downloadWithPost(String urlStr, Map<String, String> header, String body) throws Exception {
        HttpURLConnection httpURLConnection = openPostConnection(buildUrl(urlStr, null), header, body);
        return readBytes(httpURLConnection.getInputStream());
    }

    /**
     * POST上传文件
     *
     * @param urlStr      请求链接
     * @param header      请求头
     * @param contentType 文件类型
     * @param fileName    文件名称
     * @param fileData    文件数据
     * @return 请求结果
     * @throws Exception 异常
     */
    public static HttpResponse upload(String urlStr, Map<String, String> header, String contentType, String fileName, byte[] fileData) throws Exception {
        String boundary = UUID.randomUUID().toString();
        HttpURLConnection httpURLConnection = openPostConnection(buildUrl(urlStr, null), header, null);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty("connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        DataOutputStream outputStreamWriter = new DataOutputStream(httpURLConnection.getOutputStream());
        outputStreamWriter.writeUTF(TWO_DASHES + boundary
                + CR_LF
                + "Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\""
                + CR_LF
                + "Content-Type: " + contentType
                + CR_LF + CR_LF);
        outputStreamWriter.write(fileData);
        outputStreamWriter.writeUTF(CR_LF + TWO_DASHES + boundary + TWO_DASHES + CR_LF);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        HttpResponse response = new HttpResponse();
        response.setStatusCode(httpURLConnection.getResponseCode());
        response.setData(readString(httpURLConnection.getInputStream()));
        return response;
    }

    /**
     * POST上传文件
     *
     * @param urlStr  请求链接
     * @param header  请求头
     * @param param   请求参数
     * @param fileMap 上传的文件
     * @return 请求结果
     * @throws IOException 异常
     */
    public static String upload(String urlStr, Map<String, String> header, Map<String, String> param, Map<String, File> fileMap) throws IOException {
        String boundary = UUID.randomUUID().toString().replace("-", "");
        String end = CR_LF + TWO_DASHES + boundary + TWO_DASHES + CR_LF;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(urlStr).openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, String> entry : header.entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        DataOutputStream outputStreamWriter = new DataOutputStream(httpURLConnection.getOutputStream());
        if (param != null) {
            StringBuilder content = new StringBuilder();
            for (String key : param.keySet()) {
                content.append(TWO_DASHES).append(boundary)
                        .append(CR_LF)
                        .append("Content-Disposition: form-data; name=\"").append(key).append("\"")
                        .append(CR_LF)
                        .append("Content-Type: application/json; charset=UTF-8")
                        .append(CR_LF)
                        .append("Content-Transfer-Encoding: 8bit")
                        .append(CR_LF)
                        .append(CR_LF)
                        .append(param.get(key))
                        .append(CR_LF);
            }
            outputStreamWriter.writeUTF(content.toString());
        }
        if (fileMap != null) {
            int i = 0;
            for (String key : fileMap.keySet()) {
                String start = TWO_DASHES + boundary +
                        CR_LF +
                        "Content-Disposition: form-data; name=\"" + key + "\"; filename=\"" + key + "\"" +
                        CR_LF +
                        "Content-Type: application/octet-stream" +
                        CR_LF +
                        "Content-Transfer-Encoding: binary" +
                        CR_LF +
                        CR_LF;
                outputStreamWriter.write(start.getBytes(StandardCharsets.UTF_8));
                byte[] content = new byte[1024];
                int len;
                try (InputStream in = new FileInputStream(fileMap.get(key))) {
                    while ((len = in.read(content)) != -1) {
                        outputStreamWriter.write(content, 0, len);
                    }
                    if (i < fileMap.size() - 1) {
                        outputStreamWriter.write(end.getBytes(StandardCharsets.UTF_8));
                    }
                }
                i++;
            }
        }
        outputStreamWriter.writeUTF(end);
        outputStreamWriter.close();
        StringBuilder sb = new StringBuilder();
        try (InputStream is = httpURLConnection.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = new String(line.getBytes(), StandardCharsets.UTF_8);
                sb.append(line);
            }
        }
        return sb.toString();
    }

    /**
     * 构建URL
     *
     * @param url   请求地址
     * @param query 请求地址上要拼接的参数
     * @return 拼接好的URL
     * @throws UnsupportedEncodingException 异常
     * @throws MalformedURLException        异常
     */
    private static URL buildUrl(String url, Map<String, String> query) throws UnsupportedEncodingException, MalformedURLException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(url);
        if (query != null && query.size() > 0) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> entry : query.entrySet()) {
                if (sbQuery.length() > 0) {
                    sbQuery.append("&");
                }
                if (entry.getKey() == null || entry.getKey().length() == 0) {
                    if (entry.getValue() == null || entry.getValue().length() == 0) {
                        sbQuery.append(entry.getValue());
                    }
                } else {
                    sbQuery.append(entry.getKey());
                    if (entry.getValue() == null || entry.getValue().length() == 0) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
                    }
                }
            }
            if (sbQuery.length() > 0) {
                if (!url.contains("?")) {
                    // URL没带参数
                    sbUrl.append("?");
                } else {
                    // URL带了参数
                    if (!url.endsWith("&")) {
                        sbUrl.append("&");
                    }
                }
                sbUrl.append(sbQuery);
            }
        }
        return new URL(sbUrl.toString());
    }

    /**
     * 打开GET请求连接
     *
     * @param url    请求地址
     * @param header 请求头
     * @return GET请求连接
     * @throws IOException IO异常
     */
    private static HttpURLConnection openGetConnection(URL url, Map<String, String> header) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        setHeader(httpURLConnection, header);
        return httpURLConnection;
    }

    /**
     * 打开POST请求连接
     *
     * @param url    请求地址
     * @param header 请求头
     * @param body   请求体
     * @return POST请求连接
     * @throws IOException IO异常
     */
    private static HttpURLConnection openPostConnection(URL url, Map<String, String> header, String body) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        setHeader(httpURLConnection, header);
        writeBody(httpURLConnection, body);
        return httpURLConnection;
    }

    /**
     * 打开HTTPS请求连接
     *
     * @param url          请求地址
     * @param header       请求头
     * @param certStream   HTTPS证书文件流
     * @param certPassword HTTPS证书密码
     * @param body         请求体
     * @return POST请求连接
     * @throws IOException IO异常
     */
    private static HttpsURLConnection openHttpsConnection(URL url, Map<String, String> header, String body, InputStream certStream, String certPassword) throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        // 创建 SSLContext
        KeyStore keystore = KeyStore.getInstance("PKCS12");
        char[] keyPassword = certPassword.toCharArray(); //证书密码
        keystore.load(certStream, keyPassword);
        // 实例化密钥库 & 初始化密钥工厂
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keystore, keyPassword);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                //信任所有证书
                return null;
            }
        }}, new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
        httpsURLConnection.setRequestMethod("POST");
        httpsURLConnection.setHostnameVerifier((s, sslSession) -> true);
        httpsURLConnection.setSSLSocketFactory(sslSocketFactory);
        setHeader(httpsURLConnection, header);
        writeBody(httpsURLConnection, body);
        return httpsURLConnection;
    }

    /**
     * 给请求添加header
     *
     * @param urlConnection 请求连接
     * @param header        请求头
     */
    private static void setHeader(URLConnection urlConnection, Map<String, String> header) {
        if (header != null && header.size() > 0) {
            for (Map.Entry<String, String> e : header.entrySet()) {
                urlConnection.setRequestProperty(e.getKey(), e.getValue());
            }
        }
    }

    /**
     * 把body写入请求
     *
     * @param urlConnection 请求连接
     * @param body          请求体
     * @throws IOException IO异常
     */
    private static void writeBody(URLConnection urlConnection, String body) throws IOException {
        if (body == null || body.length() == 0) {
            return;
        }
        urlConnection.setDoOutput(true);
        try (OutputStream outputStream = urlConnection.getOutputStream();
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.write(body.getBytes());
            dataOutputStream.flush();
        }
    }

    /**
     * 读取返回结果（字符串形式）
     *
     * @param inputStream 输入流
     * @return 从输入流里读取的字符串
     * @throws IOException IO异常
     */
    private static String readString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = inputStream;
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = new String(line.getBytes(), StandardCharsets.UTF_8);
                sb.append(line);
            }
        }
        return sb.toString();
    }

    /**
     * 读取返回结果（byte数组形式）
     *
     * @param inputStream 输入流
     * @return 从输入流里读取的byte数组
     * @throws IOException IO异常
     */
    private static byte[] readBytes(InputStream inputStream) throws IOException {
        byte[] data;
        try (InputStream is = inputStream;
             ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            data = outStream.toByteArray();
        }
        return data;
    }

    public static class HttpResponse {
        private int statusCode;
        private String data;
        private Map<String, List<String>> header;

        public int getStatusCode() {
            return statusCode;
        }


        void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }


        public String getData() {
            return data;
        }


        public void setData(String data) {
            this.data = data;
        }

        public Map<String, List<String>> getHeader() {
            return header;
        }

        public void setHeader(Map<String, List<String>> header) {
            this.header = header;
        }

        public List<String> getHeader(String name) {
            if (header != null && header.containsKey(name)) {
                return header.get(name);
            }
            return null;
        }
    }
}

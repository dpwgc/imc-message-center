package com.dpwgc.message.center.infrastructure.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZip工具类
 * zip压缩解压并使用Base64进行编码工具类
 * 调用：
 * 压缩
 *  GZipUtil.compress(str)
 * 解压
 * GZipUtil.uncompressToString(bytes)
 * 压缩率实测原来大小的60%
 */
@Slf4j
@UtilityClass
public class GzipUtil {
    /**
     * 将字符串进行gzip压缩
     *
     * @param data
     * @return
     */
    public static String compress(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(data.getBytes(StandardCharsets.UTF_8));
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeBase64(out.toByteArray());
    }

    public static String compress(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(data);
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeBase64(out.toByteArray());
    }

    public static String uncompress(String data) {

        byte[] decode = decodeBase64(data);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(decode);
        GZIPInputStream gzipStream = null;
        try {
            gzipStream = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gzipStream.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            throw new RuntimeException("GZIP");
        } finally {
            try {
                out.close();
                if (gzipStream != null) {
                    gzipStream.close();
                }
            } catch (IOException e) {
                // TODO
            }
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }

    public static String encodeBase64(byte[] input) {
        return new String(Base64.encodeBase64(input));
    }

    public static byte[] decodeBase64(String input) {
        return Base64.decodeBase64(input.getBytes());
    }
}

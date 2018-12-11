package com.lishu.bike.utils;

import java.io.IOException;
import java.security.MessageDigest;

public class MD5 {
    public String getMd5(String str) {
        MessageDigest md5New = null;
        
        try {
            md5New = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        byte[] bs = md5New.digest(str.getBytes());
        StringBuilder sb = new StringBuilder(40);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }

	public static void main(String[] args) throws IOException {
		System.out.println(new MD5().getMd5("123456"));
	}
}

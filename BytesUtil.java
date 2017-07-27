package com.it18zhang.util;

/**
 * 字节工具类型
 */
public class BytesUtil {
	
	/**
	 * 将整数转换成字节数组
	 */
	public static byte[] int2ByteArr(int i){
		byte[] bytes = new byte[4] ;
		bytes[0] = (byte)(i >> 0) ;
		bytes[1] = (byte)(i >> 8) ;
		bytes[2] = (byte)(i >> 16) ;
		bytes[3] = (byte)(i >> 24) ;
		return bytes ;
	}
	
	/**
	 * 将字节数组转换成整数
	 */
	public static int byteArr2Int(byte[] arr){
		return ((arr[0] & 0xFF) << 0)
			| ((arr[1] & 0xFF) << 8)
			| ((arr[2] & 0xFF) << 16)
			| ((arr[3] & 0xFF) << 24) ;
	}
}

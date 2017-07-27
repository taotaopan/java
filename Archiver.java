package com.it18zhang.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.it18zhang.util.BytesUtil;

/**
 * 归档器
 */
public class Archiver {
	/**
	 * 添加文件到归档文件中
	 */
	public static void addFile(String archiveFile,String addFile){
		try {
//			//判断归档文件是否存在
//			File xarFile = new File(archiveFile);
//			if(!xarFile.exists()){
//				xarFile.createNewFile();
//			}
			
			//归档文件输出流,
			FileOutputStream fos = new FileOutputStream(archiveFile,true);
			//读取的源文件
			FileInputStream fis = new FileInputStream(addFile);
			
			//处理文件名问题
			File srcFile = new File(addFile);
			//得到文件名
			String srcFileName = srcFile.getName();
			//源文件名字节数组
			byte[] srcFileNameBytes = srcFileName.getBytes();
			
			//1.写入字节数组的长度到fos
			byte[] bytes4 = BytesUtil.int2ByteArr(srcFileNameBytes.length);
			fos.write(bytes4);
			
			//2.写入文件名数据
			fos.write(srcFileNameBytes);
			
			//3.写入文件内容长度
			int fileLen = (int)srcFile.length();
			bytes4 = BytesUtil.int2ByteArr(fileLen);
			fos.write(bytes4);
			
			//4.写入文件内容
			byte[] buf = new byte[1024] ;
			int len = -1 ;
			while((len = fis.read(buf)) !=-1){
				fos.write(buf, 0, len);
			}
			fos.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 解档程序
	 */
	public static void unarchive(String archFile,String destDir){
		try {
			FileInputStream fis = new FileInputStream(archFile);
			
			//每次读取的一个文件
			while(true){
				byte[] bytes4 = new byte[4];
				//读取文件4个字节
				int len = fis.read(bytes4);
				if(len == -1){
					break ;
				}
				
				//获得文件名长度
				int fileNameLen = BytesUtil.byteArr2Int(bytes4);
				byte[] fileNameBytes = new byte[fileNameLen];
				fis.read(fileNameBytes);
				String fileName = new String(fileNameBytes);
				
				//构造输出目录下的文件
				FileOutputStream fos = new FileOutputStream(new File(destDir,fileName)) ;
				
				//读取文件内容的长度
				byte[] contBytes4 = new byte[4] ;
				fis.read(contBytes4);
				int contLen = BytesUtil.byteArr2Int(contBytes4);
				
				//读取文件内容
				byte[] contBytes = new byte[contLen] ;
				fis.read(contBytes);
				fos.write(contBytes);
				fos.close();
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package io.dir.util;

import java.io.File;

import io.dir.util.Directory.TreeInfo;

public class DirectoryDemo {
	public static void main(String[] args) {
		//所有目录以及文件
		TreeInfo tree = Directory.walk("./src");
		System.out.println(PPrint.pformat(tree.fileAndDirs));
		System.out.println("所有文件");
		//所有以java结尾的文件
		for(File file : Directory.local("./src/io/dir/util/",".*\\.java")){
			System.out.println(file);
		}
		
		
	}
}

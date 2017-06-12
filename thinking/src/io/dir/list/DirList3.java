package io.dir.list;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DirList3 {
	
	

	
	public static void main(String[] args) {
		System.out.println("输入文件目录");
		Scanner scanner = new Scanner(System.in);
		
		String file = scanner.next();
		File path = new File(file);
		String[] list;
		System.out.println("输入正则表达式搜索（/代表所有文件）");
		String pa = scanner.next();
		if(pa.equals("/")){
			list = path.list();
		}else{
			//实现文件匹配规则
			list = path.list(new FilenameFilter(){
				
				Pattern pattern = Pattern.compile(pa);
				
				@Override
				public boolean accept(File dir, String name) {
					return pattern.matcher(name).matches();
				}
				
			});
		}
		//list排序以及排序规则
		Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
		for(String dirItem:list){
			System.out.println(dirItem);
		}
		if(scanner!=null)
		scanner.close();
	}
	
	
}




package io.dir.util;

import java.util.List;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Pattern;

public final class Directory {
	
	public static File[] local(File dir,final String regex){
		
		return dir.listFiles(new FilenameFilter(){

		private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File arg0, String arg1) {
				
				return pattern.matcher(arg1).matches();
			}
			
		});
		
	}
	
	public static File[] local(String path,final String regex){
		return local(new File(path), regex);
	}
	
	
	public static class TreeInfo implements Iterable<File>{
		
		public List<File> files = new ArrayList<>();
		public List<File> dirs = new ArrayList<>();
		public List<File> fileAndDirs = new ArrayList<>();
		
		//默认迭代文件
		@Override
		public Iterator<File> iterator() {
			// TODO Auto-generated method stub
			return files.iterator();
		}
		
		void addAll(TreeInfo other){
			files.addAll(other.files);
			dirs.addAll(other.dirs);
			fileAndDirs.addAll(other.fileAndDirs);
		}
		
		@Override
		public String toString() {
			return "dirs:" + PPrint.pformat(dirs)+
					"\n\nfiles" + PPrint.pformat(dirs);
		}
		
		
		
	}
	
	
	
	public static TreeInfo walk(String start,String regex){
		return recurseDir(new File(start),regex);
	}

	public static TreeInfo walk(File start,String regex){
		return recurseDir(start,regex);
	}
	public static TreeInfo walk(String start){
		return recurseDir(new File(start),".*");
	}
	

	public static TreeInfo walk(File start){
		return recurseDir(start,".*");
	}
	
	public static TreeInfo recurseDir(File file, String regex) {
		TreeInfo result = new TreeInfo();
		for(File item : file.listFiles()){
			if(item.isDirectory()){
				result.dirs.add(item);
				result.fileAndDirs.add(item);
				result.addAll(recurseDir(item, regex));
			}else{
				if(item.getName().matches(regex)){
					result.files.add(item);
					result.fileAndDirs.add(item);
				}
			}
			
		}
		return result;
	}


	
	
	
	
}





























package io.dir.util;

import java.io.File;
import java.io.IOException;

import org.omg.Messaging.SyncScopeHelper;

public class ProcessFiles {
	public interface Strategy {
		void process(File file);

	}

	private Strategy strategy;
	private String ext;

	public ProcessFiles(Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}

	public void start(String [] args){
//			args = new String[]{"/usr/local/tmall"};
			try {
				if(args.length == 0){
					System.out.println("当前");
					processDirectoryTree(new File("."));
			}else{
				
				for(String arg : args){
					System.out.println(arg);
					File fileArg = new File(arg);
					if(fileArg.isDirectory()){
						System.out.println("目录");
						processDirectoryTree(fileArg);
					}else{
						if(!arg.endsWith("."+ext)){
							arg += "."+ext;
							strategy.process(new File(arg).getCanonicalFile());
						}
					}
				}
			}
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void processDirectoryTree(File root) throws IOException {
		for (File file : Directory.walk(root.getAbsolutePath(), ".*\\." + ext)) {
			strategy.process(file.getCanonicalFile());
		}
	}
	
	public static void main(String[] args) {
		new ProcessFiles(new ProcessFiles.Strategy() {
			
			@Override
			public void process(File file) {
				System.out.println(file);
				
			}
		}, "java").start(args);
	}
	
	

}

package zxa.io.dir;


//page526
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;
//测试
public class DirList {
	public static void main(String[] args){
		System.out.println("输入文件目录");
		Scanner scanner = new Scanner(System.in);
		
		String file = scanner.next();
		File path = new File(file);
		String[] list;
		System.out.println("输入正则表达式搜索");
		String pa = scanner.next();
		if(pa.length() == 0){
			list = path.list();
		}else{
			//实现文件匹配规则
			list = path.list(new DirFilter(pa));
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

class DirFilter implements FilenameFilter{
	private Pattern pattern;
	
	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File arg0, String arg1) {
		return pattern.matcher(arg1).matches();
	}
	
}




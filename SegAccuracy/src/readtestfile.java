import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class readtestfile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String testfilepath="E:\\c#\\HMM中文分词 - 正确\\2016-05-31(第14讲)\\pku_test.txt";
		String resultfile = testfilepath + ".分词后.txt";				       
	     try 
		 {
				String encoding2="UTF-8";
				File file2=new File(testfilepath);
				FileWriter sw=new FileWriter(resultfile,true);
				if(file2.isFile() && file2.exists())
				{ 
					InputStreamReader read2 = new InputStreamReader(
							new FileInputStream(file2),encoding2);//考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read2);
					String lineTxt = null;
					String content=null;
					while((lineTxt = bufferedReader.readLine()) != null)
					{
						content =lineTxt+"\r\n";						
		                sw.write(content);		                
					}
					read2.close();
					sw.close();					
					System.out.println("测试语料分词完毕并已成功保存！");
				}
				else
				{
					System.out.println("找不到指定的文件");
				}
			} 
			catch (Exception e) 		
	        {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

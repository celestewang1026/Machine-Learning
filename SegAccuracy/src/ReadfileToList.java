import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadfileToList
{
	String FilePath;

	public ReadfileToList(String filePath) 
	{
		super();
		FilePath = filePath;
	}
    
	public List<String> TextTOList()
	{
		List<String> Wordlist=new ArrayList<String>(); 
        try 
        {
                String encoding="UTF-8";
                File file=new File(FilePath);
                if(file.isFile() && file.exists())
                { 
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);
                    BufferedReader bufferedReader = new BufferedReader(read);
                   
                    String lineTxt = null;
                    while((lineTxt = bufferedReader.readLine()) != null)
                    {                   	                   	
                    	String line=lineTxt.trim();          
                    	String arrays[] = line.split(" |\t");     
                    	 if (arrays.length > 0 && arrays[0].contains("/") && arrays[1].contains("/"))
                         {
                             for(String single: arrays)
                             {
                                 String[] words = single.split("/");
                                 Wordlist.add(words[0]);
                             }
                         }
                    	                    	                   
                    	 else
                    	{
                    		 for(String Item:arrays) 
                    			if(!Item.equals(""))
                    		    Wordlist.add(Item);  
                    	}
                    }                    
                    read.close();     
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
      return Wordlist; 
	}
    
}

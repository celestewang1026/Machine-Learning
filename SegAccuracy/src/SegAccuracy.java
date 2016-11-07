import java.util.ArrayList;
import java.util.List;

public class SegAccuracy 
{

	static List<String> goldlist=new ArrayList<String>();//标准文件单词列表
	static List<String> testlist=new ArrayList<String>();//测试文件单词列表
	
	public static void main(String[] args) 
	{
		// 1.读入标准文件及利用分词程序分好词的文件  两个文件对比得出分词准确率
		String goldfilepath="E:\\MyEclipse\\HMMseg\\训练语料与测试语料\\pku_test_gold.txt";
        String testfilepath="E:\\MyEclipse\\HMMseg\\训练语料与测试语料\\pku_test.txt.分词后.txt";
		
        //返回标准文件单词列表
        ReadfileToList gold=new ReadfileToList(goldfilepath);
        goldlist=gold.TextTOList();
        
        //返回测试文件单词列表
        ReadfileToList test=new ReadfileToList(testfilepath);
        testlist=test.TextTOList();
       
        System.out.println("标准文件中存在的词数:" + goldlist.size() + "\r\n");
        System.out.println("测试文件切分出的词数：" + testlist.size() + "\r\n");
        //2.计算准确率
		CalculationAccuracy(goldlist,testlist);
        
	}

	private static void CalculationAccuracy(List<String> goldlist2, List<String> testlist2) 
	{
		  int i = 0, j = 0, count = 0; //i记录标准串匹配位置，j记录测试串，count记录分词正确数
          try
          {
              while (i < goldlist2.size() - 1 && j < testlist2.size() - 1)
              {

                  while (goldlist2.get(i).toString().equals(testlist2.get(j).toString()) && i < goldlist2.size() - 1 && j < testlist2.size() - 1)
                  {
                      count++; //记录正确分词的个数
                      i++;
                      j++;
                  }
                  //此时standardwords[i] 与testwords[j]不相等，需要一直向后匹配，直到成功
                  //例如标准串“人类  社会  前进  的  航船  就要  驶入  21  世纪  的  新  航程”，待评测串“ 人类  社会  前进  的  航船  就  要驶入21世纪  的  新  航程”
                  //从标准串“就要”即i=5,j=5的位置分词开始错误，i后移到8，j后移到6，都组成“就要驶入21世纪”才结束此轮错误位置匹配，接着再看接下的分词单位是否相同，并计数
                  String s = goldlist2.get(i).toString();
                  String t = testlist2.get(j).toString();
                  if (!s.equals(t))
                  {
                      int k = 0;
                      while (!s.equals(t) && j < testlist2.size() - 1 && i < goldlist2.size() - 1)
                      {

                          if (s.length() > t.length())
                          {
                              j++;
                              t += testlist2.get(j).toString();
                              k++;
                          }
                          if (t.length() > s.length())
                          {
                              i++;
                              s += goldlist2.get(i).toString();
                              k++;
                          }
                        
                      }
                      i++;  //向后匹配成功后，i,j都应该从下一个位置开始即加1
                      j++;
                  }
               
              }
          }
          catch (Exception e)
          {
             System.out.println(e.getMessage());
          }
          
          //最后一个单词比较
          if (goldlist2.get(i).toString().equals(testlist2.get(j).toString()))
              count++;

          //计算并显示 准确率 召回率 综合F值          
          
          System.out.println("测试文件正确切分的词数：" + count + "\r\n");
         
          float a = (float)count / testlist2.size();
          System.out.println("准确率：" + a + "\r\n");
          float b = (float)count /goldlist2.size();
          System.out.println("召回率：" + b + "\r\n");
          float F = (float)a * b * 2 / (a + b);
          System.out.println("综合F值：" + F + "\r\n");

		
	}

	
	
	
	
}

package wordtoVecInput;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class uploadprocessingdata {
	private static String fullWidth2halfWidth(String fullWidthStr) {
        if (null == fullWidthStr || fullWidthStr.length() <= 0) {
            return "";
        }
        char[] charArray = fullWidthStr.toCharArray();
        //对全角字符转换的char数组遍历
        for (int i = 0; i < charArray.length; ++i) {
            int charIntValue = (int) charArray[i];
            //如果符合转换关系,将对应下标之间减掉偏移量65248;如果是空格的话,直接做转换
            if (charIntValue >= 65281 && charIntValue <= 65374) {
                charArray[i] = (char) (charIntValue - 65248);
            } else if (charIntValue == 12288) {
                charArray[i] = (char) 32;
            }
        }
        return new String(charArray);
    }
	int getindex(Vector<String> words, String temp){
		if(temp.matches("\\s+")){
			return 0;
		}
		for(int i=0;i<words.size();i++){
			if(words.get(i).equals(temp)){
				return i;
			}
		}
		return 1;
	}
	public int testvectorwordright(String vector,String word){
		String []strs=vector.split(" ");
		String []strs1=word.split(" ");
		//System.out.println(strs.length+" "+strs.length);
		if(strs.length!=strs1.length){
			return -1;
		}
		return 0;
	}
	public static void main(String[] args) throws IOException {
		
		String path=args[0];
		String path2=args[1];
		String path3=args[2];
		uploadProcessingData.java wor=new uploadProcessingData.java();
	    File word=new File(path3);
		File file = new File(path);
		//File file1 = new File(path1);
		File file2 = new File(path2);
		BufferedReader reader = null;
		//BufferedWriter writer = null;
		BufferedWriter writer1 = null;
		BufferedReader reader1 = null;
		reader1 = new BufferedReader(new FileReader(word));
		Vector <String> words=new Vector<String>();
		String str1="";
		while((str1=reader1.readLine())!=null){
			words.add(str1);
		}
		reader1.close();
		

		
		try {

			reader = new BufferedReader(new FileReader(file));
			//writer = new BufferedWriter(new FileWriter(file1));
			 writer1=new BufferedWriter(new FileWriter(file2));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				String half=wor.fullWidth2halfWidth(tempString);
				half = half.replaceAll("\\s*", "");// this step will delete all white space 
            	String regex = "([A-Za-z] )+|[A-Za-z]+";  
                Pattern pattern = Pattern.compile(regex);  
                Matcher matcher = pattern.matcher(half);  
                String s1 = matcher.replaceAll("e"); 
                String regex1 = "\\d+|(\\d )+";  
                Pattern pattern1 = Pattern.compile(regex1);  
                Matcher matcher1 = pattern1.matcher(s1);  
                String s2 = matcher1.replaceAll("d");
            
				char[] str = s2.toCharArray();
				String vector = "";String strs="";
				for (char temp1 : str) {
					String temp=temp1+"";
					//System.out.println(temp);
					if(temp.equals("")||temp.trim().length()==0){
						
						continue;
					}
					for(int j=0;j<temp.length();j++){
						if((temp.charAt(j)+"").trim().equals("")){continue;}
						int index=wor.getindex(words,temp.charAt(j)+"");
						if(index==1){
							//System.out.println(temp.charAt(j));
						}
							strs+=index+" ";
							
					}
				
				if(strs.trim().length()!=0){
					writer1.append(strs);
					writer1.newLine();
				}
				
				if(wor.testvectorwordright(vector,strs)==-1){
					System.out.println("dddddddddddddddddddddddddddd");
				}
			}
			reader.close();
			writer1.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

}

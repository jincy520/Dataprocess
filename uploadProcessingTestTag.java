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

public class uploadprocessingtestlable {
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
		// TODO Auto-generated method stub
		
		String path=args[0];
		String path1=args[1];
		String path2=args[2];
		uploadProcessingTestTag wor=new uploadProcessingTestTag();
	    File word=new File(path2);
		File file = new File(path);
		File file1 = new File(path1);
		BufferedReader reader = null;
		BufferedWriter writer = null;
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
			writer = new BufferedWriter(new FileWriter(file1));
			 //writer1=new BufferedWriter(new FileWriter(file2));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				String half=wor.fullWidth2halfWidth(tempString);
            	String regex = "([A-Za-z] )+|[A-Za-z]+";  
                Pattern pattern = Pattern.compile(regex);  
                Matcher matcher = pattern.matcher(half);  
                String s1 = matcher.replaceAll("e"); 
                String regex1 = "\\d+|(\\d )+";  
                Pattern pattern1 = Pattern.compile(regex1);  
                Matcher matcher1 = pattern1.matcher(s1);  
                String s2 = matcher1.replaceAll("d");
              
				String[] str = s2.split(" ");
				String vector = "";String strs="";
				for (String temp : str) {
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
					char[] x = temp.trim().toCharArray();
					if (x.length == 1) {
						vector += "1 ";
					} else if (x.length == 2) {
						vector += "2 4 ";
					} else if (x.length > 2) {
						vector += "2 ";
						for (int i1 = 1; i1 < x.length - 1; i1++) {
							vector += "3 ";
						}
						vector += "4 ";
					}
				}
				//System.out.println(strs);
				if(vector.trim().length()!=0){
					writer.append(vector);
					writer.newLine();
				}
				
				if(strs.trim().length()!=0){
					//writer1.append(strs);
					//writer1.newLine();
				}
				if(wor.testvectorwordright(vector,strs)==-1){
					System.out.println("dddddddddddddddddddddddddddd");
				}
			}
			reader.close();
			writer.close();
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

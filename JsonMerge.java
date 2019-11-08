package jsonmerge;
import java.io.File;
import java.io.FileInputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
public class JsonMerge {

    public static void main(String[] args) {
        Scanner is=new Scanner(System.in);
        System.out.println("enter the folder path");
        String folderPath=is.nextLine();
        System.out.println("enter input file base name");
        String inBaseName=is.nextLine();
        System.out.println("enter output file base name");
        String outBaseName=is.nextLine();
        System.out.println("enter max size");
        int maxSize=is.nextInt();
        File file = new File(folderPath);
        File[] files = file.listFiles();
        JSONArray combined = new JSONArray();
        JSONObject res=new JSONObject();
        for(File f: files)
        {
            if(f.getName().substring(0, inBaseName.length()).equals(inBaseName))
            {
                JSONParser parser = new JSONParser();
                try ( Reader reader = new InputStreamReader(new FileInputStream(f),"utf-8"))
                {
                    JSONObject jo = (JSONObject) parser.parse(reader);
                    System.out.println(jo);
                    Set<?> s =  jo.keySet();
                    Iterator<?> i = s.iterator();
                    do{
                        String k = i.next().toString();
                        System.out.println(k);
                        JSONArray ja = (JSONArray) jo.get(k);
                        combined.addAll(ja);
                        res.put(k, combined);
                        System.out.println(res);
                    }while(i.hasNext());
                }
                catch (IOException e) {
                    e.printStackTrace();
                } 
                catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        try
        {
            FileWriter file1 = new FileWriter(folderPath+outBaseName+"1.json");
            file1.write(res.toJSONString());
            file1.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

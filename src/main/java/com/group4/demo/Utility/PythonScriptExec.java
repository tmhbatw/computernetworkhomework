package com.group4.demo.Utility;

import org.python.util.PythonInterpreter;

import java.io.*;
import java.util.Map;

public class PythonScriptExec {

    //测试成功，可以执行
    public String[] execPythonScript(String filename){
        String path=System.getProperty("user.dir")+"/src/main/resources/static/";
        filename=path+filename;
        PythonScriptExec ps=new PythonScriptExec();

        PrintStream oldPrintStream = System.out; //将原来的System.out交给printStream 对象保存
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos)); //设置新的out

        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(filename); //此行用于测试,这一行的输出被新的out截获并保存在bos中(执行这一行时,控制台没有输出内容)
        System.setOut(oldPrintStream);
        String[] line=bos.toString().split("\\r?\\n");
        return line;
    }

    //以下这种方法无法使用
/*    public String execPythonScript(String filename){
        Process proc;
        String result="";
        String path = System.getProperty("user.dir");
        try {
            proc = Runtime.getRuntime().exec(path+"/src/main/resources/static/" + filename);
            BufferedReader br=new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line=null;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
            proc.waitFor();
        }catch(IOException | InterruptedException ex){
            ex.printStackTrace();
        }
        return result;
    }*/
}

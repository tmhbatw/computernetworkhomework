package com.group4.demo.controller;

import com.group4.demo.Utility.PythonScriptExec;
import org.python.util.PythonInterpreter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ConfigController {
    @ResponseBody
    @RequestMapping("/config")
    public Map<String,String> config(){
        String[] res=new PythonScriptExec().execPythonScript("config.py");
        Map<String,String> result=new HashMap<>();
        for(int i=0;i<res.length;i++){
            result.put(i+"",res[i]);
        }
        return result;
    }

}

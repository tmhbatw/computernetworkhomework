package com.group4.demo.controller;

import com.group4.demo.Utility.PythonScriptExec;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class VerifyController {
    @ResponseBody
    @RequestMapping(value="/verify",method= RequestMethod.POST)
    public Map<String,String> config(){
        String[] res=new PythonScriptExec().execPythonScript("verify.py");
        Map<String,String> result=new HashMap<>();
        for(int i=0;i<res.length;i++){
            result.put(i+"",res[i]);
        }
        return result;
    }

    public static void main(String[] args){
        System.out.println(new ConfigController().config());
    }

}

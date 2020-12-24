package com.group4.demo.controller;

import com.group4.demo.Utility.PythonScriptExec;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class VerifyController {
    @ResponseBody
    @RequestMapping("/verify")
    public Map<String,String> verify(){
        String[] res=new PythonScriptExec().execPythonScript("verify.py");
        Map<String,String> result=new HashMap<>();
        result.put("result",res[0]);
        return result;
    }
}

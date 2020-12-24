package com.group4.demo;

import com.group4.demo.Utility.PythonScriptExec;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ComputernetworkApplicationTests {

    //测试执行python脚本的方法
    @Test
    void testPythonScriptExec(){
        PythonScriptExec ps=new PythonScriptExec();
        String[] res=new String[]{"a+b=","1"};
        Assert.assertEquals(res,ps.execPythonScript("test.py"));
    }

    @Test
    void contextLoads() {
    }

}

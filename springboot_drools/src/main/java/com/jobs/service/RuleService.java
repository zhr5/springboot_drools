package com.jobs.service;

import com.jobs.entity.Calculation;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class RuleService {

    //调用Drools规则引擎实现个人所得税计算
    public Calculation calculate(Calculation calculation) throws Exception {
        KieSession session = createKieSessionFromDRL("F:\\Work\\springboot_drools\\springboot_drools\\src\\main\\resources\\rule.drl");
        session.insert(calculation);
        session.fireAllRules();
        session.dispose();
        return calculation;
    }

    // 从指定路径的文件中，读取规则内容，创建 KieSession
    public KieSession createKieSessionFromDRL(String drlFullPath) throws Exception{
        //设置规则所使用的日期格式
        System.setProperty("drools.dateformat", "yyyy-MM-dd");

        //读取规则文件中的内容，实际项目中，可以把规则内容存储到数据库中，或者 web 服务器上，或者 oss 中
        //当前把规则就存放在文件中，在项目运行中，在不重启项目的情况下，可以修改规则内容并立刻生效
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(drlFullPath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }

        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(sb.toString(), ResourceType.DRL);
        return kieHelper.build().newKieSession();
    }
}
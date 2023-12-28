package com.outmao.ebs.common.util;


import com.outmao.ebs.common.exception.BusinessException;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@Component
public class MarkerTemplateParser {

    public static void main(String[] args) {
        MarkerTemplateParser parser=new MarkerTemplateParser();
        Map<String,Object> model=new HashMap<>();
        model.put("id","111111");
        String template="测试---${id}";
        String r=parser.process(null,template,model);
        System.out.println(r);

        String template2="测2222222试---${id}";
        r=parser.process(null,template2,model);
        System.out.println(r);

        template="测试333---${id}";
        r=parser.process(null,template,model);
        System.out.println(r);
    }

    private Configuration configuration;
    private StringTemplateLoader stringTemplateLoader;

    public MarkerTemplateParser(){
        configuration = new Configuration(Configuration.VERSION_2_3_28);
        stringTemplateLoader=new StringTemplateLoader();
        configuration.setTemplateLoader(stringTemplateLoader);
    }


    public String process(String name,String template,Object model){

        if(template==null||template.isEmpty())
            return template;

        if(name==null||name.isEmpty())
            name=template;

        try {

            stringTemplateLoader.putTemplate(name,template);


            String content= FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(name, Locale.CHINA),model);

            return content;
        }catch (Exception e){
            log.error("模板内容转换出错",e);
            throw new BusinessException(e.getMessage());
        }


    }



}

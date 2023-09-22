package com.outmao.ebs.common.util;


import com.outmao.ebs.common.exception.BusinessException;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class FreemarkerTemplateUtil {


    public static String process(String template,Object model){
        if(template==null||template.isEmpty())
            return template;
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setTemplateLoader(new TemplateLoader() {
                @Override
                public Object findTemplateSource(String name) throws IOException {
                    return name;
                }

                @Override
                public long getLastModified(Object templateSource) {
                    return 0;
                }

                @Override
                public Reader getReader(Object templateSource, String encoding) throws IOException {
                    return new StringReader((String)templateSource);
                }

                @Override
                public void closeTemplateSource(Object templateSource) throws IOException {

                }
            });

            String content= FreeMarkerTemplateUtils.processTemplateIntoString(
                    configuration.getTemplate(template),model);
            content=content.replace("_zh_CN","");
            return content;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }

    }

}

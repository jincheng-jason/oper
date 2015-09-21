package com.fh.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lijc on 15/5/7.
 */
public class TemplateUtil {

    /**
     * 输出到输出到文件
     * @param ftlName   ftl文件名
     * @param root		传入的map
     * @param outFile	输出后的文件全部路径
     */
    public static void printFile(String ftlName, Map<String,Object> root, String outFile) throws Exception{
        try {
            File file = new File(outFile);
            if(!file.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
                file.getParentFile().mkdirs();				//不存在就全部创建
            }
            FileWriter out = new FileWriter(file);
            Template template = getTemplate(ftlName);
            template.process(root, out);					//模版输出
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过文件名加载模版
     * @param ftlName
     */
    public static Template getTemplate(String ftlName) throws Exception{
        try {
            Configuration cfg = new Configuration();  												//通过Freemaker的Configuration读取相应的ftl
            cfg.setEncoding(Locale.CHINA, "utf-8");
            int p = ftlName.lastIndexOf("/");

            String directory = ftlName.substring(0,p+1);
            String fileName = ftlName.substring(p+1);
            cfg.setDirectoryForTemplateLoading(new File(directory));		//设定去哪里读取相应的ftl模板文件

            Template temp = cfg.getTemplate(fileName);												//在模板文件目录中找到名称为name的文件
            return temp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

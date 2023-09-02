package cn.ft.ckn.fastmapper.util;

import cn.ft.ckn.fastmapper.bean.GenerateTemplateConfig;
import cn.ft.ckn.fastmapper.bean.TableInfo;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ckn
 * @date 2021/10/18
 */
public class GenerateTemplate {
    private static final String TEMPLATE_PATH = "src/main/resources/template";

    public static void generate(GenerateTemplateConfig config) {
        try {
            Set<String> createTables = config.getCreateTables();
            Connection connection = DbUtil.getInstance().getConnection(config);
            List<TableInfo> tableInfos = DbUtil.getInstance().getAllTables(config, connection.getMetaData(), createTables);
            if (CollUtil.isNotEmpty(tableInfos)) {
                for (TableInfo info : tableInfos) {
                    createTemplate(info, config.getReplace());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTemplate(TableInfo info, boolean replaceFile) {
        //开始生成文件
        // step1 创建freeMarker配置实例
        String[] templateFiles = {"Pojo.ftl", "PojoAction.ftl", "PojoMapper.ftl"};
        for (String fileName : templateFiles) {
            Configuration configuration = new Configuration(Configuration.getVersion());
            Writer out = null;
            try {
                // step2 获取模版路径
                configuration.setClassLoaderForTemplateLoading(GenerateTemplate.class.getClassLoader(), "/template");
                configuration.setDefaultEncoding("UTF-8");
                configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
                // step3 创建数据模型
                info.setPojoName(info.getPojoName());
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("table", info);
                // step4 加载模版文件
                Template template = configuration.getTemplate(fileName);
                // step5 生成数据
                String filePath = null;
                switch (fileName) {
                    case "Pojo.ftl":
                        filePath = info.getPojoFilePath();
                        break;
                    case "PojoAction.ftl":
                        filePath = info.getPojoActionFilePath();
                        break;
                    case "PojoMapper.ftl":
                        filePath = info.getPojoMapperFilePath();
                        break;
                    default:
                        break;
                }
                if (StrUtil.isBlank(filePath)) {
                    return;
                }
                File file = new File(filePath);
                if (!file.exists()) {
                    //父级目录不存在则自动创建
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                    // step6 输出文件
                    template.process(dataMap, out);
                } else {
                    if (replaceFile) {
                        file.delete();
                        file.createNewFile();
                        out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                        // step6 输出文件
                        template.process(dataMap, out);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (null != out) {
                        out.flush();
                        out.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

        }
    }
}

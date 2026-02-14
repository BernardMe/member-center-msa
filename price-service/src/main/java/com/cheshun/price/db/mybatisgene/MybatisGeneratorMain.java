//package com.cheshun.price.db.mybatisgene;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.Configuration;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//public class MybatisGeneratorMain {
//    public static void main(String[] args) throws Exception {
//        List<String> warnings = new ArrayList<>();
//        File configFile = new File("D:\\WS_Spring\\member-center-msa\\price-service\\src\\main\\resources\\generatorConfig.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(configFile);
//        //Configuration config = cp.parseConfiguration(ClassLoader.getSystemResourceAsStream("generatorConfig.xml"));
//        DefaultShellCallback callback = new DefaultShellCallback(true);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
//        for (String warning : warnings) {
//            System.out.println(warning);
//        }
//    }
//}
//
//

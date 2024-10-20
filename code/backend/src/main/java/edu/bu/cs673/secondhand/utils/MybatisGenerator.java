//package edu.bu.cs673.secondhand.utils;
//
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.xml.ConfigurationParser;
//import org.mybatis.generator.exception.InvalidConfigurationException;
//import org.mybatis.generator.exception.XMLParserException;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
///***
// Email: ybinman@bu.edu
// DateTime: 10/5/24-17:36
// *****/
//public class MybatisGenerator {
//
//    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
//        List<String> warnings = new ArrayList<>();
//        boolean overwrite = true;
//
//        File configFile = new File("src/main/resources/generatorConfig.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        org.mybatis.generator.config.Configuration config = cp.parseConfiguration(configFile);
//
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        myBatisGenerator.generate(null);
//
//        for (String warning : warnings) {
//            System.out.println(warning);
//        }
//    }
//}

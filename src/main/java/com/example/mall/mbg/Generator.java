package com.example.mall.mbg;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 用于生产 MBG 的代码
 */
public class Generator {

  public static void main(String[] args) throws Exception {
    // MBG 执行过程中的警告信息
    List<String> warnings = new ArrayList<>();
    // 读取 MBG 配置文件
    InputStream is = Generator.class.getResourceAsStream("/GeneratorConfig.xml");
    ConfigurationParser cp = new ConfigurationParser(warnings);
    Configuration config = cp.parseConfiguration(is);
    // 当生成的代码重复时, 覆盖原代码
    DefaultShellCallback callback = new DefaultShellCallback(true);
    // 创建 MBG
    MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
    // 执行生成代码
    myBatisGenerator.generate(null);
    // 输出警告信息
    for (String warning : warnings) {
      System.out.println(warning);
    }
  }
}

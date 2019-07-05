package com.jason;

import cn.hutool.core.util.NetUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class JasonApplication {

    public static void main(String[] args) {
        SpringApplication.run(JasonApplication.class, args);
        checkServer();
    }

    /**
     * 判断 activemq 服务器是否启动
     */
    public static void checkServer() {
        if(NetUtil.isUsableLocalPort(8161)) {
            JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
            System.exit(1);
        }
    }

}

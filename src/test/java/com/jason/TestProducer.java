package com.jason;

import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.RandomUtil;
import com.aliyuncs.exceptions.ClientException;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.*;
import javax.swing.*;

/**
 * Description:测试生产者，生产100条消息
 *
 * @author jason
 * @date 2019-05-03 22:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProducer {

    //服务地址，端口默认61616
    private static final String url="tcp://127.0.0.1:61616";
    //这次发送的消息名称
    private static final String topicName="queue_style";
    //消费者有可能是多个，为了区分不同的消费者，为其创建随机名称
    private static final String consumerName="consumer-" + RandomUtil.randomString(5);

    /**
     * 测试生产者，生产100条消息
     * @throws ClientException
     * @throws JMSException
     */
    @Test
    public void setProducer() throws ClientException, JMSException {
        //0. 先判断端口是否启动了  Active MQ 服务器
        checkServer();
        //1.创建ConnectionFactory,绑定地址
        ConnectionFactory factory=new ActiveMQConnectionFactory(url);
        //2.创建Connection
        Connection connection= factory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建会话
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建一个目标 (队列类型)
        Destination destination=session.createQueue(topicName);
        //6.创建一个生产者
        MessageProducer producer=session.createProducer(destination);

        for (int i = 0; i < 100; i++) {
            //7.创建消息
            TextMessage textMessage=session.createTextMessage("9999队列消息-"+i);
            //8.发送消息
            producer.send(textMessage);
            System.out.println("发送："+textMessage.getText());
        }
        //7. 关闭连接
        connection.close();
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

    /**
     *订阅者
     * @throws ClientException
     * @throws JMSException
     */
    @Test
    public void setConsumer() throws ClientException, JMSException {
        //0. 先判断端口是否启动了 Active MQ 服务器
        checkServer();
        System.out.printf("%s 消费者启动了。 %n", consumerName);

        //1.创建ConnectiongFactory,绑定地址
        ConnectionFactory factory=new ActiveMQConnectionFactory(url);
        //2.创建Connection
        Connection connection= factory.createConnection();
        //3.启动连接
        connection.start();
        //4.创建会话
        Session session=connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5.创建一个目标 （队列类型）
        Destination destination=session.createQueue(topicName);
        //6.创建一个消费者
        MessageConsumer consumer=session.createConsumer(destination);
        //7.创建一个监听器
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message arg0) {
                // TODO Auto-generated method stub
                TextMessage textMessage=(TextMessage)arg0;
                try {
                    System.out.println(consumerName +" 接收消息："+textMessage.getText());
                } catch (JMSException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });

        //8. 因为不知道什么时候有，所以没法主动关闭，就不关闭了，一直处于监听状态
        //connection.close();
    }

}

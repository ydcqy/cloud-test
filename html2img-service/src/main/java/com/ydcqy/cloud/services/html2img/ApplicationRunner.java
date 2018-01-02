package com.ydcqy.cloud.services.html2img;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@SpringBootApplication
public class ApplicationRunner {

    public static void main(String[] args) throws Exception {
//        SpringApplication.run(ApplicationRunner.class, args);
//        try {
//            InetAddress inetAddress = InetAddress.getLocalHost();
//            inetAddress = InetAddress.getByName("ydcqy.com");
//            log.info(inetAddress.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        log.info("==========Spring Boot cloud-registry启动成功！==========");

        //此方法仅适用于JdK1.6及以上版本
        Desktop.getDesktop().browse(
                new URL("http://google.com/intl/en/").toURI());
        Robot robot = new Robot();
        robot.delay(10000);
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        //最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(2000);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width,
                height));
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
        //保存图片
        ImageIO.write(bi, "jpg", new File("E:/html2img.jpg"));
        log.info("完成");
    }
}

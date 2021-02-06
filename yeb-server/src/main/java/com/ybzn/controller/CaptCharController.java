package com.ybzn.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Hugo
 * @time 2021/1/23
 */
@RestController
@Api(tags = "验证码管理 Controller")
public class CaptCharController {


    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captChar",produces = "image/jpeg")
    public void captChar(HttpServletRequest request, HttpServletResponse response){
        // 定义response输出类型为image/jpeg类型 固定配置
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        //-------------------生成验证码 begin --------------------------
        String text = defaultKaptcha.createText();
        System.out.println("验证码+  "+text);
        // 将验证码 数据放入session
        request.getSession().setAttribute("captChar", text);

        //生成图片
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
             outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg",outputStream );
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {if(outputStream!=null){
                outputStream.close();
            }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //-------------------生成验证码 end ----------------------------

    }

}

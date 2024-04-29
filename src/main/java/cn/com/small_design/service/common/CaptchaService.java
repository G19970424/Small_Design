package cn.com.small_design.service.common;

import cn.com.small_design.common.exception.BusinessException;
import cn.com.small_design.common.utils.RedisUtil;
import cn.com.small_design.handler.enums.GlobalExceptionEnums;
import cn.com.small_design.service.common.vo.CaptchaVO;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author gejj
 * @create 2024年04月23日 16:07
 * @version 1.0
 */
@Service
public class CaptchaService {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaService.class);

    @Autowired
    private RedisUtil redisUtils;
    @Autowired
    private DefaultKaptcha producer;

    private final String CAPTCHA_KEY = "captcha:verification:";

    public CaptchaVO cacheCaptcha(){

        // 生成文字验证码
        String content = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(content);

        outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            logger.error("验证码生成失败"+e.getMessage());
            throw new BusinessException(GlobalExceptionEnums.CAPTCHA_GENERATION_FAIL);
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray()).replace("\n", "").replace("\r", "");

        //生成一个随机标识符
        String captchaKey = UUID.randomUUID().toString();

        //缓存验证码
        redisUtils.setCacheObject(CAPTCHA_KEY.concat(captchaKey),content);

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaKey(captchaKey);
        captchaVO.setExpire(3L);
        captchaVO.setBase64Img(base64Img);
        return captchaVO;
    }
}

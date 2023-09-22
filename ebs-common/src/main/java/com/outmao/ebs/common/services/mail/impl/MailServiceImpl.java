package com.outmao.ebs.common.services.mail.impl;



import com.outmao.ebs.common.exception.BusinessException;
import com.outmao.ebs.common.services.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Component
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    String from;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String from, String[] to, String subject, String text) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from==null?this.from:from);
            mailMessage.setTo(to);
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            mailSender.send(mailMessage);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("发送邮件失败",e.getMessage());
        }
    }

    @Override
    public void sendHtmlMail(String from, String[] to, String subject, String text, Map<String, String> attachmentMap) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            //是否发送的邮件是富文本（附件，图片，html等）
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setFrom(from==null?this.from:from);
            messageHelper.setTo(to);

            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);//重点，默认为false，显示原始html代码，无效果

            if(attachmentMap != null){
                attachmentMap.entrySet().stream().forEach(entrySet -> {
                    File file = new File(entrySet.getValue());
                    if(file.exists()){
                        try {
                            messageHelper.addAttachment(entrySet.getKey(), new FileSystemResource(file));
                        } catch (MessagingException e) {
                            e.printStackTrace();
                            throw new BusinessException("发送邮件失败",e.getMessage());
                        }
                    }
                });
            }

            mailSender.send(mimeMessage);
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("发送邮件失败",e.getMessage());
        }

    }






}

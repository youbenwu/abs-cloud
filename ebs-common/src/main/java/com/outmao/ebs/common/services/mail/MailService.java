package com.outmao.ebs.common.services.mail;

import java.util.Map;

public interface MailService {

    public void sendSimpleMail(String from, String[] to, String subject, String text);

    public void sendHtmlMail(String from, String[] to, String subject, String text, Map<String, String> attachmentMap);




}

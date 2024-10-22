package com.autohome.frostmourne.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.autohome.frostmourne.common.contract.MailConfig;

public class EmailHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailHelper.class);

    public static boolean send(MailConfig mailConfig, List<String> to, String subject, String content, String contentType, List<MimeBodyPart> attachments) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", mailConfig.getSmtpHost());
        properties.put("mail.smtp.port", mailConfig.getSmtpPort());
        properties.put("mail.smtp.auth", mailConfig.getSmtpAuth());
        properties.put("mail.smtp.timeout", "10000");
        properties.put("mail.smtp.connectiontimeout", "2000");
        properties.setProperty("mail.user", mailConfig.getSender());
        properties.setProperty("mail.password", mailConfig.getSenderPassword());
        Authenticator authenticator = null;
        if ("true".equalsIgnoreCase(mailConfig.getSmtpAuth())) {
            authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailConfig.getSender(), mailConfig.getSenderPassword());
                }
            };
        }
        if ("true".equalsIgnoreCase(mailConfig.getSslEnable())) {
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.socketFactory.port", "465");
            try {
                MailSSLSocketFactory mailSSLSocketFactory = new MailSSLSocketFactory();
                mailSSLSocketFactory.setTrustAllHosts(true);
                properties.put("mail.smtp.socketFactory.class", mailSSLSocketFactory);
            } catch (GeneralSecurityException ex) {
                throw new RuntimeException(ex);
            }
        }
        Session session = Session.getDefaultInstance(properties, authenticator);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mailConfig.getSender()));
            InternetAddress[] toInternetAddressList = new InternetAddress[to.size()];
            for (int i = 0; i < to.size(); i++) {
                toInternetAddressList[i] = new InternetAddress(to.get(i));
            }
            message.addRecipients(Message.RecipientType.TO, toInternetAddressList);
            message.setSubject(subject, "UTF-8");

            Multipart multipart = new MimeMultipart();
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, contentType);
            multipart.addBodyPart(contentPart);

            if (attachments != null && attachments.size() > 0) {
                for (MimeBodyPart attachBodyPart : attachments) {
                    multipart.addBodyPart(attachBodyPart);
                }
            }
            message.setContent(multipart);
            message.saveChanges();
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            LOGGER.error("error when send email.", ex);
            return false;
        }
    }

    /**
     * 发送邮件
     *
     * @param smtpHost smtp服务地址
     * @param smtpPort smtp服务端口
     * @param sender 发送人邮箱地址
     * @param senderPassword 发送人邮箱密码
     * @param to 接收人邮件地址列表
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param contentType 邮件内容类型(text/html, text/plain)
     * @param attachments 附件
     * @param isTls 是否TLS加密传输
     * @return 发送结果
     */
    public static boolean send(String smtpHost, String smtpPort, String smtpAuth, String sender, String senderPassword, List<String> to, String subject,
        String content, String contentType, List<MimeBodyPart> attachments, String isTls) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.timeout", "10000");
        properties.put("mail.smtp.connectiontimeout", "2000");
        properties.setProperty("mail.user", sender);
        properties.setProperty("mail.password", senderPassword);
        if (Objects.equals("true", isTls)) {
            properties.put("mail.smtp.starttls.enable", "true");
            properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        }
        Authenticator authenticator = null;
        if ("true".equalsIgnoreCase(smtpAuth)) {
            authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sender, senderPassword);
                }
            };
        }
        Session session = Session.getDefaultInstance(properties, authenticator);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(sender));
            InternetAddress[] toInternetAddressList = new InternetAddress[to.size()];
            for (int i = 0; i < to.size(); i++) {
                toInternetAddressList[i] = new InternetAddress(to.get(i));
            }
            message.addRecipients(Message.RecipientType.TO, toInternetAddressList);
            message.setSubject(subject, "UTF-8");

            Multipart multipart = new MimeMultipart();
            MimeBodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(content, contentType);
            multipart.addBodyPart(contentPart);

            if (attachments != null && attachments.size() > 0) {
                for (MimeBodyPart attachBodyPart : attachments) {
                    multipart.addBodyPart(attachBodyPart);
                }
            }
            message.setContent(multipart);
            message.saveChanges();
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            LOGGER.error("error when send email.", ex);
            return false;
        }
    }

    /**
     * 发送html格式的邮件
     *
     * @param smtpHost smtp服务地址
     * @param smtpPort smtp服务端口
     * @param sender 发送人邮箱地址
     * @param senderPassword 发送人邮箱密码
     * @param to 接收人邮件地址列表
     * @param subject 邮件标题
     * @param content 邮件内容
     * @return 发送结果
     */
    public static boolean sendHtml(String smtpHost, String smtpPort, String smtpAuth, String sender, String senderPassword, List<String> to, String subject,
        String content, String isTls) {
        return send(smtpHost, smtpPort, smtpAuth, sender, senderPassword, to, subject, content, "text/html;charset=utf-8", null, isTls);
    }

    /**
     * 发送文本格式邮件
     *
     * @param smtpHost smtp服务地址
     * @param smtpPort smtp服务端口
     * @param sender 发送人邮箱地址
     * @param senderPassword 发送人邮箱密码
     * @param to 接收人邮件地址列表
     * @param subject 邮件标题
     * @param content 邮件内容
     * @return 发送结果
     */
    public static boolean sendText(String smtpHost, String smtpPort, String smtpAuth, String sender, String senderPassword, List<String> to, String subject,
        String content, String isTls) {
        return send(smtpHost, smtpPort, smtpAuth, sender, senderPassword, to, subject, content, "text/plain;charset=utf-8", null, isTls);
    }

    /**
     * 文件对象包装为邮件内容
     *
     * @param file 文件对象
     * @return 邮件内容对象
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static MimeBodyPart wrapFile2BodyPart(File file) throws MessagingException, UnsupportedEncodingException {
        MimeBodyPart filePartBody = new MimeBodyPart();
        DataSource fileDataSource = new FileDataSource(file);
        DataHandler dataHandler = new DataHandler(fileDataSource);
        filePartBody.setDataHandler(dataHandler);
        filePartBody.setFileName(MimeUtility.encodeText(file.getName()));
        return filePartBody;
    }

    /**
     * 将文件路径对应的文件包装成邮件内容
     *
     * @param fileName 文件路径
     * @return 邮件内容对象
     * @throws MessagingException
     */
    public static MimeBodyPart wrapFilename2BodyPart(String fileName) throws MessagingException, UnsupportedEncodingException {
        File file = new File(fileName);
        return wrapFile2BodyPart(file);
    }
}

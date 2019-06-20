package com.advalent.automation.impl.pages.login;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FlagTerm;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class MFACodeExtractor {
    public static String getCodeFromEmail(String user,
                               String password) throws MessagingException {
        Folder emailFolder = null;
        Store store = null;
        String code = null;
        try {

            //create properties field
            Properties props = getOutLookProperty();
            Session emailSession = Session.getDefaultInstance(props);
            store = emailSession.getStore("imaps");
            store.connect("imap-mail.outlook.com", user, password);
            emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);
            Flags seen = new Flags(Flags.Flag.SEEN);
            FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
            Message[] messages = emailFolder.search(unseenFlagTerm);
            Message message = Arrays.stream(messages).filter(m -> {
                try {

                    m.setFlag(Flags.Flag.SEEN, true);
                    return m.getSubject().contains("Welcome to SubroPoint");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            }).findFirst().get();
            code = writePart(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emailFolder.close(false);
            store.close();
        }
        return code;
    }

    private static Properties getOutLookProperty() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imaps.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.imaps.socketFactory.fallback", "false");
        props.setProperty("mail.imaps.port", "993");
        props.setProperty("mail.imaps.socketFactory.port", "993");
        return props;
    }


    public static String writePart(Part p) throws Exception {
        String code = null;
        if (p.isMimeType("text/plain")) {
            System.out.println("This is plain text");
            System.out.println("---------------------------");
            code = ((String) p.getContent()).split("Your verification code is:")[1].trim();
        }
        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
            System.out.println("This is a Multipart");
            System.out.println("---------------------------");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i));
        } else {
            Object o = p.getContent();
            if (o instanceof String) {
                System.out.println("This is a string");
                System.out.println("---------------------------");
                System.out.println((String) o);
            } else if (o instanceof InputStream) {
                System.out.println("This is just an input stream");
                System.out.println("---------------------------");
                InputStream is = (InputStream) o;
                is = (InputStream) o;
                int c;
                while ((c = is.read()) != -1)
                    System.out.write(c);
            } else {
                System.out.println("This is an unknown type");
                System.out.println("---------------------------");
                System.out.println(o.toString());
            }
        }
        return code;
    }

    /*
     * This method would print FROM,TO and SUBJECT buildDataGridAs the message
     */
    public static void writeEnvelope(Message m) throws Exception {
        System.out.println("This is the message envelope");
        System.out.println("---------------------------");
        Address[] a;

        // FROM
        if ((a = m.getFrom()) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("FROM: " + a[j].toString());
        }

        // TO
        if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
            for (int j = 0; j < a.length; j++)
                System.out.println("TO: " + a[j].toString());
        }

        // SUBJECT
        if (m.getSubject() != null)
            System.out.println("SUBJECT: " + m.getSubject());

    }

}

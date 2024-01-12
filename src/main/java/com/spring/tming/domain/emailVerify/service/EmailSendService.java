package com.spring.tming.domain.emailVerify.service;

import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService {
    @Autowired private JavaMailSender mailSender;
    private int authNumber;

    // 임의의 6자리 양수를 반환합니다.
    public void makeRandomNumber() {
        Random r = new Random();
        StringBuilder randomNumber = new StringBuilder(); // 문자열 연산에 적합한 StringBuilder를 사용합니다.
        for (int i = 0; i < 6; i++) {
            randomNumber.append(r.nextInt(10)); // 문자열 연결 대신 StringBuilder를 사용하여 효율성을 높입니다.
        }

        authNumber = Integer.parseInt(randomNumber.toString());
    }

    // 이메일을 어디서 보내는지, 어디로 보내는지, 인증 번호를 HTML 형식으로 어떻게 보내는지 작성합니다.
    public String joinEmail(String email) {
        makeRandomNumber();
        String setFrom = "jangd6995@gmail.com"; // email-config에 설정한, 인증 메일 발송용 이메일 주소.
        String toMail = "이메일 주소를 입력해주세요.";
        String title = "회원가입인증 이메일 입니다."; // 이메일 제목
        String content =
                "Tming 서비스를 이용해해셔서 감사합니다."
                        + "<br><br>"
                        + "인증번호는 "
                        + authNumber
                        + "입니다."
                        + "<br>"
                        + "인증번호를 정확히 입력해주세요"; // 이메일 내용 삽입
        mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }

    // 이메일을 전송합니다.
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message =
                mailSender.createMimeMessage(); // JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성.
        try {
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "utf-8"); // 이메일 메시지와 관련된 설정을 수행.
            // true를 전달하여 multipart 형식의 메시지를 지원하고, "utf-8"을 전달하여 문자 인코딩을 설정.
            helper.setFrom(setFrom); // 이메일의 발신자 주소 설정
            helper.setTo(toMail); // 이메일의 수신자 주소 설정
            helper.setSubject(title); // 이메일의 제목을 설정
            helper.setText(content, true); // 이메일의 내용 설정. 두 번째 매개 변수에 true를 설정하여 HTML 형식으로 지정.
            mailSender.send(message);
        } catch (MessagingException e) {
            // 이메일 서버에 연결할 수 없거나, 잘못된 이메일 주소를 사용하거나, 인증 오류가 발생하는 등 오류가 있는 경우 MessagingException이 발생.
            e.printStackTrace(); // e.printStackTrace()는 예외를 기본 오류 스트림에 출력하는 메서드.
        }
    }
}

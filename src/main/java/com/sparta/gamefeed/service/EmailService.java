package com.sparta.gamefeed.service;

import com.sparta.gamefeed.dto.EmailRequestDto;
import com.sparta.gamefeed.dto.LoginRequestDto;
import com.sparta.gamefeed.dto.SignupRequestDto;
import com.sparta.gamefeed.entity.Email;
import com.sparta.gamefeed.entity.User;
import com.sparta.gamefeed.repository.EmailRepository;
import com.sparta.gamefeed.repository.UserRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService{

    private final EmailRepository emailRepository;
    private final UserRepository userRepository;

    @Autowired
    JavaMailSender emailSender;

    public String ePw;

    private MimeMessage createMessage(String email)throws Exception{
        ePw = createKey();
        System.out.println("보내는 대상 : "+ email);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email);//보내는 대상
        message.setSubject("게임 피드 홈페이지 인증코드");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요! 게임 피드 홈페이지입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("${AdminMail.id}","GameFeed"));//보내는 사람

        Email signupEmail = new Email(email, ePw);
        emailRepository.save(signupEmail);

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }

    public void sendSimpleMessage(SignupRequestDto requestDto) throws Exception {
        // TODO Auto-generated method stub
        String email = requestDto.getEmail();

        MimeMessage message = createMessage(email);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public void emailCheck(String code) {
        Email email = emailRepository.findByCode(code).orElseThrow(
                () -> new IllegalArgumentException("해당 코드가 맞지 않습니다.")
        );
        User user = userRepository.findByEmail(email.getEmail());
        user.changechecker(true);
        emailRepository.delete(email);
    }
}
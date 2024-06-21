package com.lec.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lec.dto.MemberDTO;
import com.lec.entity.Member;
import com.lec.repository.MemberRepository;
import com.lec.service.MemberService;


@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberRepository memberrepository;

	@Override
	public String join(MemberDTO memberDTO) throws ParseException {
		
		Member member = memberDTO.toEntity();
		
		String userId = generateString();
		Date createDate = getCurrentDate();
		
		member.setUserId(userId);
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		member.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
//		memberrepository.save(member);
		
		return "index"; //회원 가입 완료 후 반환되는 템플릿 명
	}

	@Override
	// 생성일자 구하는 로직
	public Date getCurrentDate() throws ParseException {
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    	String dateString = dateFormat.format(new Date());
	    	return dateFormat.parse(dateString);
	    }

	@Override
	// 회원 아이디 - 영문자, 숫자, 영문자&숫자로 이루어진 10글자 반환 
    public String generateString() {
        Random random = new Random();
        String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        return generateRandomString(source, 10, random);
    }

	@Override
	
	// 회원 아이디 - 실제로 10자의 랜덤한 String 생성
	public String generateRandomString(String source, int length, Random random) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(source.length());
            sb.append(source.charAt(randomIndex));
        }
        return sb.toString();
    }
}

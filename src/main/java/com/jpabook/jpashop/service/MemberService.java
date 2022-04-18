package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    public final MemberRepository memberRepository;


    /*회원가입*/
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검사

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /*전체 회원 조회*/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

/**
 * @RequiredArgsConstructor
 * final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션이다.
 * 의존성 주입 편의성을 높여줌.
 * 스프링 의존성 주입 특징 중에
 * "어떠한 빈에 생성자가 오직 하나만 있고 생성자의 파라미터 타입이 빈으로 등록 가능한 존재라면
 * 이 빈은 @Autowired 없이도 의존성 주입이 가능하다."
 *
 * 단점 setter가 필요없는 필드에 대해서도 강제로 생성하게 됨.
 * */

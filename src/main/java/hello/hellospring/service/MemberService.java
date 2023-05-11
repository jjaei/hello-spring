package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    public Long join(Member member) {
        // 회원 가입
        // 같은 이름의 중복 회원은 허용하지 않는다는 룰 생성
        validateDuplicateMember(member);  // 중복 회원 검증 메서드
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                 .ifPresent(m ->  {
                      throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    public List<Member> findMembers() {
        // 전체 회원 조회
        return memberRepository.findAll();
    }

    public Optional<Member> findeOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
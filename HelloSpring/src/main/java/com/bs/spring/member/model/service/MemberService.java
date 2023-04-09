package com.bs.spring.member.model.service;

import com.bs.spring.member.model.vo.Member;

public interface MemberService {
	
	Member selectMemberById(String userId);
	
	int insertMember(Member m);
	
	int updateMember(Member m);
	
	int deleteMember(String userId);
	
	
}

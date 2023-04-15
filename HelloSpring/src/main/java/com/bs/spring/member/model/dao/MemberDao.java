package com.bs.spring.member.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.member.model.vo.Member;

public interface MemberDao {
	
	Member selectMemberById(SqlSessionTemplate session,String userId);
	
	int insertMember(SqlSessionTemplate session,Member m);
	
	int updateMember(SqlSessionTemplate session,Member m);
	
	int deleteMember(SqlSessionTemplate session,String userId);
	
	List<Member> selectMemberAll(SqlSessionTemplate session);
}




package com.eomjinyoung.ew.dao;

import java.util.List;
import java.util.Map;
import com.eomjinyoung.ew.domain.Member;

public interface MemberDao {
  int insert(Member member);
  Member findByNo(int no);
  Member findByUsernameAndPassword(Map<String,Object> params);
  List<Member> findAll();
  int update(Member member); 
  int delete(int no);
}

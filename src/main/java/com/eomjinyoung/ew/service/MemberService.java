package com.eomjinyoung.ew.service;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.eomjinyoung.ew.dao.MemberDao;
import com.eomjinyoung.ew.domain.Member;

@Service
public class MemberService {
  
  MemberDao memberDao;
  
  public MemberService(MemberDao memberDao) {
    this.memberDao = memberDao;
  }
  
  public void signUp(Member member) {
    memberDao.insert(member);
  }
  
  public Member signIn(Map<String,Object> params) {
    return memberDao.findByUsernameAndPassword(params);
  }
}

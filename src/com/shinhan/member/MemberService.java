package com.shinhan.member;

public class MemberService {
	
	public static int SimpleDelete(MemberDTO mem) {
		return MemberDAO.SimpleDelete(mem);
	}
	
	public static int SimpleUpdate(MemberDTO mem) {
		return MemberDAO.SimpleUpdate(mem);
	}
	public static int MemDelete(MemberDTO mem) {
		return MemberDAO.MemDelete(mem);
	}
	
	
	public static int InfoUpdate(MemberDTO mem) {
		return MemberDAO.InfoUpdate(mem);
	}
	
	public int MemberInsert(MemberDTO mem) {
		return MemberDAO.MemberInsert(mem);
	}
	
}

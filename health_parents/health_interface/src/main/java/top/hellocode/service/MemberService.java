package top.hellocode.service;

import top.hellocode.pojo.Member;

import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月28日 10:53
 */
public interface MemberService {
    public Member findByTelephone(String telephone);
    public void add(Member member);
    public List<Integer> findMemberCountByMonth(List<String> month);
}

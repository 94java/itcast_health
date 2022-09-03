package top.hellocode.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.hellocode.dao.MemberDao;
import top.hellocode.pojo.Member;
import top.hellocode.service.MemberService;
import top.hellocode.utils.MD5Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年08月28日 11:02
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;

    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }

    @Override
    public void add(Member member) {
        String password = member.getPassword();
        if(password != null){
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for(String m : month){
            m = m + ".31";//格式：2019.04.31
            Integer count = memberDao.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }
}

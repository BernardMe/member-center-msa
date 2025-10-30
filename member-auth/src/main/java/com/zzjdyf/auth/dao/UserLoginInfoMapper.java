package com.zzjdyf.auth.dao;

import com.zzjdyf.auth.vo.dto.MemberLoginInfo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginInfoMapper {

    @Select("SELECT member_card_no,member_name,phone_number,birthday,gender,openid FROM member_login_info WHERE openid = #{openid}")
    MemberLoginInfo existsByOpenId(String openid);


    @Update("delete from member_login_info where openid = #{openid}")
    void delUserLoginInfo(String openid);

    @Insert("insert into member_login_info(openid,unionid,refresh_token,session_key) values (#{openid},#{unionid},#{refreshToken}," +
            "#{sessionKey})")
    void addUserLoginInfo(MemberLoginInfo memberLoginInfo);

    void editMemberInfo(MemberLoginInfo memberLoginInfo);

}

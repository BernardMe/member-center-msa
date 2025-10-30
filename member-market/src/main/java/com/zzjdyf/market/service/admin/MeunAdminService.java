package com.zzjdyf.market.service.admin;

import com.zzjdyf.market.domain.dao.MeunDao;
import com.zzjdyf.market.domain.entity.ClsMarketEtcMeun;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class MeunAdminService {

    private final MeunDao meunDao;

    public List<ClsMarketEtcMeun> findList(Long roleId){
        return meunDao.findList(roleId);
    }



}

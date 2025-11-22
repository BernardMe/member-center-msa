package com.cheshun.market.service.admin;

import com.cheshun.market.domain.entity.ClsMarketEtcMeun;
import com.cheshun.market.domain.dao.MeunDao;
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

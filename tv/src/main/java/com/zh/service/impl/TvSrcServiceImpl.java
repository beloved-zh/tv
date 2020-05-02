package com.zh.service.impl;

import com.zh.mapper.TvSrcMapper;
import com.zh.pojo.TvSrc;
import com.zh.service.TvSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvSrcServiceImpl implements TvSrcService {

    @Autowired
    private TvSrcMapper mapper;

    @Override
    public List<TvSrc> findBySid(int sid) {
        return mapper.findBySid(sid);
    }
}

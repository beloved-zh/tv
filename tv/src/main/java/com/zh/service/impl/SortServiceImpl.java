package com.zh.service.impl;

import com.zh.mapper.SortMapper;
import com.zh.pojo.Sort;
import com.zh.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SortServiceImpl implements SortService {

    @Autowired
    private SortMapper mapper;

    @Override
    public List<Sort> findAllSort() {
        return mapper.findAllSort();
    }
}

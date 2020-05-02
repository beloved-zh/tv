package com.zh.service;

import com.zh.pojo.TvSrc;

import java.util.List;

public interface TvSrcService {

    List<TvSrc> findBySid(int sid);

}

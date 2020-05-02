package com.zh.mapper;

import com.zh.pojo.TvSrc;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TvSrcMapper {

    List<TvSrc> findBySid(int sid);

}

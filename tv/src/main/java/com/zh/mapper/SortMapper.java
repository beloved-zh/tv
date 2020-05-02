package com.zh.mapper;

import com.zh.pojo.Sort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SortMapper {

    List<Sort> findAllSort();

}

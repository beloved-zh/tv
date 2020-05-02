package com.zh.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zh.pojo.Sort;
import com.zh.pojo.TvSrc;
import com.zh.service.SortService;
import com.zh.service.TvSrcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private SortService sortService;
    @Autowired
    private TvSrcService tvSrcService;

    @GetMapping("/sort")
    public List<Sort> findAllSort(){

        List<Sort> list = sortService.findAllSort();

        return list;
    }

    @GetMapping("/tvsrc/{sid}/{pageNum}/{pageSize}")
    public PageInfo findBySid( @PathVariable("sid") int sid,
                               @PathVariable("pageNum") int pageNum,
                               @PathVariable("pageSize") int pageSize
    ){

        PageHelper.startPage(pageNum, pageSize);
        List<TvSrc> list = tvSrcService.findBySid(sid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}

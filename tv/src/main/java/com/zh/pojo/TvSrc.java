package com.zh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TvSrc {

    private int tid;
    private String tname;
    private String tsrc;
    private Sort sort;

}

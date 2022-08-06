package com.example.uiplog.entity.cache;

import lombok.Data;

@Data
public class UserDormInfo {
    private Integer dormId;
    private String dormName;
    private Integer bedId;
    private String bedName;
    private String checkTime;//归寝时间
    private String lateTime;//晚归时间
}

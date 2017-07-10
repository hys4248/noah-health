package com.yhch.bean.origin;

import com.yhch.pojo.ResultOrigin;
import org.springframework.beans.BeanUtils;

/**
 * 原始数据列表中的一条记录
 * Created by zlren on 17/6/25.
 */
public class ResultOriginExtend extends ResultOrigin {

    public String userName;
    public String checkerName;
    public String uploaderName;

    /**
     * 构造函数
     *
     * @param resultOrigin
     * @param userName
     * @param checkerName
     * @param uploaderName
     */
    public ResultOriginExtend(ResultOrigin resultOrigin, String userName, String checkerName,
                              String uploaderName) {
        BeanUtils.copyProperties(resultOrigin, this);
        this.userName = userName;
        this.checkerName = checkerName;
        this.uploaderName = uploaderName;
    }
}
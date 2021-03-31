package com.ntu.yangyu.dao;

import com.ntu.yangyu.vo.FileQuery;
import com.ntu.yangyu.vo.TimeAndType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileDao {

    void saveFile(@Param("size") String size,@Param("type") String type,
                  @Param("originalName")String originalName,@Param("createTime") String createTime,
                  @Param("path")String path,@Param("UUID")String UUID);

    FileQuery getData(@Param("UUID") String UUID);

    TimeAndType findFilePath(@Param("UUID")String UUID);
}

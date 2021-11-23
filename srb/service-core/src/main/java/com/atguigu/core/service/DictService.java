package com.atguigu.core.service;

import com.atguigu.core.pojo.dto.ExcelDictDTO;
import com.atguigu.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author liwenyang
 * @since 2021-11-02
 */
public interface DictService extends IService<Dict> {

    // 处理导入的excel文件流
    void importData(InputStream inputStream);

    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);

    List<Dict> findByDictCode(String dictCode);

    String getNameByParentDictCodeAndValue(String education, Integer education1);

}

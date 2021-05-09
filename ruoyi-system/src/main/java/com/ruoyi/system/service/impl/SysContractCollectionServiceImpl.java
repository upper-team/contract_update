package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysContractCollectionMapper;
import com.ruoyi.system.domain.SysContractCollection;
import com.ruoyi.system.service.ISysContractCollectionService;
import com.ruoyi.common.core.text.Convert;

/**
 * 收款合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-03
 */
@Service
public class SysContractCollectionServiceImpl implements ISysContractCollectionService 
{
    @Autowired
    private SysContractCollectionMapper sysContractCollectionMapper;

    /**
     * 查询收款合同
     * 
     * @param contractId 收款合同ID
     * @return 收款合同
     */
    @Override
    public SysContractCollection selectSysContractCollectionById(Long contractId)
    {
        return sysContractCollectionMapper.selectSysContractCollectionById(contractId);
    }

    /**
     * 查询收款合同列表
     * 
     * @param sysContractCollection 收款合同
     * @return 收款合同
     */
    @Override
    public List<SysContractCollection> selectSysContractCollectionList(SysContractCollection sysContractCollection)
    {
        return sysContractCollectionMapper.selectSysContractCollectionList(sysContractCollection);
    }

    /**
     * 新增收款合同
     * 
     * @param sysContractCollection 收款合同
     * @return 结果
     */
    @Override
    public int insertSysContractCollection(SysContractCollection sysContractCollection)
    {
        return sysContractCollectionMapper.insertSysContractCollection(sysContractCollection);
    }

    /**
     * 修改收款合同
     * 
     * @param sysContractCollection 收款合同
     * @return 结果
     */
    @Override
    public int updateSysContractCollection(SysContractCollection sysContractCollection)
    {
        return sysContractCollectionMapper.updateSysContractCollection(sysContractCollection);
    }

    /**
     * 删除收款合同对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysContractCollectionByIds(String ids)
    {
        return sysContractCollectionMapper.deleteSysContractCollectionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收款合同信息
     * 
     * @param contractId 收款合同ID
     * @return 结果
     */
    @Override
    public int deleteSysContractCollectionById(Long contractId)
    {
        return sysContractCollectionMapper.deleteSysContractCollectionById(contractId);
    }
}

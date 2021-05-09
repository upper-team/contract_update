package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysContractCooperativeMapper;
import com.ruoyi.system.domain.SysContractCooperative;
import com.ruoyi.system.service.ISysContractCooperativeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合作合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Service
public class SysContractCooperativeServiceImpl implements ISysContractCooperativeService 
{
    @Autowired
    private SysContractCooperativeMapper sysContractCooperativeMapper;

    /**
     * 查询合作合同
     * 
     * @param contractId 合作合同ID
     * @return 合作合同
     */
    @Override
    public SysContractCooperative selectSysContractCooperativeById(Long contractId)
    {
        return sysContractCooperativeMapper.selectSysContractCooperativeById(contractId);
    }

    /**
     * 查询合作合同列表
     * 
     * @param sysContractCooperative 合作合同
     * @return 合作合同
     */
    @Override
    public List<SysContractCooperative> selectSysContractCooperativeList(SysContractCooperative sysContractCooperative)
    {
        return sysContractCooperativeMapper.selectSysContractCooperativeList(sysContractCooperative);
    }

    /**
     * 新增合作合同
     * 
     * @param sysContractCooperative 合作合同
     * @return 结果
     */
    @Override
    public int insertSysContractCooperative(SysContractCooperative sysContractCooperative)
    {
        return sysContractCooperativeMapper.insertSysContractCooperative(sysContractCooperative);
    }

    /**
     * 修改合作合同
     * 
     * @param sysContractCooperative 合作合同
     * @return 结果
     */
    @Override
    public int updateSysContractCooperative(SysContractCooperative sysContractCooperative)
    {
        return sysContractCooperativeMapper.updateSysContractCooperative(sysContractCooperative);
    }

    /**
     * 删除合作合同对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysContractCooperativeByIds(String ids)
    {
        return sysContractCooperativeMapper.deleteSysContractCooperativeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合作合同信息
     * 
     * @param contractId 合作合同ID
     * @return 结果
     */
    @Override
    public int deleteSysContractCooperativeById(Long contractId)
    {
        return sysContractCooperativeMapper.deleteSysContractCooperativeById(contractId);
    }
}

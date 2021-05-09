package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysContractPaymentMapper;
import com.ruoyi.system.domain.SysContractPayment;
import com.ruoyi.system.service.ISysContractPaymentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 付款合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-09
 */
@Service
public class SysContractPaymentServiceImpl implements ISysContractPaymentService 
{
    @Autowired
    private SysContractPaymentMapper sysContractPaymentMapper;

    /**
     * 查询付款合同
     * 
     * @param contractId 付款合同ID
     * @return 付款合同
     */
    @Override
    public SysContractPayment selectSysContractPaymentById(Long contractId)
    {
        return sysContractPaymentMapper.selectSysContractPaymentById(contractId);
    }

    /**
     * 查询付款合同列表
     * 
     * @param sysContractPayment 付款合同
     * @return 付款合同
     */
    @Override
    public List<SysContractPayment> selectSysContractPaymentList(SysContractPayment sysContractPayment)
    {
        return sysContractPaymentMapper.selectSysContractPaymentList(sysContractPayment);
    }

    /**
     * 新增付款合同
     * 
     * @param sysContractPayment 付款合同
     * @return 结果
     */
    @Override
    public int insertSysContractPayment(SysContractPayment sysContractPayment)
    {
        return sysContractPaymentMapper.insertSysContractPayment(sysContractPayment);
    }

    /**
     * 修改付款合同
     * 
     * @param sysContractPayment 付款合同
     * @return 结果
     */
    @Override
    public int updateSysContractPayment(SysContractPayment sysContractPayment)
    {
        return sysContractPaymentMapper.updateSysContractPayment(sysContractPayment);
    }

    /**
     * 删除付款合同对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysContractPaymentByIds(String ids)
    {
        return sysContractPaymentMapper.deleteSysContractPaymentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除付款合同信息
     * 
     * @param contractId 付款合同ID
     * @return 结果
     */
    @Override
    public int deleteSysContractPaymentById(Long contractId)
    {
        return sysContractPaymentMapper.deleteSysContractPaymentById(contractId);
    }
}

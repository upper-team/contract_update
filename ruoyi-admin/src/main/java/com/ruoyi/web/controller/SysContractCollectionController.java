package com.ruoyi.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.domain.SysClient;
import com.ruoyi.system.domain.SysEmployee;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.mapper.SysEmployeeMapper;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysContractCollection;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收款合同Controller
 * 
 * @author ruoyi
 * @date 2021-05-03
 */
@Controller
@RequestMapping("/system/contract_collection")
public class SysContractCollectionController extends BaseController
{
    private String prefix = "system/contract_collection";

    @Autowired
    private ISysClientService clientService;


    @Autowired
    private ISysProductService productService;

    @Autowired
    private ISysEmployeeService employeeService;

    @Autowired
    private ISysContractCollectionService sysContractCollectionService;

    @RequiresPermissions("system:contract_collection:view")
    @GetMapping()
    public String contract_collection()
    {
        return prefix + "/contract_collection";
    }

    /**
     * 查询收款合同列表
     */
    @RequiresPermissions("system:contract_collection:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysContractCollection sysContractCollection)
    {
        startPage();
        List<SysContractCollection> list = sysContractCollectionService.selectSysContractCollectionList(sysContractCollection);
        return getDataTable(list);
    }

    /**
     * 导出收款合同列表
     */
    @RequiresPermissions("system:contract_collection:export")
    @Log(title = "收款合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysContractCollection sysContractCollection)
    {
        List<SysContractCollection> list = sysContractCollectionService.selectSysContractCollectionList(sysContractCollection);
        ExcelUtil<SysContractCollection> util = new ExcelUtil<SysContractCollection>(SysContractCollection.class);
        return util.exportExcel(list, "contract_collection");
    }

    /**
     * 新增收款合同
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<SysProduct> productList = productService.selectSysProductAll();
        List<SysClient> clientList = clientService.selectSysClientAll();
        List<SysEmployee> employeeList = employeeService.selectSysEmployeeAll();
        mmap.put("clients", clientList);
        mmap.put("products", productList);
        mmap.put("employees", employeeList);
        return prefix + "/add";
    }

    /**
     * 新增保存收款合同
     */
    @RequiresPermissions("system:contract_collection:add")
    @Log(title = "收款合同", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysContractCollection sysContractCollection)
    {
        return toAjax(sysContractCollectionService.insertSysContractCollection(sysContractCollection));
    }

    /**
     * 修改收款合同
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {
        SysContractCollection sysContractCollection = sysContractCollectionService.selectSysContractCollectionById(contractId);
        mmap.put("sysContractCollection", sysContractCollection);
        List<SysProduct> productList = productService.selectSysProductAll();
        List<SysClient> clientList = clientService.selectSysClientAll();
        List<SysEmployee> employeeList = employeeService.selectSysEmployeeAll();
        mmap.put("clients", clientList);
        mmap.put("products", productList);
        mmap.put("employees", employeeList);

        return prefix + "/edit";
    }

    /**
     * 修改保存收款合同
     */
    @RequiresPermissions("system:contract_collection:edit")
    @Log(title = "收款合同", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysContractCollection sysContractCollection)
    {
        return toAjax(sysContractCollectionService.updateSysContractCollection(sysContractCollection));
    }

    /**
     * 删除收款合同
     */
    @RequiresPermissions("system:contract_collection:remove")
    @Log(title = "收款合同", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysContractCollectionService.deleteSysContractCollectionByIds(ids));
    }
}

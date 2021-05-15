package com.ruoyi.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.SysClient;
import com.ruoyi.system.domain.SysEmployee;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.service.ISysClientService;
import com.ruoyi.system.service.ISysEmployeeService;
import com.ruoyi.system.service.ISysProductService;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysContractCooperative;
import com.ruoyi.system.service.ISysContractCooperativeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 合作合同Controller
 * 
 * @author ruoyi
 * @date 2021-05-04
 */
@Controller
@RequestMapping("/system/cooperative")
public class SysContractCooperativeController extends BaseController
{
    private String prefix = "system/cooperative";

    @Autowired
    private ISysProductService productService;

    @Autowired
    private ISysEmployeeService employeeService;

    @Autowired
    private ISysContractCooperativeService sysContractCooperativeService;

    @RequiresPermissions("system:cooperative:view")
    @GetMapping()
    public String cooperative()
    {
        return prefix + "/cooperative";
    }

    /**
     * 查询合作合同列表
     */
    @RequiresPermissions("system:cooperative:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysContractCooperative sysContractCooperative)
    {
        startPage();
        List<SysContractCooperative> list = sysContractCooperativeService.selectSysContractCooperativeList(sysContractCooperative);
        return getDataTable(list);
    }

    /**
     * 导出合作合同列表
     */
    @RequiresPermissions("system:cooperative:export")
    @Log(title = "合作合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysContractCooperative sysContractCooperative)
    {
        List<SysContractCooperative> list = sysContractCooperativeService.selectSysContractCooperativeList(sysContractCooperative);
        ExcelUtil<SysContractCooperative> util = new ExcelUtil<SysContractCooperative>(SysContractCooperative.class);
        return util.exportExcel(list, "cooperative");
    }

    @RequiresPermissions("system:cooperative:addFile")
    @PostMapping("/addFile")
    @ResponseBody
    public AjaxResult insert(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("file") MultipartFile[] file) throws Exception {

        String id = request.getParameter("id");//当需对
        logger.info("获取选中数据主键：{}", id);

        if (file != null && file.length > 0) {
            List<String> fileName = new ArrayList<String>();
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            try {
                for (int i = 0; i < file.length; i++) {
                    if (!file[i].isEmpty()) {
                        //上传文件
                        fileName.add(uploadImage(request, filePath, file[i], false));
                    }
                }
                //上传成功
                if (fileName != null && fileName.size() > 0) {
                    return AjaxResult.success("上传成功！");
                } else {
                    return AjaxResult.error("上传失败！文件格式错误！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.error("上传出现异常！");
            }
        } else {
            return AjaxResult.error("未检测到文件！");
        }
    }

    /**
     * 上传文件
     * 原名称
     *
     * @param request      请求
     * @param path_deposit 存放位置(路径)
     * @param file         文件
     * @param isRandomName 是否随机名称
     * @return 完整文件路径
     */
    public String uploadImage(HttpServletRequest request, String path_deposit, MultipartFile file, boolean isRandomName) {
        try {
            if (file != null) {
                String origName = file.getOriginalFilename();// 文件原名称
                System.out.println("上传的文件原名称:" + origName);
                //路径+文件名称
                String fileSrc = "";
                //是否随机名称
//                if (isRandomName) {
//                    origName = DateUtil.today() + UUID.randomUUID().toString() + origName.substring(origName.lastIndexOf("."));
//                }
                // 上传并返回新文件名称
                String fileNameNew = FileUploadUtils.upload(path_deposit, file);
                logger.info("新文件名称：{}", fileNameNew);
                //判断是否存在目录
//                        File targetFile = new File(path, origName);
//                        if (!targetFile.exists()) {
//                            targetFile.mkdirs();//创建目录
//                        }
//                        //上传
//                        file.transferTo(targetFile);
                //完整路径
                fileSrc = request.getContextPath() + path_deposit + origName;
                logger.info("图片上传路径:{}", fileSrc);
                return fileSrc;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新增合作合同
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<SysProduct> list = productService.selectSysProductAll();
        mmap.put("products", list);
        List<SysEmployee> list_e = employeeService.selectSysEmployeeAll();
        mmap.put("employees", list_e);
        return prefix + "/add";
    }

    /**
     * 新增保存合作合同
     */
    @RequiresPermissions("system:cooperative:add")
    @Log(title = "合作合同", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysContractCooperative sysContractCooperative)
    {
        return toAjax(sysContractCooperativeService.insertSysContractCooperative(sysContractCooperative));
    }

    /**
     * 修改合作合同
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {
        SysContractCooperative sysContractCooperative = sysContractCooperativeService.selectSysContractCooperativeById(contractId);
        mmap.put("sysContractCooperative", sysContractCooperative);
        List<SysProduct> list = productService.selectSysProductAll();
        mmap.put("products", list);
        List<SysEmployee> list_e = employeeService.selectSysEmployeeAll();
        mmap.put("employees", list_e);
        return prefix + "/edit";
    }

    /**
     * 修改保存合作合同
     */
    @RequiresPermissions("system:cooperative:edit")
    @Log(title = "合作合同", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysContractCooperative sysContractCooperative)
    {
        return toAjax(sysContractCooperativeService.updateSysContractCooperative(sysContractCooperative));
    }

    /**
     * 删除合作合同
     */
    @RequiresPermissions("system:cooperative:remove")
    @Log(title = "合作合同", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysContractCooperativeService.deleteSysContractCooperativeByIds(ids));
    }


}

package com.ruoyi.web.controller;

import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件上传&下载
 *
 * @author lxy
 */
@Controller
@RequestMapping("/system/file")
public class FileController {

    private String prefix = "system/file";

    @Autowired
    private ServerConfig serverConfig;

    @GetMapping("/fileDemo")
    public String fileDemo() {
        return prefix + "/fileDemo";
    }

    @GetMapping("/file")
    public String file() {
        return prefix + "/file";
    }

    @RequiresPermissions("system:file:upload")
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception {
        try {
            // 上传文件路径(可自定义)
            String filePath = "D:\\ruoyi";
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @RequiresPermissions("system:file:download")
    @GetMapping("/download")
    public void download(HttpServletResponse response) throws Exception {
        // 下载本地文件
        String fileName = "example.pdf".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("D:/example.pdf");// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

package com.sanxiang.bank.controller.api;

import com.sanxiang.bank.common.enums.ResultCode;
import com.sanxiang.bank.model.vo.ResultVO;
import com.sanxiang.bank.utils.QiniuUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@RestController
@Slf4j
public class ImageController {
    /**
     * 上传图片返回url
     * @param uploadFile 上传的图片文件
     * @return url
     */
    @PostMapping(value = "/image/upload")
    public ResultVO<String> uploadFile(@RequestParam("image") MultipartFile uploadFile) {
        String url = null;
        try {
            //重命名
            String newFilename = QiniuUtils.renamePic(Objects.requireNonNull(uploadFile.getOriginalFilename()));
            url = QiniuUtils.InputStreamUpload((FileInputStream) uploadFile.getInputStream(), newFilename);
            if (url.contains("error")) {
                log.error("图片上传失败, url: [{}]", url);
                return new ResultVO<>(ResultCode.FAILED);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("图片上传成功, url: [{}]",url);
        return new ResultVO<>(url);
    }
}

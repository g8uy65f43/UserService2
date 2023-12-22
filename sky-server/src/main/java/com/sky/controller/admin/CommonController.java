package com.sky.controller.admin;

import com.sky.annotation.AutoFill;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api("共通API")
public class CommonController {





    @PostMapping("upload")
    @ApiOperation("ファールアップロード")
    public Result<String> upload(MultipartFile file) throws IOException {
log.info("ファールアップロード：｛｝",file);
        // Generate a unique file name or use the original file name
        String fileName =  file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString()+extension;

        // Save the file to the server
        String absolutePath = "C:\\develop\\sky-take-out\\sky-server\\src\\main\\resources\\update";
        String filePath =absolutePath + File.separator + fileName;
        // 打印保存路径
          System.out.println(filePath);
          // 保存文件的路径信息
          File saveFile = new File(filePath);
          // 文件保存
        file.transferTo(saveFile);
        return Result.success();
     }

}

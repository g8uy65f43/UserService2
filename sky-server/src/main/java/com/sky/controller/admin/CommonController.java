package com.sky.controller.admin;

import com.sky.annotation.AutoFill;
import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
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


    String tempDir = System.getProperty("java.io.tmpdir");
    String relativePath = "src/update";  // 相对于项目根目录的路径

    @PostMapping("upload")
    @ApiOperation("ファールアップロード")
    public Result<String> upload(MultipartFile file) throws IOException {
        log.info("ファールアップロード：{}", file);

        // Generate a unique file name or use the original file name
        String fileName = file.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + extension;
        File directory = new File("");//设定为当前文件夹
        try{
            System.out.println(directory.getCanonicalPath());//获取标准的路径
            String filePath = directory.getCanonicalPath()+"\\nginx-1.20.2\\html\\sky\\img\\upload\\" + objectName;
            try {
                // 构建文件保存路径

                // 打印保存路径
                System.out.println(filePath);

                // 保存文件的路径信息
                File saveFile = new File(filePath);

                // 文件保存
                file.transferTo(saveFile);
                String avatar = "/images/"+objectName;
                return Result.success(avatar);
            } catch (IOException e) {
                log.error("文件上传失败：{}", e);
            }

        }catch(Exception e){}

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }


}

package cc.ziyi.controller;

import cc.ziyi.pojo.Result;
import cc.ziyi.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {

    @PostMapping("/upload")  // path:/upload; method: POST;  request format:multipart/form-data
    public Result<String> upload(MultipartFile file) throws Exception{
        //Store file contents to local disk
        String originalFilename = file.getOriginalFilename();
        // Ensure that file names are unique to prevent file being covered
        String filename = UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // getInputStream(): Returns an input stream of the file's content.
        String url = AliOssUtil.uploadFile(filename,file.getInputStream());
        return Result.success(url);
    }
}


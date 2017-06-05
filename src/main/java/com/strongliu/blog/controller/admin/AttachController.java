package com.strongliu.blog.controller.admin;

import com.strongliu.blog.constant.ErrorCode;
import com.strongliu.blog.controller.BaseController;
import com.strongliu.blog.dto.ResponseDto;
import com.strongliu.blog.entity.Attach;
import com.strongliu.blog.manager.AttachManager;
import com.strongliu.blog.utility.FileUtil;
import com.strongliu.blog.vo.AttachPageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by liuyuzhe on 2017/5/4.
 */

@Controller
@RequestMapping("/admin/attach")
public class AttachController extends BaseController {

    @Autowired
    private AttachManager attachManager;

    private final static Logger logger = LoggerFactory.getLogger(AttachController.class);

    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageId,
                        @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                        Model model) {
        try {
            AttachPageVo attachPageVo = attachManager.getAttachPageVo(pageId, limit);
            if (!ObjectUtils.isEmpty(attachPageVo)) {
                model.addAttribute(attachPageVo);
            }
        } catch (Exception e) {
            logger.error(e.toString());
            return this.render_500();
        }

        return this.renderAdmin("attach");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDto uploadAttach(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (file.isEmpty()) {
            return new ResponseDto(ErrorCode.ERROR_PARAM_MISS);
        }

        String path = request.getSession().getServletContext().getRealPath("/upload/");
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        String fileSlug = FileUtil.getFileSlug(fileName);

        File filePath = new File(path, fileSlug);
        if (!filePath.getParentFile().exists()) {
            filePath.getParentFile().mkdirs();
        }

        try {
            file.transferTo(filePath); // 保存上传文件到一个目标文件中

            Attach attach = new Attach();
            attach.setSlug(fileSlug);
            attach.setType(fileType);
            attach.setName(fileName);
            attach.setCreate_time(new Date());
            attach.setCreator_id(this.getUserId());
            attachManager.addAttach(attach);

        } catch (IOException e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto<>(ErrorCode.SUCCESS, fileSlug);
    }

    @RequestMapping(value = "/remove", method = {RequestMethod.DELETE, RequestMethod.POST})
    @ResponseBody
    public ResponseDto deleteAttach(@RequestParam Integer attachId, HttpServletRequest request) {
        try {
            Attach attach = attachManager.getAttach(attachId);
            if (ObjectUtils.isEmpty(attach)) {
                return new ResponseDto(ErrorCode.ERROR_RESOURCE_NOT_FOUND);
            }

            attachManager.removeAttach(attachId);
            String fileSlug = attach.getSlug();
            String path = request.getSession().getServletContext().getRealPath("/upload/");
            File filePath = new File(path, fileSlug);
            if (!filePath.delete()) {
                return new ResponseDto(ErrorCode.ERROR_IO_ACCESS_FAILED);
            }

        } catch (Exception e) {
            logger.error(e.toString());
            return new ResponseDto(ErrorCode.ERROR_SERVER_INTERNAL);
        }

        return new ResponseDto(ErrorCode.SUCCESS);
    }

}

package com.xhk.lab.controller;

import com.xhk.lab.common.ProjectException;
import com.xhk.lab.common.aspect.RetFormat;
import com.xhk.lab.common.constant.ErrorCodeMap;
import com.xhk.lab.common.constant.ProjectConstant;
import com.xhk.lab.rmodel.IndexImgModelGetResponse;
import com.xhk.lab.service.FileOperateService;
import com.xhk.lab.service.IndexManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * create by xhk on 2018/4/7
 */
@Controller
public class IndexManageController {
    private static Logger logger = LoggerFactory.getLogger(IndexManageController.class);

    @Autowired
    private IndexManageService indexManageService;

    @Autowired
    private FileOperateService fileOperateService;
    /**
     * 进入首页管理页面，返回前端轮播图片
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "index-manage")
    @RetFormat(isPage = true)
    public String indexManage(ModelMap modelMap){
        String s = toString();
        IndexImgModelGetResponse response = indexManageService.getIndexImg();
        modelMap.addAttribute("list",response.getIndexImgList());
        return "page/index-manage";
    }

    @RequestMapping(value = "img-upload/{type}")
    @ResponseBody
    @RetFormat
    public Object uploadIndexImg(@RequestParam(value="file",required=false) MultipartFile file,
                                 Integer seq, @PathVariable String type) throws Exception {
        if (file == null){
            throw new ProjectException(ErrorCodeMap.FILE_EMPTY_ERROR);
        }
        if ("index".equals(type) &&seq == null){
            throw new ProjectException(ErrorCodeMap.PARAMETER_LACK_ERROR);
        }
        String filePath = ProjectConstant.IMG_SAVE_DIR+File.separator+type+File.separator;
        logger.info(filePath);

        String filename = fileOperateService.saveFile(file, filePath);
        if ("index".equals(type)){
            indexManageService.imgSaveDB(filename,seq);
        }
        Map map = new HashMap();
        map.put("img_url",ProjectConstant.IMG_DIR+"/"+type+"/"+filename);
        return map;
    }
}

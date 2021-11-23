package com.example.demo.controller;


import com.alibaba.fastjson.JSON;
import com.example.demo.commonRequest.responsehandler.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zcc
 * @date 2019/5/14 21:20
 */
@Api(tags = {"上传文件"})
@RestController
@RequestMapping("/upload")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UploadController {

    //实现图片上传   未使用
    @ApiOperation(value = "图片上传")
    @PostMapping(value = "/uploadPicture")
    @ResponseBody
    public void uploadPicture(@RequestParam("file") MultipartFile file) throws IOException {
        String name = uploadFile(file, "picture");
        ResponseUtil.showMessage("200", "上传成功", name);
    }

    //平台端使用微信小程序图片上传
    @ApiOperation(value = "平台人脸识别图片上传")
    @PostMapping(value = "/uploadFace")
    @ResponseBody
    public String uploadFace(@RequestParam("file") MultipartFile file) throws IOException {
        String realPath = "face/" + uploadFile(file, "face");
        return realPath;
    }

    //平台端使用健康证图片上传
    @ApiOperation(value = "平台健康证图片上传")
    @RequestMapping(value = "/uploadCaPicture", method = {RequestMethod.POST})
    @ResponseBody
    public String uploadCaPicture(@RequestParam("file") MultipartFile file) throws IOException {
        String realPath = "caPhoto/" + uploadFile(file, "caPhoto");
        return realPath;
    }
    //微信端微信小程序图片上传
    @ApiOperation(value = "微信端小程序人脸识别图片上传")
    @PostMapping(value = "/uploadMiniFace")
    @ResponseBody
    public String uploadMiniFace(@RequestParam("file") MultipartFile file) throws IOException {
//        String realPath = "face/" + uploadFile(file, "face");

        Map<String, Object> result = new HashMap<>();
        result.put("name", file.getOriginalFilename());
        result.put("url","face/"+ uploadFile(file, "face"));
        result.put("fileFormat", file.getContentType());
        List<Object> resultList = new ArrayList<>();
        resultList.add(result);
        String str = JSON.toJSON(resultList).toString();
        return str;
    }

    //微信端健康证图片上传
    @ApiOperation(value = "微信端健康证图片上传")
    @RequestMapping(value = "/uploadMiniCaPicture", method = {RequestMethod.POST})
    @ResponseBody
    public String uploadMiniCaPicture(@RequestParam("file") MultipartFile file) throws IOException {
//        String realPath = "caPhoto/" + uploadFile(file, "caPhoto");
        Map<String, Object> result = new HashMap<>();
        result.put("name", file.getOriginalFilename());
        result.put("url","caPhoto/"+ uploadFile(file, "caPhoto"));
        result.put("fileFormat", file.getContentType());
        List<Object> resultList = new ArrayList<>();
        resultList.add(result);
        String str = JSON.toJSON(resultList).toString();
        return str;

    }

    //    //实现视频上传
//    @RequestMapping(value = "/uploadvideo/cut",method = {RequestMethod.POST})
//    @ResponseBody
//    public void videoUpload(@RequestParam("file")MultipartFile file) throws IOException{
//        String name = uploadFile(file,"video");
//        ResponseUtil.showMessage("200", "上传成功", name);
//    }
//
//    //实现报告上传
//    @RequestMapping(value = "/uploadReport",method = {RequestMethod.POST} )
//    @ResponseBody
//    public void reportUpload(@RequestParam("file")MultipartFile file) throws IOException {
//        String name = uploadFile(file,"report");
//        ResponseUtil.showMessage("200", "上传成功", name);
//    }
//    // 上传照片
//    @ApiOperation(value = "微信小程序图片上传")
//    @RequestMapping("/upload/picture")
//    public ResultVo uploadPicture(@RequestParam("file") MultipartFile file) throws IOException {
//        Map<String, Object> result = new HashMap<>();
//        Map<String, Object> tempMap = new HashMap<>();
//        Map<String, Object> originObj = new HashMap<>();
//        tempMap.put("data", uploadFile(file, "picture"));
//        Date date = new Date();
//        String uid = UUIDUtil.uuid();
//        result.put("uid", uid);
//        originObj.put("uid", uid);
//        result.put("originFileObj", originObj);
//        result.put("lastModifiedDate", date.toString());
//        result.put("name", file.getOriginalFilename());
//        result.put("response", tempMap);
//        List<Object> resultList = new ArrayList<>();
//        resultList.add(result);
//        String str = JSON.toJSON(resultList).toString();
//        return new ResultVo(JSONUtil.stringify(resultList));
//    }
//
    public String uploadFile(MultipartFile file, String type) throws IOException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentTime = dateFormat.format(now);

        String fileName = file.getOriginalFilename();
        System.out.println("源文件名：" + fileName);

        File filed = new File("upload/" + type + "/" + currentTime);
        if (!filed.exists()) {
            filed.mkdirs();
        }
        String filename = System.currentTimeMillis() + (int) (1 + Math.random() * 1000) + fileName.substring(fileName.lastIndexOf("."));
        file.transferTo(new File(filed.getAbsolutePath(), filename));
        System.out.println(currentTime + "/" + filename);
        return currentTime + "/" + filename;
    }


//    //实现文件下载
//    @RequestMapping(value = "/downloadFile", method=RequestMethod.GET)
//    public void downFileFromServer(HttpServletResponse response, @RequestParam(name = "name") String fileName){
//        //response.setHeader("content-type", "application/octet-stream");
//
//        byte[] buff = new byte[1024];
//        BufferedInputStream bis = null;
//        OutputStream os = null;
//        try {
//            os = response.getOutputStream();
//            bis = new BufferedInputStream(new FileInputStream(new File("upload/report",fileName)));
//            //            bis = new BufferedInputStream(new FileInputStream(new File("upload/report",fileName)));
//            response.setContentType("application/octet-stream");
//            response.setHeader("Content-Disposition", "attachment;filename=" +  java.net.URLEncoder.encode(fileName,"UTF-8"));
//            int i = bis.read(buff);
//            while (i != -1) {
//                os.write(buff, 0, buff.length);
//                os.flush();
//                i = bis.read(buff);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @RequestMapping(value = "/downloadFileEx", method=RequestMethod.GET)
//    public static void downloadExcelModle(HttpServletResponse response,@RequestParam(name = "name") String fileName) {
//        //下载
//        File file = new File("upload/report/"+fileName);//   1.获取要下载的文件的绝对路径
////        File file = new File("upload/report/"+fileName);//   1.获取要下载的文件的绝对路径
////        File file = new File(fileName);//   1.获取要下载的文件的绝对路径
//        String newDname = fileName;     //2.获取要下载的文件名
//        System.out.println(fileName);
//        if (file.exists()) {  //判断文件是否存在
//            response.setHeader("content-type", "application/octet-stream");
//            response.setContentType("application/xlsx");
//            try {
//                response.setHeader("Content-Disposition", "attachment;filename=" + new String(newDname
//                        .getBytes("UTF-8"), "ISO8859-1"));  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件.特别注意，在swagger中会练吗，
//                // 但是其实不会乱码。
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            byte[] buff = new byte[1024];    //5.创建数据缓冲区
//            BufferedInputStream bis = null;
//            OutputStream os = null;
//            OutputStream outputStream = null;
//            try {
//                FileInputStream inputStream = new FileInputStream(file);
//                outputStream = response.getOutputStream();
//                IOUtils.copy(inputStream, outputStream);
//                response.flushBuffer();
//                inputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (outputStream != null) {
//                    try {
//                        outputStream.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        else
//        {
//            System.out.println("1");
//        }
//    }
}

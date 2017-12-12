package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.BasicLogicUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@Api(value="Operation", description = "测试使用api",tags="operations")
@RequestMapping(value = "/operations")
public class OperationController {

    private Logger logger = Logger.getLogger(OperationController.class);
    
    @ApiOperation(value = "生成编号", notes = "生成编号", httpMethod = "GET")
    @RequestMapping(value="/getNo/{type}")
    @ResponseBody
    public String getNo(@PathVariable String type) {
        return  BasicLogicUtils.getApplyNo(type);
    }

    
    @ApiOperation(value = "test", notes = "测试", httpMethod = "GET", produces="application/json;charset=UTF-8,application/xml")
    @RequestMapping(value="/{id}")
    @ResponseBody
    @ApiResponses({
        @ApiResponse(code=400,message="请求参数没填好,badRequest"),
        @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
        @ApiResponse(code=406,message=" 不接受所请求页面的 MIME 类型")
    })
    public Object getNo(HttpServletRequest request,@PathVariable String id,@RequestParam String value) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("value", value);
    	if(request.getHeader("Accept").contains("xml")){
    		Element root = DocumentHelper.createElement(id);
    		Document doucment = DocumentHelper.createDocument(root); 
    		root.addText(value);
    		return doucment.asXML();
            }
	return  jsonObject;
    }
    
    
    @ApiOperation(value = "test", notes = "测试", httpMethod = "GET", produces="application/json;charset=UTF-8")
    @RequestMapping(value="/getJson")
    @ResponseBody
    public Object getJSON() {
    	

//    	{
//    		  "price": 12.5,
//    		  "name": "A green door",
//    		  "checked": false,
//    		  "id": 1,
//    		  "dimensions": {
//    		    "width": 5,
//    		    "height": 10
//    		  },
//    		  "tags": [
//    		    "home",
//    		    "green"
//    		  ],
//    		  "description": {
//    		    "colors": "red",
//    		    "legs": [
//    		      3,
//    		      4
//    		    ]
//    		  }
//    		}
    	
    	String jsonString = "{\"checked\": false,\"dimensions\": {\"width\": 5,\"height\": 10},\"id\": 1,\"name\": \"A green door\",\"price\": 12.5,\"tags\": [\"home\",\"green\"],\"description\": {\"colors\": \"red\",\"legs\": [3,4]}}";
        JSONObject jsonObject = JSON.parseObject(jsonString);
        return  jsonObject;
    }


}

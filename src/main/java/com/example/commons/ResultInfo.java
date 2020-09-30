package com.example.commons;

import com.example.commons.exception.AppException;
import com.example.commons.utils.ObjectUtils;

import javax.security.auth.login.LoginException;
import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @Title:
 * @auther: raohr
 * @Date: 2020/9/30 17:19
 * @param:
 * @Description:
 * @return:
 * @throws:
 */
public class ResultInfo extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;
    public static final int SUCCESS = 200;
    public static final String SUCCESS_MSG = "操作成功！";
    public static final int REQUEST_NON_EXISTENT = 404;
    public static final String REQUEST_NON_EXISTENT_MSG = "请求资源不存在，请核对后操作！";
    public static final int REQUEST_TIME_OUT = 408;
    public static final String REQUEST_TIME_OUT_MSG = "请求超时！";
    public static final int AUTH_ERROR = 403;
    public static final String AUTH_ERROR_MSG = "您无该操作权限，如需操作，请联系相关人员开通。";
    public static final int SYS_ERROR = 500;
    public static final String SYS_ERROR_MSG = "系统错误，请联系管理员！";
    public static final int LOGIN_ERROR = 401;
    public static final String LOGIN_ERROR_MSG = "登录超时，请重新登录！";
    public static final int APP_ERROR = 510;

    public ResultInfo() {
        this.put("resultCode", 200);
        this.put("resultMsg", "操作成功！");
        this.put("data", (Object)null);
    }

    public static ResultInfo success() {
        ResultInfo resultInfo = new ResultInfo();
        return resultInfo;
    }

    public static ResultInfo success(Object data) {
        ResultInfo resultInfo = new ResultInfo();
        if (data != null && !data.equals("null")) {
            resultInfo.put("data", data);
        }

        return resultInfo;
    }

    public static ResultInfo nonExistentError() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 404);
        resultInfo.put("resultMsg", "请求资源不存在，请核对后操作！");
        return resultInfo;
    }

    public static ResultInfo timeOutError() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 408);
        resultInfo.put("resultMsg", "请求超时！");
        return resultInfo;
    }

    public static ResultInfo timeOutError(String message) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 408);
        resultInfo.put("resultMsg", message);
        return resultInfo;
    }

    public static ResultInfo loginTimeOut() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 401);
        resultInfo.put("resultMsg", "登录超时，请重新登录！");
        return resultInfo;
    }

    public static ResultInfo authError() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 403);
        resultInfo.put("resultMsg", "您无该操作权限，如需操作，请联系相关人员开通。");
        return resultInfo;
    }

    public static ResultInfo sysError() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 500);
        resultInfo.put("resultMsg", "系统错误，请联系管理员！");
        return resultInfo;
    }

    public static ResultInfo sysError(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 500);
        resultInfo.put("resultMsg", msg);
        return resultInfo;
    }

    public static ResultInfo appError(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.put("resultCode", 510);
        resultInfo.put("resultMsg", msg);
        return resultInfo;
    }

    public int getResultCode() {
        return (Integer)this.get("resultCode");
    }

    public String getResultMsg() {
        return (String)this.get("resultMsg");
    }

    public <T> T getData() {
        return this.isSuccess() ? this.get("data") : null;
    }

    public <T> T getSuccessData() throws Exception {
        this.successOrThrowException();
        return this.get("data");
    }

    public Map getSuccessMap() throws Exception {
        Object data = this.getSuccessData();
        if (data == null) {
            return null;
        } else {
            return data instanceof Map ? (Map)data : ObjectUtils.objectToMap(data);
        }
    }

    public <T> List<T> getList() {
        Object data = this.getData();
        return data != null ? (List)data : null;
    }

    public <T> List<T> getSuccessList() throws Exception {
        Object data = this.getSuccessData();
        return data == null ? null : (List)data;
    }

    private boolean isSuccess() {
        return 200 == this.getResultCode();
    }

    public void successOrThrowException() throws Exception {
        int resultCode = this.getResultCode();
        if (200 != resultCode) {
            Object exception;
            switch(resultCode) {
                case 401:
                    exception = new LoginException();
                    break;
                case 402:
                case 405:
                case 406:
                case 407:
                default:
                    exception = new AppException(this.getResultMsg());
                    break;
                case 403:
                    exception = new AuthException();
                    break;
                case 404:
                    exception = new AppException("请求资源不存在，请核对后操作！");
                    break;
                case 408:
                    exception = new TimeoutException(this.getResultMsg());
            }

            throw (Exception)exception;
        }
    }
}

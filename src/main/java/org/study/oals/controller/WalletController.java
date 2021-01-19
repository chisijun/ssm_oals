package org.study.oals.controller;

import com.beust.jcommander.internal.Maps;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.OrderDto;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.dto.WalletQueryDto;
import org.study.oals.model.entity.OnlinePayParam;
import org.study.oals.model.vo.AuditVo;
import org.study.oals.model.vo.TaskVo;
import org.study.oals.model.vo.WalletVo;
import org.study.oals.service.WalletService;
import org.study.oals.utils.UtilDate;
import org.study.oals.utils.alipay.AliPayConfig;
import org.study.oals.utils.alipay.AlipayTrade;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:13
 * @Description:
 */
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    /**
     * 教师提现
     *
     * @param login     当前登陆者
     * @param orderDto  提现对象
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
    public JsonResult withdrawal(@CurrentUser User login, OrderDto orderDto) {

        Integer result = walletService.withdrawal(login, orderDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 学生充值
     *
     * @param login     当前登陆者
     * @param orderDto  充值对象
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public JsonResult recharge(@CurrentUser User login, OrderDto orderDto) {

        Integer result = walletService.recharge(login, orderDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 查询个人钱包
     *
     * @param login 当前登录者
     * @param id    钱包id
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/showById/{id}", method = RequestMethod.POST)
    public JsonResult showById(@CurrentUser User login, @PathVariable Long id) {

        WalletVo walletVo = walletService.showById(id);

        return new JsonResult(true, "操作成功", walletVo);
    }

    /**
     * 查询钱包列表 - 分页
     *
     * @param walletQueryDto  查询条件
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/queryListWithPage", method = RequestMethod.POST)
    public JsonResult queryListWithPage(WalletQueryDto walletQueryDto) {

        List<WalletVo> walletVoList = walletService.queryListWithPage(walletQueryDto);

        return new JsonResult(true, "操作成功", new PageInfo<>(walletVoList));

    }

    @RequestMapping(value = "/onlinePay")
    public void onlinePay(HttpServletResponse response, HttpServletRequest request, OnlinePayParam param, BindingResult br)
            throws ServletException, IOException {
        /* 返回上传结果 */
        response.setCharacterEncoding("UTF-8");
        /* 解决文件上传跨域问题 */
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        response.setContentType("text/html;charset=" + AliPayConfig.CHARSET);

        boolean result = true;
        String form = null;

//        TokenModel model = tokenManager.getToken(param.getToken());
//	    if (!tokenManager.checkToken(model)) {
//	        //如果token验证失败
//	        result = false;
//	        errorMap.put("token", "token验证失败");
//	    }

//        if (!result) {
//            /* 返回错误信息 */
//            //JSONObject jsObject =JSONObject.fromObject(new JsonResult(false,"在线支付参数检验失败", errorMap));
//            //form = new String(jsObject.toString().getBytes("ISO-8859-1"),"UTF-8");
//            //form = jsObject.toString();
//        } else {
            String orderId = UtilDate.getOrderNum();
//            DecimalFormat df = new DecimalFormat("#,#00.00#");
            // 只保留两位小数
            BigDecimal totalAount = param.getTotalAmount().setScale(2, BigDecimal.ROUND_DOWN);
//            if (0 == param.getPayType()) {
//                // 银联支付 - 手机控件支付
//                UnionPayTrade unionPayTrade = new UnionPayTrade();
//                Map<String,String> paramMap = new HashMap<String,String>();
//                // 乘以100
//                paramMap.put("txnAmt", totalAount.scaleByPowerOfTen(2).toString());  //交易金额，单位分，不要带小数点
//                paramMap.put("orderId", orderId);
//                Map<String, String> rspData = unionPayTrade.appConsume(paramMap, null);
//                JSONObject jsObject =JSONObject.fromObject(WrapMapper.ok(rspData));
//                // 设置响应头类型
//                response.setContentType("application/json;charset=UTF-8");
//
//                form = new String(jsObject.toString().getBytes("UTF-8"),"UTF-8").toString();
//            } if (1 == param.getPayType()) {
//                // 银联支付 - 网关支付
//                UnionPayTrade unionPayTrade = new UnionPayTrade();
//                Map<String,String> paramMap = new HashMap<String,String>();
//                paramMap.put("txnAmt", totalAount.scaleByPowerOfTen(2).toString());  //交易金额，单位分，不要带小数点
//                paramMap.put("orderId", orderId);
//                // 订单超时时间。
//                // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
//                // 此时间建议取支付时的北京时间加15分钟。
//                // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
//                paramMap.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000));
//                form = unionPayTrade.tokenOpen(paramMap, null);
//            }
            if (2 == param.getPayType()) {
                // 支付宝支付 -- 网关支付
                AlipayTrade alipayTrade = new AlipayTrade();
                Map<String,String> paraMap = new HashMap<String,String>();
                paraMap.put("out_trade_no", orderId);	// 订单号
                //paraMap.put("out_trade_no", System.currentTimeMillis()+"");	// 订单号
                paraMap.put("total_amount", totalAount.toString());	// 充值金额
                paraMap.put("subject","在线充值");		// 名称
                /**
                 *  所有支付接口开始计时都是订单创建开始计时，不同接口对于timeout_express计时方式是不同的。
                 *  取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
                 *  该参数数值不接受小数点， 如 1.5h，可转换为 90m。
                 *  如果不设置该参数默认15天订单关闭
                 */
                paraMap.put("timeout_express", "15m");	// 过期时间
                if (StringUtils.isNotBlank(param.getBody())) {
                    paraMap.put("body", param.getBody());	// 备注
                } else {
                    paraMap.put("body", "暂无付款说明");
                }
                paraMap.put("product_code","QUICK_WAP_PAY");	// 支付方式
                form = alipayTrade.TradeWapPayRequest(paraMap);
//            } if (3 == param.getPayType()) {
//
//                // 支付宝支付 -- app支付
//                AlipayTrade alipayTrade = new AlipayTrade();
//                Map<String,String> paraMap = new HashMap<String,String>();
//                paraMap.put("out_trade_no", orderId);	// 订单号
//                //paraMap.put("out_trade_no", System.currentTimeMillis()+"");	// 订单号
//                paraMap.put("total_amount", totalAount.toString());	// 充值金额
//                paraMap.put("subject","守护佳智能看护系统");		// 名称
//                /**
//                 *  所有支付接口开始计时都是订单创建开始计时，不同接口对于timeout_express计时方式是不同的。
//                 *  取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
//                 *  该参数数值不接受小数点， 如 1.5h，可转换为 90m。
//                 *  如果不设置该参数默认15天订单关闭
//                 */
//                paraMap.put("timeout_express", "15m");	// 过期时间
//                if (StringUtils.isNotBlank(param.getBody())) {
//                    paraMap.put("body", param.getBody());	// 备注
//                } else {
//                    paraMap.put("body", "暂无付款说明");
//                }
//                paraMap.put("product_code","QUICK_MSECURITY_PAY");	// 支付方式
//                form = alipayTrade.TradeAppPayRequest(paraMap);
//
//                Map<String, String> out = Maps.newHashMap();
//                out.put("tn", form);
//
//                JSONObject jsObject =JSONObject.fromObject(WrapMapper.ok(out));
//                // 设置响应头类型
//                response.setContentType("application/json;charset=UTF-8");
//
//                form = new String(jsObject.toString().getBytes("UTF-8"),"UTF-8").toString();
//            }

            // 创建支付工单
//            PayOrder payOrder = new PayOrder();
//            payOrder.setOrderId(orderId);
//            payOrder.setBody(param.getBody());
//            payOrder.setAmount(param.getTotalAmount().toString());
//            //payOrder.setUserId(model.getUserId());
//            payOrder.setForm(form);
//            payOrderService.payOrderCreat(payOrder);
        }

        response.getWriter().write(form); //直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

}

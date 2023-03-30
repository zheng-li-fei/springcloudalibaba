package com.zlf.commonredis.constants;

/**
 * @description: redission 锁的 key常量
 * @author: zhenglifei
 * @create: 2022/3/17 10:30
 **/
public class RedissionConstantKey {

    /**
     * 张家港核销平台-商户核销订单-核销码锁
     */
    public static final String VERIFY_LOCK_VERIFY_CODE_KEY = "java-verify:lock:verify_order:verify_code_%s";
    /**
     * 张家港核销平台-确认收货-物流单号锁
     */
    public static final String VERIFY_LOCK_CONFIRM_RECEIVE_GOODS_KEY = "java-verify:lock:confirm_receive_goods:express_sn_%s";
    /**
     * 张家港核销平台-自动同步物流接口-更新物流信息锁
     */
    //public static final String VERIFY_LOCK_AUTO_SYNC_ORD_LOGISTIC_KEY = "java-verify:lock:auto_sync_logistic_%s";


    /**
     * 1. 自提核销平台-商户核销订单- 核销码锁(核销单-未核销)
     * 2. 自提核销平台-商户撤销核销订单- 反核销锁(核销单-已核销,未结算)
     * 3. 自提核销平台-线下支付单退款-支付单号锁 (线下支付单 - 已支付,未结算)
     * 4. 自提核销平台-订单结算-商城商户锁 (核销订单 - 已核销,未结算)(线下支付单 - 已支付,未结算)
     */
    public static final String VERIFY_MERCHANT_LOCK_SHOP_MERCHANT_ID_KEY = "java-verify-merchant:lock:shop_merchant_id_%s_%s";

    /**
     * 自提核销平台-商城用户扫描二维码支付-商城用户锁
     */
    public static final String VERIFY_MERCHANT_LOCK_SHOP_USER_ID_KEY = "java-verify-merchant:lock:shop_user_id_%s_%s";



    /**
     * 限流接口锁
     */
    //接口访问限流
    public static final String OPEN_LOCK_INTERFACE_LIMIT_KEY = "open:lock:limit:appName_%s:%s_%s";
    //用户访问限流
    public static final String OPEN_LOCK_USER_LIMIT_KEY = "open:lock:limit:appName_%s:%s_%s_userFlag_%s";
    //ip访问限流
    public static final String OPEN_LOCK_IP_LIMIT_KEY = "open:lock:limit:appName_%s:%s_%s_remoteIp_%s";


    /**
     * 领卷中心-立即领取-发放的优惠卷id锁
     */
    public static final String COUPON_LOCK_GET_COUPON_SEND_ID_KEY = "java-shop:lock:coupon:get_coupon_send_id_%s";

    /**
     * 通用商城-下单 商城用户锁
     */
    public static final String CREATE_ORDER_LOCK_SHOP_ID_USER_ID_KEY = "java-shop:lock:createOrder:shopId_userId_%s_%s";

    /**
     * 通用商城-取消订单 订单锁
     */
    public static final String CANCEL_ORDER_LOCK_USER_ID_ORDER_ID_KEY = "java-shop:lock:cancelOrder:orderId_%s";

    /**
     * 大转盘-抽奖-抽奖编码锁锁
     */
    public static final String MKT_ROULETTE_CODE_KEY = "java-shop:lock:roulette:roulette_code_%s";

    /**
     * 大转盘-抽奖-抽奖编码锁锁
     */
    public static final String MKT_ROULETTE_CODE_USER_KEY = "java-shop:lock:roulette:roulette_code_user_%s_%s";


}

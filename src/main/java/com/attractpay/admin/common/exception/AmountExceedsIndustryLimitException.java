package com.attractpay.admin.common.exception;
/**
 * 当支付金额超出行业限额是抛出此异常
 * @author ChenHuiLiang
 * @修改时间 2018年11月1日下午4:22:48
 */
public class AmountExceedsIndustryLimitException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AmountExceedsIndustryLimitException() {
        super();
    }

    public AmountExceedsIndustryLimitException(String message) {
        super(message);
    }

    public AmountExceedsIndustryLimitException(Throwable cause) {
        super(cause);
    }

    public AmountExceedsIndustryLimitException(String message, Throwable cause) {
        super(message, cause);
    }

}

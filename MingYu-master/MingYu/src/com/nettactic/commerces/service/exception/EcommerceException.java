package com.nettactic.commerces.service.exception;

public class EcommerceException extends Exception
{
	/**
	 * 注释内容
	 */
	private static final long serialVersionUID = -6555356073842721747L;

	// 错误码
	private int errCode;

	public EcommerceException(int errCode)
	{
		super();
		this.errCode = errCode;
	}

	public void setErrCode(int errCode)
	{
		this.errCode = errCode;
	}

	public int getErrCode()
	{
		return errCode;
	}

	@Override
	public String toString()
	{
		return "errCode: " + errCode + super.toString();
	}

	/**
	 * 获取异常信息
	 * 
	 * @param ctx
	 * @return [参数说明]
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 * @see [类、类#方法、类#成员]
	 */
	// public String getErrorMessage(Context ctx)
	// {
	// if (null == ctx)
	// {
	// return null;
	// }
	// return ServerConstant.ReturnCode.getMsgByReturnCode(errCode, ctx);
	//
	// }

}

package org.xfs.common.exception;

import org.xfs.common.constant.ErrorCode;

public class SqlException extends BaseException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5383186791371166046L;

	public SqlException(String message) {
		super( ErrorCode.EXECUTE_ERROR_10011,message);
	}

	public SqlException(String module,String message) {
		super( module,ErrorCode.EXECUTE_ERROR_10011,message);
	}


	public SqlException(String module,String message,Object[]args) {
		super( module,ErrorCode.EXECUTE_ERROR_10011,args,message);
	}
	
	
}

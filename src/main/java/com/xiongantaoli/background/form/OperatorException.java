package com.xiongantaoli.background.form;

import com.xiongantaoli.background.model.ResultData;
/**
  * 前后台交互抛出的异常
 * @author 周天
 */

public class OperatorException extends Exception{

		private ResultData resultData;

		public OperatorException(ResultData resultData) {
			super();
			this.resultData = resultData;
		}

		public ResultData getResultData() {
			return resultData;
		}

		public void setResultData(ResultData resultData) {
			this.resultData = resultData;
		}
		
		
}

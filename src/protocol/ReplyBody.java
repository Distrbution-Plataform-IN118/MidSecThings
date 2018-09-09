package protocol;
import java.io.Serializable;
import java.util.ArrayList;


public class ReplyBody implements Serializable{


	private static final long serialVersionUID = 1L;
	private Object operationResult;
	private Object operationResultArray;
	
	public ReplyBody(Object operationResult) {
		this.operationResult = operationResult;
	}

	public ReplyBody(Object[] operationResultArray) {
		this.operationResultArray = operationResultArray;
	}
	
	public Object getOperationResult() {
		return operationResult;
	}

	public Object getOperationResultArray() {
		return operationResultArray;
	}

	
	
	
	
}

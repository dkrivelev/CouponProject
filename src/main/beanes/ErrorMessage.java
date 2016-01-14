package main.beanes;

public class ErrorMessage {
	private String error;

	public ErrorMessage() {
	}

	public ErrorMessage(String error) {
		super();
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorMessage [error=" + error + "]";
	}
}

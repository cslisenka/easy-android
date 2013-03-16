package by.easyandroid.webapp;

public abstract class AbstractBaseDialog {

	private boolean opened;

	public void open() {
		opened = true;
	}
	
	public void close() {
		opened = false;
	}
	
	public boolean isOpened() {
		return opened;
	}
}
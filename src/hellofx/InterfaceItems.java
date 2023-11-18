package hellofx;

public interface InterfaceItems {
	void changeXCoordinate(int newXCoordinate);
	void changeYCoordinate(int newYCoordinate);
	void changeName(String newName);
	void changeWidth(int newWidth);
	void changeHeight(int newHeight);
	ContainerTags getTag();
	String getName();
	String getParentId();
	String getId();
	int getWidth();
	int getHeight();
	int getxCordinate();
	int getyCordinate();
}

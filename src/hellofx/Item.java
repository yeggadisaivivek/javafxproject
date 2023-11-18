package hellofx;

public class Item implements InterfaceItems{
	
	private String id;
	private String parentId;
	private ContainerTags tag = ContainerTags.ITEM;
	private String name;
	private float price;
	private int xCordinate;
	private int yCordinate;
	private int width;
	private int height;
	
	public Item() {
		this.id = Utils.generateUniqueID();
	}
	public Item(String name, int xCordinate, int yCordinate, int width, int height) {
		this.id = Utils.generateUniqueID();
		this.name = name;
		this.xCordinate = xCordinate;
		this.yCordinate = yCordinate;
		this.width = width;
		this.height = height;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	@Override
	public String toString() {
		return name;
	}
	public ContainerTags getTag() {
		return tag;
	}
	public void setTag(ContainerTags tag) {
		this.tag = tag;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getxCordinate() {
		return xCordinate;
	}
	public void setxCordinate(int xCordinate) {
		this.xCordinate = xCordinate;
	}
	public int getyCordinate() {
		return yCordinate;
	}
	public void setyCordinate(int yCordinate) {
		this.yCordinate = yCordinate;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	@Override
	public void changeXCoordinate(int newXCoordinate) {
		// TODO Auto-generated method stub
		this.xCordinate = newXCoordinate;
		
	}
	@Override
	public void changeYCoordinate(int newYCoordinate) {
		// TODO Auto-generated method stub
		this.yCordinate = newYCoordinate;
		
	}
	@Override
	public void changeName(String newName) {
		// TODO Auto-generated method stub
		this.name = newName;
	}
	@Override
	public void changeWidth(int newWidth) {
		// TODO Auto-generated method stub
		this.width = newWidth;
	}
	@Override
	public void changeHeight(int newHeight) {
		// TODO Auto-generated method stub
		this.height = newHeight;
	}
	
	
}

package hellofx;

import java.util.ArrayList;
import java.util.List;

public class CompositeItems implements InterfaceItems{
	private List<InterfaceItems> itemContainers = new ArrayList<InterfaceItems>();
	
	public void addItem(InterfaceItems item) {
		itemContainers.add(item);
	}
	
	public void deleteItem(InterfaceItems item) {
		itemContainers.remove(item);
	}
	
	public List<InterfaceItems> getAll() {
		return itemContainers;
	}

	@Override
	public void changeXCoordinate(int newXCoordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeYCoordinate(int newYCoordinate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeName(String newName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeWidth(int newWidth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeHeight(int newHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContainerTags getTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParentId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getxCordinate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getyCordinate() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
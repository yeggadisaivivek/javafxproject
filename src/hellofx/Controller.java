package hellofx;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

class ListViewItems {
	public InterfaceItems item;
	public String listViewItem;
	
	public ListViewItems() {
		
	}
	
	public ListViewItems(String name, InterfaceItems item) {
		listViewItem = name;
		this.item = item;
	}
	
	public String toString() {
		return listViewItem;
	}
}

public class Controller implements Initializable {
	
	private static Controller instance;
	
	private Controller() {
		
	}
	
	public static Controller getInstance() {
		if(instance == null) {
			instance = new Controller();
		}
		
		return instance;
	}
	
	@FXML
	private VBox leftPanel;

    @FXML
    private TreeView<InterfaceItems> treeView1;
    
    @FXML
    private ListView<ListViewItems> myListView;
    
    @FXML
    private AnchorPane visualizationArea;
    
    @FXML
    private ImageView myImage;
    
    private ImageView drone;
    
    private CompositeItems item;
    
    String currentFood;
    
    private CompositeItems getInitialSetup() {
    	ItemContainer rootItemContainer = new ItemContainer("Root",0,0,0,0,ContainerTags.ROOT);
    	ItemContainer commandCenter = new ItemContainer("Command Center", 300,0,150,150,ContainerTags.ITEM_CONTAINER);
    	commandCenter.setParentId(rootItemContainer.getId());
    	Item droneItem = new Item("Drone", 310,20, 130,120);
    	droneItem.setParentId(commandCenter.getId());
    	item = new CompositeItems();
    	item.addItem(droneItem);
    	item.addItem(rootItemContainer);
    	item.addItem(commandCenter);
    	
    	return item;
    }
    
    public List<StackPane> setVisualPane(CompositeItems items) {
    	//("Items in setVisualPane:::::::"+ items);
    	List<StackPane> sp = new ArrayList<StackPane>();
    	for(InterfaceItems ic : items.getAll()) {
			if (ic.getTag() != ContainerTags.ROOT) {
				StackPane sp1 = new StackPane();
				Label label = new Label(ic.getName());
				Rectangle rectangle = new Rectangle(ic.getWidth(), ic.getHeight());
				sp1.setLayoutX(ic.getxCordinate()); // Set the X coordinate
				sp1.setLayoutY(ic.getyCordinate()); // Set the Y coordinate
				rectangle.setFill(null);
				rectangle.setStroke(Color.RED); // Set the outline (stroke) color to red
				rectangle.setStrokeWidth(2);
				StackPane.setAlignment(label, javafx.geometry.Pos.TOP_LEFT);
				sp1.getChildren().addAll(label, rectangle);
				sp.add(sp1);
    		}
    	}
    	return sp;
    }
    
    public InterfaceItems setVisulization(CompositeItems items){
    	// Treeview component is set here
		InterfaceItems root = setTreeViewItemControlPane(items);

		// Visual Pane
		visualizationArea.getChildren().clear();
        visualizationArea.getChildren().addAll(setVisualPane(items));
        drone = new ImageView(new Image("drone.png"));
    	drone.setLayoutX(320);
		drone.setLayoutY(40);

    	System.out.println("drone curre x position::::"+ drone.getLayoutX()+" "+ drone.getLayoutY());
        visualizationArea.getChildren().add(drone);
        
        return root;
    }
    
    public InterfaceItems setTreeViewItemControlPane(CompositeItems allItems) {
    	Map<String, List<TreeItem<InterfaceItems>>> rootMap = new HashMap<>();
    	List<InterfaceItems> rootContainers = new ArrayList<>();
    	Map<String, List<TreeItem<InterfaceItems>>> itemContainerMap = new HashMap<>();
    	List<InterfaceItems> itemContainers = new ArrayList<>();
    	List<InterfaceItems> items = new ArrayList<>();
    	for(InterfaceItems i : allItems.getAll()) {
    		//("In line 124:::::::"+i.getTag());
    		if(i.getTag() == ContainerTags.ROOT) {
    			rootContainers.add(i);
    		} else if(i.getTag() == ContainerTags.ITEM_CONTAINER) {
    			itemContainers.add((ItemContainer) i);
    		} else if(i.getTag() == ContainerTags.ITEM) {
    			items.add((Item) i);
    			if(itemContainerMap.get(((Item) i).getParentId()) != null) {
    				itemContainerMap.get(((Item) i).getParentId()).add(new TreeItem<InterfaceItems>((Item) i));
    			} else {
    				List<TreeItem<InterfaceItems>> tempItem = new ArrayList<TreeItem<InterfaceItems>>();
    				tempItem.add(new TreeItem<InterfaceItems>((Item) i));
    				itemContainerMap.put(((Item) i).getParentId(),tempItem);
    			}
    		}
    	}
    	
    	List<TreeItem<InterfaceItems>> rootItems = new ArrayList<TreeItem<InterfaceItems>>();
    	rootItems.add(new TreeItem<InterfaceItems>(rootContainers.get(0)));
    	treeView1.setRoot(rootItems.get(0));
    	rootItems.get(0).getChildren().clear();
    	for(InterfaceItems ic : itemContainers) {
    		TreeItem<InterfaceItems> temp = new TreeItem<InterfaceItems>(ic);
    		rootItems.get(0).getChildren().add(temp);
    		if(itemContainerMap.get(ic.getId()) != null) {
    			temp.getChildren().addAll(itemContainerMap.get(ic.getId()));
    		}
    	}
    	return rootItems.get(0).getValue();
    }
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	CompositeItems items = getInitialSetup();
    	
    	InterfaceItems root = setVisulization(items);
    	//Initial values in ListView
    	myListView.getItems().addAll(new ListViewItems("Add_Item_Container",root));
    }
    
    public void selectTreeViewItem(){
    	TreeItem<InterfaceItems> item123 = treeView1.getSelectionModel().getSelectedItem();
    	//("item123 out side changed:::::::"+item123);
    	if(item123 != null) {
			//("item123:::::::"+item123);
			List<ListViewItems> listViewItems = new ArrayList<ListViewItems>();;
			if(item123.getValue().getTag() == ContainerTags.ITEM) {
				ItemVariables[] ivm = ItemVariables.values();
				for(ItemVariables i : ivm) {
					listViewItems.add(new ListViewItems(i.toString(),item123.getValue()));
				}
			} else if(item123.getValue().getTag() == ContainerTags.ITEM_CONTAINER) {
				ItemContainerVariables[] ivm = ItemContainerVariables.values();
				for(ItemContainerVariables i : ivm) {
					listViewItems.add(new ListViewItems(i.toString(),item123.getValue()));
				}
			} else if(item123.getValue().getTag() == ContainerTags.ROOT) {
				listViewItems.add(new ListViewItems("Add_Item_Container",item123.getValue()));
			}
			
			ObservableList<ListViewItems> items = FXCollections.observableArrayList(listViewItems);
			////("");
			myListView.setItems(items);
		}	
    	if (item != null) {
    		////(item);
    	}
    }
    
    public void onListViewItemClick() {
    	System.out.println("onListViewItemClick:::::::");
    	ListViewItems selectedItem = myListView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
        	String key = selectedItem.listViewItem;
        	ListViewItems lvItem;
        	switch(key) {
        	case "Add_Item_Container":
        		ItemContainer newItemContainer =  new ItemContainer();
        		////("In line 209:::::::"+ selectedItem.item.getName());
        		newItemContainer.setParentId(selectedItem.item.getTag() == ContainerTags.ROOT?selectedItem.item.getId():selectedItem.item.getParentId());
        		//newItemContainer.setParentId(selectedItem.item.getParentId());
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add a Name";
        		newItemContainer.setName(openDialog(lvItem, ""));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add width";
        		newItemContainer.setWidth(Integer.parseInt(openDialog(lvItem, "")));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add height";
        		newItemContainer.setHeight(Integer.parseInt(openDialog(lvItem, "")));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add X coordinate";
        		newItemContainer.setxCordinate(Integer.parseInt(openDialog(lvItem, "")));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add Y Coordinate";
        		newItemContainer.setyCordinate(Integer.parseInt(openDialog(lvItem,"")));
        		
        		//("before::::::"+ item.getAll().size());
        		item.addItem(newItemContainer);
        		//("after::::::"+ item.getAll().size());
        		setVisulization(item);
        		break;
        		
        	case "Delete_Item_Container":
        		int i=0;
        		for(i=0; i < item.getAll().size(); i++) {
        			if(item.getAll().get(i).getId() == selectedItem.item.getId() ) {
        				break;
        			}
        		}
        		//("In Delete_Item_Container::::::::::::");
        		item.deleteItem(item.getAll().get(i));
        		setVisulization(item);
        		break;
        		
        	case "Add_Item":
        		Item newItem =  new Item();
        		newItem.setParentId(selectedItem.item.getId());
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add a Name";
        		newItem.setName(openDialog(lvItem, ""));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add width";
        		newItem.setWidth(Integer.parseInt(openDialog(lvItem, "")));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add height";
        		newItem.setHeight(Integer.parseInt(openDialog(lvItem, "")));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add X coordinate";
        		newItem.setxCordinate(Integer.parseInt(openDialog(lvItem, "")));
        		
        		lvItem = new ListViewItems();
        		lvItem.listViewItem = "Add Y Coordinate";
        		newItem.setyCordinate(Integer.parseInt(openDialog(lvItem,"")));
        		
        		
        		item.addItem(newItem);
        		setVisulization(item);
        		break;
        		
        	case "Delete_Item":
        		int j=0;
        		for(j=0; j < item.getAll().size(); j++) {
        			if(item.getAll().get(j).getId() == selectedItem.item.getId() ) {
        				break;
        			}
        		}
        		//item.getAll().remove(j);
        		item.deleteItem(item.getAll().get(j));
        		setVisulization(item);
        		break;
        		
        	case "Change_X_Coordinate":
        		int k=0;
        		for(k=0; k < item.getAll().size(); k++) {
        			if(item.getAll().get(k).getId() == selectedItem.item.getId() ) {
        				item.getAll().get(k).changeXCoordinate(Integer.parseInt(openDialog(selectedItem, Integer.toString(selectedItem.item.getxCordinate()))));
        				break;
        			}
        		}
        		setVisulization(item);
        		break;
        		
        	case "Change_Y_Coordinate":
        		for(int r=0; r < item.getAll().size(); r++) {
        			if(item.getAll().get(r).getId() == selectedItem.item.getId() ) {
        				item.getAll().get(r).changeYCoordinate(Integer.parseInt(openDialog(selectedItem, Integer.toString(selectedItem.item.getyCordinate()))));
        				break;
        			}
        		}
        		setVisulization(item);
        		break;
        		
        	case "Change_Width":
        		for(int r=0; r < item.getAll().size(); r++) {
        			if(item.getAll().get(r).getId() == selectedItem.item.getId() ) {
        				item.getAll().get(r).changeWidth(Integer.parseInt(openDialog(selectedItem, Integer.toString(selectedItem.item.getWidth()))));
        				break;
        			}
        		}
        		setVisulization(item);
        		break;
        		
        	case "Change_Height":
        		for(int r=0; r < item.getAll().size(); r++) {
        			if(item.getAll().get(r).getId() == selectedItem.item.getId() ) {
        				item.getAll().get(r).changeHeight(Integer.parseInt(openDialog(selectedItem, Integer.toString(selectedItem.item.getHeight()))));
        				break;
        			}
        		}
        		setVisulization(item);
        		break;
        		
        	case "Change_Name":
        		for(int r=0; r < item.getAll().size(); r++) {
        			if(item.getAll().get(r).getId() == selectedItem.item.getId() ) {
        				item.getAll().get(r).changeName(openDialog(selectedItem, selectedItem.item.getName()));
        				break;
        			}
        		}
        		setVisulization(item);
        		break;
        	}
        	
        }
    }
    
    private String openDialog(ListViewItems selectedItem, String initialvalue) {
        
        TextInputDialog dialog = new TextInputDialog(initialvalue);
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText(selectedItem.toString());
        dialog.setContentText("Data:");

        Optional<String> result = dialog.showAndWait();
        
        result.ifPresent(inputText -> {
            //("Input text: " + inputText);
        });
        return result.get();
    }
    
    @FXML
    void onGoToItemBtnClick() {
    	if(treeView1.getSelectionModel().getSelectedItem().getValue() != null && treeView1.getSelectionModel().getSelectedItem().getValue().getTag() != ContainerTags.ROOT) {
            visualizationArea.getChildren().remove(drone);
    		drone = new ImageView(new Image("drone.png"));
    		double x = treeView1.getSelectionModel().getSelectedItem().getValue().getxCordinate();
    		double y = treeView1.getSelectionModel().getSelectedItem().getValue().getyCordinate();
    		drone.setLayoutX(x);
    		drone.setLayoutY(y);
    		visualizationArea.getChildren().add(drone);
    	}
    }
    
    
    @FXML
    void onGoHomeBtnClick() {
        visualizationArea.getChildren().remove(drone);
        drone = new ImageView(new Image("drone.png"));
    	drone.setLayoutX(320);
		drone.setLayoutY(40);
		visualizationArea.getChildren().add(drone);
    }
    
    private List<Path> paths;

    private List<Path> createPaths() {
        // Create and return an array of paths with your desired paths
        paths = new ArrayList<Path>(); 
        int newCounter = 0;
        int prevCounter = 0;
        int i = 1;
        visualizationArea.getChildren().remove(drone);
        drone = new ImageView(new Image("drone.png"));
    	drone.setLayoutX(320);
		drone.setLayoutY(40);
		visualizationArea.getChildren().add(drone);
        Path path = new Path(new MoveTo(40, drone.getLayoutY()+(prevCounter * 100)), new LineTo(visualizationArea.getWidth() - drone.getLayoutX() - 40, drone.getLayoutY()+(prevCounter * 100)));
        paths.add(path);
        while(drone.getLayoutY()+(newCounter * 100) < visualizationArea.getHeight()-100) {
        	path =  new Path(new MoveTo(drone.getLayoutX() - visualizationArea.getWidth() + leftPanel.getWidth() + 40,drone.getLayoutY()+(prevCounter * 100)),new LineTo(visualizationArea.getWidth() - drone.getLayoutX() - 40,drone.getLayoutY()+(prevCounter * 100)));
        	
            if (i % 2 == 1) {
                // Alternate the direction of some paths
        		newCounter++;
                path = new Path(new MoveTo(visualizationArea.getWidth() - drone.getLayoutX(), drone.getLayoutY()+(prevCounter * 100)), new LineTo(drone.getLayoutX() - visualizationArea.getWidth() + leftPanel.getWidth() + 40, drone.getLayoutY()+(newCounter * 100)));
                prevCounter = newCounter;
            }
            paths.add(path);
            i++;
        }
        path =  new Path(new MoveTo(drone.getLayoutX() - visualizationArea.getWidth() + leftPanel.getWidth() + 40,drone.getLayoutY()+(prevCounter * 100)),new LineTo(visualizationArea.getWidth() - drone.getLayoutX() - 40,drone.getLayoutY()+(prevCounter * 100)));
    	paths.add(path);
    	path =  new Path(new MoveTo(visualizationArea.getWidth() - drone.getLayoutX(),drone.getLayoutY()+(prevCounter * 100)),new LineTo(40,drone.getLayoutY()));
    	paths.add(path);

        return paths;
    }
    
    private int currentPathIndex;
    private void playNextPathTransition() {
        if (currentPathIndex < paths.size()) {
            PathTransition pathTransition = new PathTransition(Duration.seconds(1), paths.get(currentPathIndex), drone);
            pathTransition.setOnFinished(event -> {
                currentPathIndex++;
                playNextPathTransition();
            });
            pathTransition.play();
        }
    }

    @FXML
    void onScanFarmBtnClick() {
    	paths = createPaths();
        currentPathIndex = 0;

        playNextPathTransition();    
        
    }
	
}
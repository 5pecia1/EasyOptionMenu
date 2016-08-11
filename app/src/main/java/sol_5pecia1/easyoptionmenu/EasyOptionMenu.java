package sol_5pecia1.easyoptionmenu;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by 5pecia1 on 2016-08-11.
 */
public class EasyOptionMenu {
    private Activity activity;
    private int menuRes;
    private Menu menu;

    private LinkedHashMap<MenuItem, boolean[]> menuItemLinkedHashMap = null;
    private ArrayList<MenuItem> menuItemList = null;
    private int mode = 0;

    public EasyOptionMenu(Activity activity, int menuRes, Menu menu) {
        this.activity = activity;
        this.menuRes = menuRes;
        this.menu = menu;

        activity.getMenuInflater().inflate(menuRes,menu);
        menuItemLinkedHashMap = new LinkedHashMap<>(menu.size());
        menuItemList = new ArrayList<>(menu.size());
    }

    public void addMenu(int menuId, boolean... enableState){ // 어노테이션 사용 처음 불리언 리스트랑 크기가 다르면 에러
        MenuItem menuItem = menu.findItem(menuId);

        menuItemList.add(menuItem);
        menuItemLinkedHashMap.put(menuItem, enableState);
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    public boolean setMenuItemEnable(){
        boolean isSuccess = true;

        for(MenuItem menuItem : menuItemList) {
            boolean[] enableArray = menuItemLinkedHashMap.get(menuItem);
            boolean isEnable = false;

            if (enableArray.length < mode) {
                isSuccess = false;
            } else {
                isEnable = enableArray[mode];
            }

            menuItem.setEnabled(isEnable);
        }

        return isSuccess;
    }

    public List<MenuItem> getMenuList() {
        return menuItemList;
    }

    public MenuItem getMenuItme(int id) {

    }

    public Menu getMenu() {
        return menu;
    }
}

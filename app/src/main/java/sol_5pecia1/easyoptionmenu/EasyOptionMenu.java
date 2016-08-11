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
    public static final int ALL_ENABLE = -1;

    private Activity activity;
    private int menuRes;
    private Menu menu;

    private LinkedHashMap<MenuItem, boolean[]> menuItemLinkedHashMap = null;
    private ArrayList<MenuItem> menuItemList = null;
    private int mode = -1;

    public EasyOptionMenu(Activity activity, int menuRes, Menu menu) {
        this.activity = activity;
        this.menuRes = menuRes;
        this.menu = menu;

        activity.getMenuInflater().inflate(menuRes,menu);
        menuItemLinkedHashMap = new LinkedHashMap<>(menu.size());
        menuItemList = new ArrayList<>(menu.size());
    }

    public void addMenuItem(int menuId, boolean... enableState){
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

            if (enableArray  != null
                    && enableArray.length < mode
                    && mode < ALL_ENABLE) {
                isSuccess = false;
            } else if (ALL_ENABLE == mode) {
                isEnable = true;
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

    public Menu getMenu() {
        return menu;
    }
}

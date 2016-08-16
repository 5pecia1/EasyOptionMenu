package sol_5pecia1.easyoptionmenu;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class EasyOptionMenu {
    public static final int VISIBLE = 1;
    public static final int DISABLE = 0;
    public static final int HIDDEN = -1;

    public static final int ALL_ENABLE = -1;

    private Menu menu;

    private LinkedHashMap<MenuItem, int[]> menuItemHashMap;
    private int mode = 0;

    public EasyOptionMenu(Activity activity, int menuRes, Menu menu, int firstMode) {
        this.menu = menu;

        activity.getMenuInflater().inflate(menuRes,menu);
        this.menuItemHashMap = new LinkedHashMap<>(menu.size());
        this.mode = firstMode;
    }

    public void addMenuItem(int menuId, int... itemState){
        MenuItem menuItem = menu.findItem(menuId);

        menuItemHashMap.put(menuItem, itemState);
    }

    public boolean setMenuItemState(){
        boolean isSuccess = true;


        for(MenuItem menuItem : menuItemHashMap.keySet()) {
            int[] stateArray = menuItemHashMap.get(menuItem);
            boolean isEnable = true;
            boolean isVisible = true;

            if (stateArray == null
                    || stateArray.length < mode
                    || mode < ALL_ENABLE) {
                isSuccess = false;
            } else if (mode  != ALL_ENABLE) {
                switch (stateArray[mode]) {
                    case DISABLE:
                        isEnable = false;
                        break;
                    case HIDDEN :
                        isVisible = false;
                        break;
                    case VISIBLE :
                    default:
                        break;
                }
            }

            menuItem.setEnabled(isEnable);
            menuItem.setVisible(isVisible);
        }

        return isSuccess;
    }

    public List<MenuItem> getMenuList() {
        MenuItem[] array = (MenuItem[])menuItemHashMap.keySet().toArray();

        return Arrays.asList(array);
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMode(int mode){
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}

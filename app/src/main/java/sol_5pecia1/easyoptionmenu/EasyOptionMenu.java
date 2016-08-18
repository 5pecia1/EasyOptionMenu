package sol_5pecia1.easyoptionmenu;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedHashMap;

public class EasyOptionMenu {
    public static final int VISIBLE = 1;
    public static final int DISABLE = 0;
    public static final int HIDDEN = -1;

    public static final int ALL_ENABLE = -1;

    private Activity activity;
    private Menu menu;

    private LinkedHashMap<MenuItem, int[]> menuItemHashMap;

    public EasyOptionMenu(Activity activity) {
        this.activity = activity;
    }

    public void setMenu(int menuRes, Menu menu) {
        this.menu = menu;

        activity.getMenuInflater().inflate(menuRes,menu);
        this.menuItemHashMap = new LinkedHashMap<>(menu.size());
    }

    public void addMenuItem(int menuId, int... itemState){
        MenuItem menuItem = menu.findItem(menuId);

        menuItemHashMap.put(menuItem, itemState);
    }

//    /**
//     *
//     * @param menuId
//     * @param itemState
//     * @param otherItemState
//     * @return mode
//     */
//    public int setNextModeOnceItemState(int menuId, int itemState, int otherItemState) {
//
//    }
//
//    /**
//     * default state visible
//     *
//     * @param menuId
//     * @param itemState
//     * @return mode
//     */
//    public int setNextModeAllItemState(int menuId, int... itemState) {
//
//    }

    public boolean setMenuItemState(int mode){
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
                        break;
                    default:
                        isSuccess = false;
                        break;
                }
            }

            menuItem.setEnabled(isEnable);
            menuItem.setVisible(isVisible);
        }

        return isSuccess;
    }
}

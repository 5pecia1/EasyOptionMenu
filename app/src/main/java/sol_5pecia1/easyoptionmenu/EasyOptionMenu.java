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
    private int minStateArrayLength = 0;

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

        if (itemState.length > minStateArrayLength) {
            minStateArrayLength = itemState.length;
        }

        menuItemHashMap.put(menuItem, itemState);
    }

    /**
     *
     * @param menuId
     * @param itemState
     * @param otherItemState
     * @return mode
     */
    public int addNextModeOnceItemState(int menuId, int itemState, int otherItemState) {
        int nextStateArrayLength = minStateArrayLength + 1;

        for (MenuItem menuItem : menuItemHashMap.keySet()) {
            int[] stateArray = new int[nextStateArrayLength];

            System.arraycopy(menuItemHashMap.get(menuItem), 0, stateArray, 0, minStateArrayLength);
            if (menuItem.getItemId() == menuId) {
                stateArray[minStateArrayLength] = itemState;
            } else {
                stateArray[minStateArrayLength] = otherItemState;
            }
            menuItemHashMap.put(menuItem, stateArray);
        }

        minStateArrayLength += 1;
        return minStateArrayLength;
    }

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

package sol_5pecia1.easyoptionmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private static final int MODE_VISIBLE_1_NOT_ENABLE_3_4 = 0;
    private static final int MODE_VISIBLE_3_NOT_ENABLE_1 = 1;


    private EasyOptionMenu easyOptionMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.testButton1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyOptionMenu.setMode(EasyOptionMenu.ALL_ENABLE);
            }
        });

        findViewById(R.id.testButton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyOptionMenu.setMode(MODE_VISIBLE_1_NOT_ENABLE_3_4);
            }
        });

        findViewById(R.id.testButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyOptionMenu.setMode(MODE_VISIBLE_3_NOT_ENABLE_1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        easyOptionMenu = new EasyOptionMenu(this, R.menu.main, menu, EasyOptionMenu.ALL_ENABLE);

        int v = EasyOptionMenu.VISIBLE;
        int n = EasyOptionMenu.DISABLE;
        int h = EasyOptionMenu.HIDDEN;

        easyOptionMenu.addMenuItem(R.id.test1, v, n);
        easyOptionMenu.addMenuItem(R.id.test2, h, h);
        easyOptionMenu.addMenuItem(R.id.test3, n, v);
        easyOptionMenu.addMenuItem(R.id.test4, n, h);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        easyOptionMenu.setMenuItemState();

        return super.onPrepareOptionsMenu(menu);
    }
}

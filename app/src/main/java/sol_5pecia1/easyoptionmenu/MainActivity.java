package sol_5pecia1.easyoptionmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class MainActivity extends AppCompatActivity {
    private static final int MODE_EANBLE_1_3_4 = 0;
    private static final int MODE_EANBLE_3 = 1;


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
                easyOptionMenu.setMode(MODE_EANBLE_1_3_4);
            }
        });

        findViewById(R.id.testButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                easyOptionMenu.setMode(MODE_EANBLE_3);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        easyOptionMenu = new EasyOptionMenu(this,R.menu.main,menu);


        easyOptionMenu.addMenuItem(R.id.test1,true,false);
        easyOptionMenu.addMenuItem(R.id.test2,false,false);
        easyOptionMenu.addMenuItem(R.id.test3,true,true);
        easyOptionMenu.addMenuItem(R.id.test4,true,false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        easyOptionMenu.setMenuItemEnable();

        return super.onPrepareOptionsMenu(menu);
    }
}

package com.vansuzy.baigiang43_contextmenu;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnTopicaEdumall, btnEdumallTopica, btnTopicaAllThings;
    Button btnLastedSelected = null;   // lưu lại control cuối cùng đang chọn để chương trình biết được chúng ta đang chọn control nào để tương tác control đó với context menu (trong trường hợp context menu đó được chia sẻ cho nhiều control khác nhau thì bắt buộc chúng ta phải làm cách này, tức là chúng ta phải đánh dấu lại trước).
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addEvents() {
        btnTopicaEdumall.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastedSelected = btnTopicaEdumall;
                return false;
            }
        });
        btnEdumallTopica.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastedSelected = btnEdumallTopica;
                return false;
            }
        });
        btnTopicaAllThings.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btnLastedSelected = btnTopicaAllThings;
                return false;
            }
        });
    }

    private void addControls() {
        btnTopicaEdumall = (Button) findViewById(R.id.btnTopicaEdumall);
        btnEdumallTopica = (Button) findViewById(R.id.btnEdumallTopica);
        btnTopicaAllThings = (Button) findViewById(R.id.btnTopicaAllThings);

        // Bước 4
        registerForContextMenu(btnTopicaEdumall);
        registerForContextMenu(btnEdumallTopica);
        registerForContextMenu(btnTopicaAllThings);
    }

    // Bước 2
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextmenu_main, menu);
    }

    // Bước 3
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnuInDam) {
            btnLastedSelected.setTypeface(null, Typeface.BOLD);
        } else if (item.getItemId() == R.id.mnuMauDo) {
            btnLastedSelected.setTextColor(Color.RED);
        } else if (item.getItemId() == R.id.mnuXoa) {
            btnLastedSelected.setVisibility(View.INVISIBLE);
        }
        return super.onContextItemSelected(item);
    }
}

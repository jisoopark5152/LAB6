package edu.temple.colorchooser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PaletteFragment.ColorSelectedInterface {

    CanvasFragment canvasFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        canvasFragment = new CanvasFragment();

        PaletteFragment paletteFragment = PaletteFragment.newInstance(getResources().getStringArray(R.array.colors));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_1, paletteFragment)
                .add(R.id.container_2, canvasFragment)
                .commit();
    }

    //implement interface ColorSelectedInterface
    @Override
    public void colorSelected(String color){
        canvasFragment.setBackgroudColor(color);
    }
}

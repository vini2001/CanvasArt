package br.vinic.customvinicalendarview;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MainActivity extends AppCompatActivity {

    private ImageButton btn_redo, btn_undo;
    private CanvasArtView canvasView;
    private SeekBar seekBar;
    private TextView txtEspessura;
    private CircleView circleView;
    private String corAtual = "#008577";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_redo = findViewById(R.id.btn_redo);
        btn_undo = findViewById(R.id.btn_undo);
        canvasView = findViewById(R.id.canvasView);
        seekBar = findViewById(R.id.seekBar);
        txtEspessura = findViewById(R.id.txt_espessura);
        circleView = findViewById(R.id.circleView);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int qtd = (seekBar.getProgress()+5)/4;

                String str = "Espessura: "+qtd;
                txtEspessura.setText(str);
                canvasView.setEspessura(qtd);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasView.undo();
            }
        });

        btn_redo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                canvasView.redo();
            }
        });

        circleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirColorPicker();
            }
        });
    }

    private void abrirColorPicker() {
        ColorPickerDialogBuilder
                .with(MainActivity.this)
                .setTitle("Escolha a cor desejada")
                .initialColor(Color.parseColor(corAtual))
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        corAtual = "#"+Integer.toHexString(selectedColor);
                    }
                })
                .setPositiveButton("OK", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        circleView.setColor(corAtual);
                        canvasView.setColor(corAtual);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .build()
                .show();
    }
}

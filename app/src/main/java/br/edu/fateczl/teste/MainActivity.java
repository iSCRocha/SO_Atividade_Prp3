package br.edu.fateczl.teste;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    private FrameLayout flSobre;
    private FloatingActionButton fabFecharSobre;
    private ImageView icSobre;
    private ImageView icTema;
    private EditText etQtdTermos;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        icSobre = findViewById(R.id.icSobre);
        icSobre.setOnClickListener(act -> abrirSobre());

        flSobre = findViewById(R.id.flSobre);

        fabFecharSobre = findViewById(R.id.fabFecharSobre);
        fabFecharSobre.setOnClickListener(act -> voltarPrincipal());

        icTema = findViewById(R.id.icTema);
        icTema.setOnClickListener(act -> mudarTema());

        etQtdTermos = findViewById(R.id.etQtdTermos);
        tvResultado = findViewById(R.id.tvResultado);

        btnCalcular = findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(act -> multiplicarTermos());
    }

    private void multiplicarTermos() {
        int[] serie = {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

        String value = etQtdTermos.getText().toString();

        int qtd = value.isEmpty() ? 0 : Integer.parseInt(value);

        String resultado;
        if (qtd > 9) {
            resultado = getString(R.string.maior_que_nove);
        } else if (qtd < 3) {
            resultado = getString(R.string.menor_que_tres);
        } else {
            int multiplicacao = 1;
            String calculo = "1";

            for (int i = 0; i < qtd; i++) {
                multiplicacao *= serie[i];
                calculo = i > 0 ? calculo.concat(" * " + serie[i]) : calculo;
            }

            resultado = calculo + "\n\n" + getString(R.string.resultado) + " " + NumberFormat.getInstance().format(multiplicacao).replace(",", ".");
        }

        tvResultado.setText(resultado);
        tvResultado.setEnabled(true);
    }

    private void abrirSobre() {
        if (flSobre.getVisibility() == FrameLayout.VISIBLE) {
            voltarPrincipal();
        } else {
            flSobre.setVisibility(FrameLayout.VISIBLE);
            fabFecharSobre.setVisibility(Button.VISIBLE);
        }
    }

    private void voltarPrincipal() {
        flSobre.setVisibility(FrameLayout.INVISIBLE);
        fabFecharSobre.setVisibility(Button.INVISIBLE);
    }

    private void mudarTema() {
        int theme = AppCompatDelegate.getDefaultNightMode();

        if (theme == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED || theme == AppCompatDelegate.MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }
}
package com.example.quadraticsolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText CoeA, CoeB, CoeC;
   Button btCalculate,btClear;
   TextView ErrorMessage;
  int a,b,c;

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


        CoeA = findViewById(R.id.CoeA);
        CoeB = findViewById(R.id.CoeB);
        CoeC = findViewById(R.id.CoeC);
        btCalculate = findViewById(R.id.buttonSolve);
        btClear=findViewById(R.id.buttonClear);
        ErrorMessage=findViewById(R.id.textViewErrorMessage);

        btCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                Bundle bundle= new Bundle();
               if(Datatransfer(bundle)==-1){
                   return;
               };
                intent.putExtra("Data",bundle); 
                startActivity(intent);
                clearEditTexts();
            }
        });
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearEditTexts();
            }
        });
    }
    private int Datatransfer(Bundle bundle) {

        String aStr = CoeA.getText().toString();
        String bStr = CoeB.getText().toString();
        String cStr = CoeC.getText().toString();

        if (aStr.isEmpty() || bStr.isEmpty() || cStr.isEmpty()) {
            ErrorMessage.setText("Please enter all coefficients");
            ErrorMessage.setVisibility(View.VISIBLE);
            return -1;
        }
        a = Integer.parseInt(aStr);
        b = Integer.parseInt(bStr);
        c = Integer.parseInt(cStr);
        int[] coefficients = {a, b, c};
        bundle.putIntArray("Coef",coefficients);
        return 1;
    }
    private void clearEditTexts() {
        CoeA.setText("");
        CoeB.setText("");
        CoeC.setText("");
        CoeA.requestFocus();
        ErrorMessage.setVisibility(View.GONE);
    }



}
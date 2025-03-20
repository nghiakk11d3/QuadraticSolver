package com.example.quadraticsolver;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.graphics.Color;
public class MainActivity2 extends AppCompatActivity {
    Button btReturn;
    TextView Type, X1, X2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btReturn=findViewById(R.id.buttonReturn);
        Type=findViewById(R.id.SolutionType);
        X1=findViewById(R.id.X1);
        X2=findViewById(R.id.X2);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("Data");
        int[] coefficients=bundle.getIntArray("Coef");
        int a = coefficients[0];
        int b = coefficients[1];
        int c = coefficients[2];

        calculateQuadraticEquation(a,b,c);


        btReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    private void calculateQuadraticEquation(int a, int b, int c) {

        double delta = b * b - 4 * a * c;
        if (delta < 0) {
            Type.setText("The equation has no solution");
            Type.setTextColor(Color.parseColor("#FF0000"));
            X1.setText("No Solution");
            X2.setText("No Solution");
        } else if (delta == 0) {
            double x = -b / (2 * a);
            Type.setText("The equation has two equal roots");
            Type.setTextColor(Color.parseColor("#008000"));
            X1.setText("X1 = "+x);
            X2.setText("X2 = "+x);
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            Type.setText("The equation has two distinct roots");
            Type.setTextColor(Color.parseColor("#008000"));
            X1.setText("X1 = "+x1);
            X2.setText("X2 = "+x2);
        }
    }
}

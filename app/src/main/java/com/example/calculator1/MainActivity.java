package com.example.calculator1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener{

    private Button[] butt = new Button[19];
    private int[] butt_id = new int[]{R.id.butt_c, R.id.butt_divide, R.id.butt_multiply, R.id.butt_delete, R.id.butt_substract,
            R.id.butt_add, R.id.butt_equal, R.id.butt_percent, R.id.butt_point, R.id.butt_0, R.id.butt_1, R.id.butt_2,
            R.id.butt_3, R.id.butt_4, R.id.butt_5, R.id.butt_6, R.id.butt_7, R.id.butt_8, R.id.butt_9};
    private TextView cal_result;
    private String str,num1,num2;
    private double result;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //界面打开后最先运行的地方
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//对应界面UI
        //一般用来进行界面初始化，控件初始化，初始化一些参数和变量

        ui_init();
    }

    private void ui_init() {
        cal_result = findViewById(R.id.cal_result);
        flag = true;
        double result = 0;
        for(int i=0;i<butt_id.length;i++){
            butt[i] = findViewById(butt_id[i]);
            butt[i].setOnClickListener(this);
        }

    }

    public void onClick(View view){
        str = (String)cal_result.getText();
        int id = view.getId();
        switch(id){
            case R.id.butt_percent:
                String strTemp = cal_result.getText().toString();
                double tempNum = Double.valueOf(strTemp);
                tempNum = tempNum * 0.01;
                strTemp = String.valueOf(tempNum);
                cal_result.setText(strTemp);
                break;
            case R.id.butt_c:
                cal_result.setText("");
                break;
            case R.id.butt_delete:
                if(!str.equals("") && str != null){
                    cal_result.setText(str.substring(0,str.length()-1));
                }
                break;
            case R.id.butt_equal:
                if(str.contains("+")){
                    getResult(num1, num2, "+");
                }
                else if(str.contains("-")){
                    getResult(num1, num2, "-");
                }
                else if(str.contains("*")){
                    getResult(num1, num2, "*");
                }
                else if(str.contains("/")){
                    getResult(num1, num2, "/");
                }
                else {
                    return;
                }
            case R.id.butt_add:
            case R.id.butt_substract:
            case R.id.butt_multiply:
            case R.id.butt_divide:
                if (str.contains("+")||str.contains("-")||str.contains("*")||str.contains("/"))
                    return;
                else
                    cal_result.setText(str+((Button)view).getText());
                if(!flag)
                    flag = true;
                break;
            default:
                if (flag) {
                    cal_result.setText(str+((Button)view).getText());
                }else{
                    cal_result.setText(((Button)view).getText());
                    flag = true;
                }
                break;
        }

    }

    private void getResult(String num1,String num2,String sp) {
        num1 = str.substring(0, str.indexOf(sp));
        num2 = str.substring(str.indexOf(sp) + 1);
        double n1 = Double.valueOf(num1);
        double n2 = Double.valueOf(num2);

        if (sp.equals("+")) {
            result = n1 + n2;
        } else if (sp.equals("-")) {
            result = n1 - n2;
        } else if (sp.equals("*")) {
            result = n1 * n2;
        } else if (sp.equals("/")) {
            if(n2 == 0){
                result = 0;
            }else{
                result = n1 / n2;
            }
        } else {
            return;
        }

        String tmp = String.valueOf(result);

        if (tmp.contains(".") && tmp.substring(tmp.length() - 1).equals("0")) {
            tmp = tmp.substring(0, tmp.indexOf("."));
        }

        cal_result.setText(tmp);
        flag = false;

    }
}

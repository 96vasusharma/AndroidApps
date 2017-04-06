package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int price = calculatePrice();
        int price=5;
        EditText editText=(EditText)findViewById(R.id.name_edit_text);
        CheckBox checkBox=(CheckBox)findViewById(R.id.chocolate_checkbox);
        CheckBox checkBox1=(CheckBox)findViewById(R.id.whipped_cream_checkbox);
        String user=editText.getText().toString();
        boolean isWhippedCreamChecked = checkBox1.isChecked();
        boolean isChocolateChecked = checkBox.isChecked();
        if (isWhippedCreamChecked){
            price+=1;
        }
        if (isChocolateChecked){
            price+=2;
        }
        price*=quantity;
        String text=createOrderSummary(price,isChocolateChecked,isWhippedCreamChecked,user);
        //displayMessage(info);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,"JustJava order for "+user);
        intent.putExtra(Intent.EXTRA_TEXT,text);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    /**
     *
     * @param price is the price of the order
     * @param chocolateStatus is the status whether we need chocolate or not
     * @param whippedCreamStatus is the status whether we need whipped cream or not
     * @param user is the username
     * @return name is the final output we need to send to the display method
     */
    private String createOrderSummary(int price,boolean chocolateStatus,boolean whippedCreamStatus,String user) {

        String name="Name:  "+user;
        name+="\nAdd whipped cream ? "+whippedCreamStatus;
        name+="\nAdd chocolate ? "+chocolateStatus;
        name = name + "\nQuantity = "+quantity;
        name+="\nTotal = Rs."+price;
        name+="\nThank you.";
        return name;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    protected void increment(View view){
        Button b2 = (Button)findViewById(R.id.b2);
        Button b1 =(Button)findViewById(R.id.b1);
        quantity=quantity+1;
        if (quantity==2)
        {

            b1.setEnabled(true);

        }
        if (quantity==99)
        {
            b2.setEnabled(false);
            Toast.makeText(MainActivity.this, "The maximum cups can be 99", Toast.LENGTH_LONG).show();

        }
        displayQuantity(quantity);
    }
    public void decrement(View view){
        Button b1 =(Button)findViewById(R.id.b1);
        Button b2 = (Button)findViewById(R.id.b2);
        quantity-=1;
        if (quantity==1)
        {

            b1.setEnabled(false);
            Toast.makeText(MainActivity.this, "The minimum cups must be 1", Toast.LENGTH_LONG).show();
        }
        if (quantity== 98){
            b2.setEnabled(true);
        }
        displayQuantity(quantity);
    }




}
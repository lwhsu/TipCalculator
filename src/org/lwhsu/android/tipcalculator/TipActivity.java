package org.lwhsu.android.tipcalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TipActivity extends Activity {

    private double percentage = 0.10;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip);

        setupTotalAmountListener();
    }

    private void setupTotalAmountListener() {
        final EditText etAmount = (EditText) findViewById(R.id.etAmount);
        etAmount.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
            }

            @Override
            public void afterTextChanged(final Editable s) {
                calcTip();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changePercentage(final View v) {
        percentage = Double.parseDouble(v.getTag().toString());
        calcTip();
    }

    private void calcTip() {
        final EditText etAmount = (EditText) findViewById(R.id.etAmount);
        final TextView tvTipAmount = (TextView) findViewById(R.id.tvTipAmount);

        try {
            final double amount = Double.parseDouble((etAmount.getText().toString()));
            final double tipAmount = amount * percentage;

            tvTipAmount.setText("Tip is: " + new DecimalFormat("$#,##0.00").format(tipAmount)
                    + " (" + new DecimalFormat("#%").format(percentage) + ")");
        } catch (final Exception e) {
            tvTipAmount.setText("Tip is: N/A");
        }
    }
}

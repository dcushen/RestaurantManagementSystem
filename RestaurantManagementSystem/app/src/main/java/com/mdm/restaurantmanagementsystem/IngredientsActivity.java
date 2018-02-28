package com.mdm.restaurantmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.mdm.restaurantmanagementsystem.request.CalorieRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IngredientsActivity extends AppCompatActivity implements OnClickListener
{
    String Weight, IngredientName, Type, Cal;
    double totalCal;
    LinearLayout panel1,panel2,panel3,panel4,panel5;
    TextView vegetables,meats,fish,dairy,pasta;
    CheckBox TomatoCheckbox,CarrotCheckbox,LettuceCheckbox,
            ChickenCheckbox,BeefCheckbox,PorkCheckbox,
            SalmonCheckbox,ShrimpCheckbox,CodCheckbox,
            ButterCheckbox,CottageCheeseCheckbox,MilkCheckbox,
            FusilliCheckbox,LinguineCheckbox,PenneCheckbox;
    EditText TomatoWeight,CarrotWeight,LettuceWeight,
            ChickenWeight,BeefWeight,PorkWeight,
            SalmonWeight,ShrimpWeight,CodWeight,
            ButterWeight,CottageCheeseWeight,MilkWeight,
            FusilliWeight,LinguineWeight,PenneWeight;

    ArrayList<String> ingredients = new ArrayList<>();

    View openLayout;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);

        panel1 = (LinearLayout) findViewById(R.id.panel1); //Vegetables Panel
        panel2 = (LinearLayout) findViewById(R.id.panel2); //Meat Panel
        panel3 = (LinearLayout) findViewById(R.id.panel3); //Fish Panel
        panel4 = (LinearLayout) findViewById(R.id.panel4); //Dairy Panel
        panel5 = (LinearLayout) findViewById(R.id.panel5); //Pasta Panel

        vegetables = (TextView) findViewById(R.id.vegetables);
        meats = (TextView) findViewById(R.id.meats);
        fish = (TextView) findViewById(R.id.fish);
        dairy = (TextView) findViewById(R.id.dairy);
        pasta = (TextView) findViewById(R.id.pasta);

        vegetables.setOnClickListener(this);
        meats.setOnClickListener(this);
        fish.setOnClickListener(this);
        dairy.setOnClickListener(this);
        pasta.setOnClickListener(this);

        TomatoCheckbox = (CheckBox) findViewById(R.id.TomatoCheckbox);
        CarrotCheckbox = (CheckBox) findViewById(R.id.CarrotCheckbox);
        LettuceCheckbox = (CheckBox) findViewById(R.id.LettuceCheckbox);
        ChickenCheckbox = (CheckBox) findViewById(R.id.ChickenCheckbox);
        BeefCheckbox = (CheckBox) findViewById(R.id.BeefCheckbox);
        PorkCheckbox = (CheckBox) findViewById(R.id.PorkCheckbox);
        SalmonCheckbox = (CheckBox) findViewById(R.id.SalmonCheckbox);
        ShrimpCheckbox = (CheckBox) findViewById(R.id.ShrimpCheckbox);
        CodCheckbox = (CheckBox) findViewById(R.id.CodCheckbox);
        ButterCheckbox = (CheckBox) findViewById(R.id.ButterCheckbox);
        CottageCheeseCheckbox = (CheckBox) findViewById(R.id.CottageCheeseCheckbox);
        MilkCheckbox = (CheckBox) findViewById(R.id.MilkCheckbox);
        FusilliCheckbox = (CheckBox) findViewById(R.id.FusilliCheckbox);
        LinguineCheckbox = (CheckBox) findViewById(R.id.LinguineCheckbox);
        PenneCheckbox = (CheckBox) findViewById(R.id.PenneCheckbox);

        TomatoWeight = (EditText) findViewById(R.id.TomatoWeight);
        CarrotWeight = (EditText) findViewById(R.id.CarrotWeight);
        LettuceWeight = (EditText) findViewById(R.id.LettuceWeight);
        ChickenWeight = (EditText) findViewById(R.id.ChickenWeight);
        BeefWeight = (EditText) findViewById(R.id.BeefWeight);
        PorkWeight = (EditText) findViewById(R.id.PorkWeight);
        SalmonWeight = (EditText) findViewById(R.id.SalmonWeight);
        ShrimpWeight = (EditText) findViewById(R.id.ShrimpWeight);
        CodWeight = (EditText) findViewById(R.id.CodWeight);
        ButterWeight = (EditText) findViewById(R.id.ButterWeight);
        CottageCheeseWeight = (EditText) findViewById(R.id.CottageCheeseWeight);
        MilkWeight = (EditText) findViewById(R.id.MilkWeight);
        FusilliWeight = (EditText) findViewById(R.id.FusilliWeight);
        LinguineWeight = (EditText) findViewById(R.id.LinguineWeight);
        PenneWeight = (EditText) findViewById(R.id.PenneWeigth);
    }

    @Override
    public void onClick(View v)
    {
        hideOthers(v);
    }

    private void hideThemAll()
    {
        if(openLayout == null) return;
        if(openLayout == panel1)
            panel1.startAnimation(new ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, panel1, true));
        if(openLayout == panel2)
            panel2.startAnimation(new ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, panel2, true));
        if(openLayout == panel3)
            panel3.startAnimation(new ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, panel3, true));
        if(openLayout == panel4)
            panel4.startAnimation(new ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, panel4, true));
        if(openLayout == panel5)
            panel5.startAnimation(new ScaleAnimToHide(1.0f, 1.0f, 1.0f, 0.0f, 500, panel5, true));
    }

    private void hideOthers(View layoutView)
    {
        {
            int v;
            if(layoutView.getId() == R.id.vegetables)
            {
                v = panel1.getVisibility();
                if(v != View.VISIBLE)
                {
                    panel1.setVisibility(View.VISIBLE);
                }

                hideThemAll();
                if(v != View.VISIBLE)
                {
                    panel1.startAnimation(new ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, panel1, true));
                }
            }
            else if(layoutView.getId() == R.id.meats)
            {
                v = panel2.getVisibility();
                hideThemAll();
                if(v != View.VISIBLE)
                {
                    panel2.startAnimation(new ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, panel2, true));
                }
            }
            else if(layoutView.getId() == R.id.fish)
            {
                v = panel3.getVisibility();
                hideThemAll();
                if(v != View.VISIBLE)
                {
                    panel3.startAnimation(new ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, panel3, true));
                }
            }
            else if(layoutView.getId() == R.id.dairy)
            {
                v = panel4.getVisibility();
                hideThemAll();
                if(v != View.VISIBLE)
                {
                    panel4.startAnimation(new ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, panel4, true));
                }
            }
            else if(layoutView.getId() == R.id.pasta)
            {
                v = panel5.getVisibility();
                hideThemAll();
                if(v != View.VISIBLE)
                {
                    panel5.startAnimation(new ScaleAnimToShow(1.0f, 1.0f, 1.0f, 0.0f, 500, panel5, true));
                }
            }
        }
    }

    public class ScaleAnimToHide extends ScaleAnimation
    {
        private View mView;

        private LayoutParams mLayoutParams;

        private int mMarginBottomFromY, mMarginBottomToY;

        private boolean mVanishAfter = false;

        public ScaleAnimToHide(float fromX, float toX, float fromY, float toY, int duration, View view, boolean vanishAfter)
        {
            super(fromX, toX, fromY, toY);
            setDuration(duration);
            openLayout = null;
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LayoutParams) view.getLayoutParams();
            int height = mView.getHeight();
            mMarginBottomFromY = (int) (height * fromY) + mLayoutParams.bottomMargin - height;
            mMarginBottomToY = (int) (0 - ((height * toY) + mLayoutParams.bottomMargin)) - height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f)
            {
                int newMarginBottom = mMarginBottomFromY + (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime);
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin,mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
            }
            else if (mVanishAfter)
            {
                mView.setVisibility(View.GONE);
            }
        }
    }

    public class ScaleAnimToShow extends ScaleAnimation
    {
        private View mView;

        private LayoutParams mLayoutParams;

        private int mMarginBottomFromY, mMarginBottomToY;

        private boolean mVanishAfter = false;

        public ScaleAnimToShow(float toX, float fromX, float toY, float fromY, int duration, View view, boolean vanishAfter)
        {
            super(fromX, toX, fromY, toY);
            openLayout = view;
            setDuration(duration);
            mView = view;
            mVanishAfter = vanishAfter;
            mLayoutParams = (LayoutParams) view.getLayoutParams();
            mView.setVisibility(View.VISIBLE);
            int height = mView.getHeight();

            mMarginBottomFromY = 0;
            mMarginBottomToY = height;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            super.applyTransformation(interpolatedTime, t);
            if (interpolatedTime < 1.0f)
            {
                int newMarginBottom = (int) ((mMarginBottomToY - mMarginBottomFromY) * interpolatedTime) - mMarginBottomToY;
                mLayoutParams.setMargins(mLayoutParams.leftMargin, mLayoutParams.topMargin,mLayoutParams.rightMargin, newMarginBottom);
                mView.getParent().requestLayout();
            }
        }
    }

    public void retrieveIngredients(View view)
    {
        ingredients.clear();

        if (TomatoCheckbox.isChecked())
        {
            IngredientName = "Tomato"; Weight = TomatoWeight.getText().toString();
            ingredients.add(Weight + " small whole " + IngredientName);
            retrieveInfo(IngredientName);
        }

        if (CarrotCheckbox.isChecked())
        {
            IngredientName = "Carrot"; Weight = CarrotWeight.getText().toString();
            ingredients.add(Weight + " cup chopped " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (LettuceCheckbox.isChecked())
        {
            IngredientName = "Lettuce"; Weight = LettuceWeight.getText().toString();
            ingredients.add(Weight + " cup shredded " + IngredientName);
            retrieveInfo(IngredientName);
        }

        if (ChickenCheckbox.isChecked())
        {
            IngredientName = "Chicken"; Weight = ChickenWeight.getText().toString();
            ingredients.add(Weight + " patty " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (BeefCheckbox.isChecked())
        {
            IngredientName = "Beef"; Weight = BeefWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (PorkCheckbox.isChecked())
        {
            IngredientName = "Pork"; Weight = PorkWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }

        if (SalmonCheckbox.isChecked())
        {
            IngredientName = "Salmon"; Weight = SalmonWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (ShrimpCheckbox.isChecked())
        {
            IngredientName = "Shrimp"; Weight = ShrimpWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (CodCheckbox.isChecked())
        {
            IngredientName = "Cod"; Weight = CodWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }

        if (ButterCheckbox.isChecked())
        {
            IngredientName = "Butter"; Weight = ButterWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (CottageCheeseCheckbox.isChecked())
        {
            IngredientName = "CottageCheese"; Weight = CottageCheeseWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (MilkCheckbox.isChecked())
        {
            IngredientName = "Milk"; Weight = MilkWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }

        if (FusilliCheckbox.isChecked())
        {
            IngredientName = "Fusilli"; Weight = FusilliWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (LinguineCheckbox.isChecked())
        {
            IngredientName = "Linguine"; Weight = LinguineWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }
        if (PenneCheckbox.isChecked())
        {
            IngredientName = "Penne"; Weight = PenneWeight.getText().toString();
            ingredients.add(Weight + " g of " + IngredientName);
            retrieveInfo(IngredientName);
        }

        for (String ingredient : ingredients)
        {
            Log.v("IngredientsActivity", "Ingredient: " + ingredient);
        }
    }

    public void retrieveInfo(String IngredientName)
    {
        Response.Listener<String> responseListener = new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonResponse = new JSONObject(response);
                    retrieveCal(response);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        };

        CalorieRequest calRequest = new CalorieRequest(IngredientName, responseListener);
        RequestQueue queue = Volley.newRequestQueue(IngredientsActivity.this);
        queue.add(calRequest);
    }

    private void retrieveCal(String response)
    {
        try
        {
            JSONObject jsonObject = new JSONObject(response);

            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);

            for (int x = 0; x < result.length(); x++)
            {
                JSONObject jsonData = result.getJSONObject(x);

                IngredientName = jsonData.getString(Config.KEY_IngredientName);
                Type = jsonData.getString(Config.KEY_Type);
                Cal = jsonData.getString(Config.KEY_CalPerWeight);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        Double CalTotal = Double.parseDouble(Cal) * Double.parseDouble(Weight);
        Log.v("IngredientsActivity","Cal: " + CalTotal);

        totalCal += CalTotal;
        Log.v("IngredientsActivity","Total Cal: " + totalCal);

        String ingredientsList = ingredients.toString();
        Intent recipeIntent = new Intent(IngredientsActivity.this, NewMenuItemActivity.class);
            recipeIntent.putExtra("ingredientsList",ingredientsList);
            recipeIntent.putExtra("totalCal",totalCal);
        startActivity(recipeIntent);
    }
}
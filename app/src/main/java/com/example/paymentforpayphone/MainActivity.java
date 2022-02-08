package com.example.paymentforpayphone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    public RequestQueue colaRequest;
    private final String TOKEN = "kLNZZBXH_EUd7i7iMqpb62fNcP1-0frAyGI9iQmN_uBaTaOfnuN_c3bQS23bN5YhLy0JKrd36GSLCpV-lMyVunSGx78Xrb6i5YvC7M-ZxRz452t_elOG1ay1A5KObGn--HxQYYf8s8-2E8KKpm2Njghdc0xWp_cHm_-EXikJkU2GGaCXobDe7L14QkaGmG_jrppc_xqp4eDth8a8gi7hsp8XDi2VXlGClHdLtDHYyKCndojvF_qptwipn_VcYaZbfFmJKqFd6Cei9IpST9LSJGyDQ660m_FNtp8Vt7-0SecNzywUfS2xE4n2sFIozzvb0a3rGQ";

    List<String> elementos = new ArrayList<String>();

    AutoCompleteTextView itemListCode;

    ArrayAdapter<String> adapterItem;
    String codigoSelecionado = "";
    TextInputEditText inputNumeroCelular;
    TextInputEditText inputMonto;
    TextInputEditText inputReferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colaRequest = Volley.newRequestQueue(this);
        colaRequest.start();

        // asociar elementos
        inputNumeroCelular = (TextInputEditText)findViewById(R.id.inputNumeroCelular);
        inputMonto = (TextInputEditText)findViewById(R.id.inputMonto);
        inputReferencia = (TextInputEditText)findViewById(R.id.inputReferencia);

        itemListCode = findViewById(R.id.item_list_code);

        elementos.add("232");
        elementos.add("222");
        elementos.add("772");


        adapterItem = new ArrayAdapter<>(this, R.layout.list_item, elementos);

        itemListCode.setAdapter(adapterItem);

        getRegiones();

        itemListCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();
                codigoSelecionado = item;
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getRegiones() {
        JsonArrayRequest RequestRegiones = new JsonArrayRequest(
                Request.Method.GET,
                "https://pay.payphonetodoesposible.com/api/Regions",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        elementos.clear();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                elementos.add(String.valueOf(response.getJSONObject(i).getInt("prefixNumber")));
                                System.out.println(response.getJSONObject(i).getInt("prefixNumber"));
                            }

                        } catch (JSONException ex) {
                            System.out.println(ex.toString());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("Authorization", "Bearer " + TOKEN);

                        return params;
                    }
        };

        colaRequest.add(RequestRegiones);
    }

    public void pagar(View view) {
        Toast.makeText(getApplicationContext(), "codigo " + codigoSelecionado, Toast.LENGTH_LONG).show();
        System.out.println(codigoSelecionado);
        System.out.println(inputNumeroCelular.getText().toString());
        System.out.println(inputMonto.getText().toString());
        System.out.println(inputReferencia.getText().toString());

        // Establecinedo información de pago
        Sale infoPago = new Sale();
        infoPago.setCountryCode(codigoSelecionado);
        infoPago.setPhoneNumber(inputNumeroCelular.getText().toString());
        infoPago.setAmount(100);
        infoPago.setAmountWithoutTax(100);
        infoPago.setReference(inputReferencia.getText().toString());
        infoPago.setCurrency("USD");
        infoPago.setClientTransactionId(UUID.randomUUID().toString());

        // Convirtiendo los datos obj json
        JSONObject saleJson = new JSONObject();
        try {
            saleJson = infoPago.getJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(saleJson.toString());

        JsonObjectRequest pagarPayPhone = new JsonObjectRequest(
                Request.Method.POST,
                "https://pay.payphonetodoesposible.com/api/Sale",
                saleJson,// info del pago
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println("transactionId: " + response.getInt("transactionId"));
                            Toast.makeText(getApplicationContext(), "transactionId: " + response.getInt("transactionId"), Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            System.out.println(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Authorization", "Bearer " + TOKEN);

                return params;
            }
        };

        colaRequest.add(pagarPayPhone);
    }

    public void cancelarPago(View view) {
        Toast.makeText(getApplicationContext(), "Se a cancelado el pago", Toast.LENGTH_LONG).show();
    }
}
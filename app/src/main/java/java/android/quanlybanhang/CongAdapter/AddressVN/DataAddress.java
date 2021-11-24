package java.android.quanlybanhang.CongAdapter.AddressVN;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.android.quanlybanhang.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DataAddress {

    public ArrayList<DiaChi> readCompanyJSONFile(Context context) throws IOException, JSONException {

        String jsonText = readText(context, R.raw.diachi);

        ArrayList<DiaChi> listDiaChi = new ArrayList<>();

        JSONArray root = new JSONArray(jsonText);
        for (int i = 0; i < root.length(); i++) {
            JSONObject khuVuc = root.getJSONObject(i);
            String tinhTP = khuVuc.getString("name");
            JSONArray arrHuyen = khuVuc.getJSONArray("districts");
            ArrayList<Huyen> huyens = new ArrayList<>();
            for (int j = 0; j < arrHuyen.length(); j++) {
                JSONObject khuVucHuyen = arrHuyen.getJSONObject(j);
                String tenHuyen = khuVucHuyen.getString("name");
                JSONArray arrXa = khuVucHuyen.getJSONArray("wards");
                ArrayList<String> xas = new ArrayList<>();
                for (int k = 0; k < arrXa.length(); k++) {
                    JSONObject khuVucXa = arrXa.getJSONObject(k);
                    String xa = khuVucXa.getString("name");
                    xas.add(xa);
                }
                Huyen huyen = new Huyen(tenHuyen, xas);
                huyens.add(huyen);
            }

            DiaChi diaChi = new DiaChi(tinhTP, huyens);

            listDiaChi.add(diaChi);
        }

        return listDiaChi;
    }

    private String readText(Context context, int resId) throws IOException {
        InputStream is = context.getResources().openRawResource(resId);
        BufferedReader br= new BufferedReader(new InputStreamReader(is));
        StringBuilder sb= new StringBuilder();
        String s= null;
        while((  s = br.readLine())!=null) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}

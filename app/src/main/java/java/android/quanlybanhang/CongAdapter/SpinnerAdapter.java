package java.android.quanlybanhang.CongAdapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.android.quanlybanhang.Sonclass.DonGia;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<DonGia> {
    public SpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<DonGia> objects) {
        super(context, resource, textViewResourceId, objects);
    }

}

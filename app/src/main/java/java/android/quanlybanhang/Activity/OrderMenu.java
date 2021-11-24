    package java.android.quanlybanhang.Activity;

    import android.os.Bundle;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.ActionBar;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.appcompat.widget.Toolbar;
    import androidx.recyclerview.widget.LinearLayoutManager;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.ValueEventListener;
    import com.sa90.materialarcmenu.ArcMenu;

    import java.android.quanlybanhang.Ban.StaticBanModel;
    import java.android.quanlybanhang.Ban.StaticRvAdapter;
    import java.android.quanlybanhang.ChiTietSanPham.Interface_KhuVuc_ban;
    import java.android.quanlybanhang.KhuVuc.StaticModelKhuVuc;

    import java.android.quanlybanhang.R;
    import java.android.quanlybanhang.KhuVuc.StaticRvKhuVucAdapter;
    import java.util.ArrayList;

    public class OrderMenu extends AppCompatActivity implements Interface_KhuVuc_ban {
    private RecyclerView recyclerView,recyclerView2;//rv khu vuc ban
    private StaticRvKhuVucAdapter staticRvKhuVucAdapter;//adapter khu vuc
     ArrayList<StaticBanModel> items= new ArrayList<>();//araylist ban
     StaticRvAdapter staticRvAdapter;//adapter ban
     private DatabaseReference mDatabase;//khai bao database
     Interface_KhuVuc_ban interfaceKhuVucBan ; //ham get back
     private ArcMenu arcMenu;//arc menu material
        ArrayList<StaticModelKhuVuc> item;
     private Toolbar toolbar;//tool bar khai bao id

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_order_menu);
//menu toolbar
             toolbar = findViewById(R.id.toolbars);
             setSupportActionBar(toolbar);
//             viet su kien cho toolbar
            ActionBar actionBar = getSupportActionBar();
//Thiết lập tiêu đề nếu muốn
            actionBar.setTitle("");
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
//arc menu them ban
            arcMenu = findViewById(R.id.arcmenu);

//database realtime khu vuc
             mDatabase = FirebaseDatabase.getInstance().getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("khuvuc");
//            Log.d("ccc",FirebaseDatabase.getInstance().getReference("JxZOOK1RzcMM7pL5I6naGZfYSsu2").child("khuvuc").getKey());
            mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                     item = new ArrayList<>();

                    for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                        ArrayList<StaticBanModel> mm= new ArrayList<>();
                        String trangthai= postSnapshot.child("trangthai").getValue()+"";
                        String tenkhuvuc=postSnapshot.child("tenkhuvuc").getValue()+"";

//                        Log.d("TENBAN",postSnapshot.child("tenkhuvuc").getValue()+"");
                        DataSnapshot sss = postSnapshot.child("ban");
                        for (DataSnapshot aaa: sss.getChildren()){

                            String tenban= aaa.child("tenban").getValue()+"";
                            String trangthai1=aaa.child("trangthai").getValue()+"";
//                            Log.d("TENBAN",aaa.child("tenban").getValue()+"");
                            mm.add(new StaticBanModel(tenban,trangthai1));
                        }
                        StaticModelKhuVuc product = new StaticModelKhuVuc(tenkhuvuc,trangthai,mm);
                      Log.d("av",mm.size()+"avv");
                        item.add(product);
//                        Log.d("vvv",item.get(0).getStaticBanModels().get(0).getTenban());
                    }
                    recyclerView = findViewById(R.id.rv_1);
                    for(int i=0; i< item.size();i++){
                        Log.d("vvv",item.get(i).getTenkhuvuc());
                        for (int x=0;x< item.get(i).getStaticBanModels().size();x++){
                            Log.d("vvv",item.get(i).getStaticBanModels().get(x).getTenban());

                        }
                    }
                    staticRvKhuVucAdapter = new StaticRvKhuVucAdapter(item,OrderMenu.this,OrderMenu.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(OrderMenu.this,LinearLayoutManager.HORIZONTAL,false));
                    recyclerView.setAdapter(staticRvKhuVucAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//           final ArrayList<StaticModelKhuVuc> item = new ArrayList<>();
//            item.add(new StaticModelKhuVuc("khu Vuc 2","1"));
//            item.add(new StaticModelKhuVuc("khu Vuc 3","2"));
//            item.add(new StaticModelKhuVuc("khu Vuc 4","1"));
//            item.add(new StaticModelKhuVuc("khu Vuc 5","2"));

//            recyclerView = findViewById(R.id.rv_1);
//            staticRvKhuVucAdapter = new StaticRvKhuVucAdapter(item,this,this);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//            recyclerView.setAdapter(staticRvKhuVucAdapter);

            items = new ArrayList<>();
//            items.add(new StaticBanModel("aaaaa","1","aaaaaaaaa","11h"));
//            items.add(new StaticBanModel("ban 2","2","Long","2h"));
            recyclerView2 =findViewById(R.id.rv_2);
            staticRvAdapter = new StaticRvAdapter(items,OrderMenu.this,item);

            recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
            recyclerView2.setAdapter(staticRvAdapter);

            recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if(dy>0){
                        arcMenu.setVisibility(View.GONE);
                    }
                    else {
                        arcMenu.setVisibility(View.VISIBLE);
                    }
                }
            });

        }




        @Override
        public void GetBack(int position, ArrayList<StaticBanModel> items) {
         staticRvAdapter = new StaticRvAdapter(items,OrderMenu.this,item);
         staticRvAdapter.notifyDataSetChanged();
         recyclerView2.setAdapter(staticRvAdapter);

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu_main2, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            return super.onOptionsItemSelected(item);
        }
    }
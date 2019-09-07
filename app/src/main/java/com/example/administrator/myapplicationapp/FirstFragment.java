package com.example.administrator.myapplicationapp;
//首页fragment
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
<<<<<<< HEAD
        View v = inflater.inflate(R.layout.fragment_first, null);
        return v;
    }
=======
        View v = inflater.inflate(R.layout.fragment_first,null);
        Button button = v.findViewById(R.id.login_first);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }


>>>>>>> 8788e5baeb1ba29d2a86619aafded6bf3dd56d42
}

package com.cafenoion.grizzlymovie.fragment.ticket;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cafenoion.grizzlymovie.R;
import java.util.ArrayList;

public class TicketOrderFragment extends Fragment  {

    View root;

    private RecyclerView tRecyclerView;
    public TicketRecyclerAdapter tListAdapter = null;
    public ArrayList<TicketModel> tItem = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root =   inflater.inflate(R.layout.fragment_ticket_order, container, false);


        tRecyclerView = root.findViewById(R.id.movietickets);
        //tRecyclerView.setLayoutManager(new LinearLayoutManager(MovieTicketFragment.super.getActivity(), LinearLayoutManager.HORIZONTAL, true));
        tItem = new ArrayList<>();
        //movieTicketListAdapter = new MovieTicketListAdapter;


        tItem.add(new TicketModel
                (R.drawable.joke,"蟻人與黃蜂女","2016-07-25","故事接續在《美國隊長3：英雄內戰》之後，史考特朗恩因為參與了內戰判刑，帶上電子腳鐐，居家監禁，在父親和蟻人兩個角色中左支右絀。眼看刑期終於快服滿，皮姆博士和荷普又帶著危急的任務找上門，史考特不得不再次穿上蟻人裝束，與黃蜂女一起對抗來自過去的黑暗秘密。\n"));
        tItem.add(new TicketModel
                (R.drawable.joke,"蟻人與黃蜂女","2016-07-25","故事接續在《美國隊長3：英雄內戰》之後，史考特朗恩因為參與了內戰判刑，帶上電子腳鐐，居家監禁，在父親和蟻人兩個角色中左支右絀。眼看刑期終於快服滿，皮姆博士和荷普又帶著危急的任務找上門，史考特不得不再次穿上蟻人裝束，與黃蜂女一起對抗來自過去的黑暗秘密。\n"));
        tItem.add(new TicketModel
                (R.drawable.joke,"蟻人與黃蜂女","2016-07-25","故事接續在《美國隊長3：英雄內戰》之後，史考特朗恩因為參與了內戰判刑，帶上電子腳鐐，居家監禁，在父親和蟻人兩個角色中左支右絀。眼看刑期終於快服滿，皮姆博士和荷普又帶著危急的任務找上門，史考特不得不再次穿上蟻人裝束，與黃蜂女一起對抗來自過去的黑暗秘密。\n"));
        tItem.add(new TicketModel
                (R.drawable.joke,"蟻人與黃蜂女","2016-07-25","故事接續在《美國隊長3：英雄內戰》之後，史考特朗恩因為參與了內戰判刑，帶上電子腳鐐，居家監禁，在父親和蟻人兩個角色中左支右絀。眼看刑期終於快服滿，皮姆博士和荷普又帶著危急的任務找上門，史考特不得不再次穿上蟻人裝束，與黃蜂女一起對抗來自過去的黑暗秘密。\n"));


        tListAdapter = new TicketRecyclerAdapter( getActivity(),tItem );
        tRecyclerView.setAdapter(tListAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        tRecyclerView.setLayoutManager(layoutManager);
        tRecyclerView.setAdapter(tListAdapter);
        
        return root;
    }


}

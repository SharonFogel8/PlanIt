package com.sharon.planitall.fregments;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.sharon.planitall.Objects.Events;
import com.sharon.planitall.Objects.MyUser;
import com.sharon.planitall.R;
import com.sharon.planitall.activities.ActivityEnterApp;
import com.sharon.planitall.callbacks.Fragment_callback;
import com.sharon.planitall.callbacks.Observable;
import com.sharon.planitall.callbacks.get_users_callback;
import com.sharon.planitall.fregments.EventProfileFragment;
import com.sharon.planitall.tools.DataManager;
import com.sharon.planitall.tools.MyDB;

import java.util.ArrayList;
import java.util.HashMap;

public class EditEventProfile extends Fragment implements Observable {

    private View   view;
    private Events theEvent;
    private ImageButton editEventProfile_BTN_Editphoto;
    private TextInputEditText editEventProfile_ETXT_editName;
    private TextInputEditText            editEventProfile_ETXT_editLocation;
    private TextInputEditText            editEventProfile_ETXT_editBudget;
    private MaterialButton      editEventProfile_BTN_addPartner;
    private MaterialButton      editEventProfile_BTN_okEdit;
    private LinearLayoutCompat editEventProfile_LLY_addNewPartner;
    private MaterialButton      editEventProfile_BTN_cancelEdit;
    private EditText            editEventProfile_ETXT_partnerPhone;
    private MaterialButton      editEventProfile_BTN_addNewPartner;
    private MaterialButton editEventProfile_BTN_addNewCancel;
    private RelativeLayout      editEventProfile_RLY_foucus;
    private ArrayList<MyUser> myUsers;
    private TextView editEventProfile_TXT_warning;
    String phoneNumber;


    public EditEventProfile() {
        // Required empty public constructor
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_edit_event_profile, container, false);
        theEvent= DataManager.get_instance().getCurrentEvent();
        MyDB.getInstance().setGet_users_callback(get_users_callback);
        MyDB.getInstance().getUsers();
        findViews();
        initViews();


        return view;
    }

    private void initViews() {
        editEventProfile_BTN_cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment_callback!=null){
                    DataManager.get_instance().setCurrentEvent(theEvent);
                    EventProfileFragment eventProfileFragment= new EventProfileFragment();
                    fragment_callback.go_next(eventProfileFragment,eventProfileFragment);
                }
            }
        });
        editEventProfile_BTN_okEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.get_instance().getCurrentEvent().setName(""+editEventProfile_ETXT_editName.getText())
                        .setLocation(""+editEventProfile_ETXT_editLocation.getText())
                        .setBudget(Float.valueOf(""+editEventProfile_ETXT_editBudget.getText()));
                MyDB.getInstance().addEvent( DataManager.get_instance().getCurrentEvent().getMy_uid(), DataManager.get_instance().getCurrentEvent());
                if(fragment_callback!=null){
                    DataManager.get_instance().setCurrentEvent(theEvent);
                    EventProfileFragment eventProfileFragment= new EventProfileFragment();
                    fragment_callback.go_next(eventProfileFragment,eventProfileFragment);
                }
            }
        });
        editEventProfile_BTN_addPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEventProfile_LLY_addNewPartner.setVisibility(View.VISIBLE);
                editEventProfile_RLY_foucus.setVisibility(View.VISIBLE);
            }
        });
        editEventProfile_BTN_addNewPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MyLog","me: "+ DataManager.get_instance().getUser());
                phoneNumber= ""+editEventProfile_ETXT_partnerPhone.getText();
                MyUser user=findUser(phoneNumber);
                if(user!=null){
                    user.addNewEvent(theEvent.getMy_uid());
                    theEvent.addManager(user.getMy_uid());
                    MyDB.getInstance().addEvent(theEvent.getMy_uid(),theEvent);
                    MyDB.getInstance().addEventToAnotherAccount(user.getMy_uid(),theEvent);
                    editEventProfile_LLY_addNewPartner.setVisibility(View.INVISIBLE);
                    editEventProfile_RLY_foucus.setVisibility(View.INVISIBLE);
                }
                else{
                    editEventProfile_TXT_warning.setText("The user dosn't found!");
                }

            }
        });
        editEventProfile_BTN_addNewCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEventProfile_LLY_addNewPartner.setVisibility(View.INVISIBLE);
                editEventProfile_RLY_foucus.setVisibility(View.INVISIBLE);
            }
        });
    }

    private MyUser findUser(String phoneNumber) {
        String curPhone="+972"+phoneNumber;

        if(myUsers!=null){
            for (int i = 0; i < myUsers.size(); i++) {
                Log.d("MyLog",""+myUsers.get(i).getPhoneNum()+" "+myUsers.get(i).getName());
                if(myUsers.get(i).getPhoneNum().compareTo(curPhone)==0)
                    return myUsers.get(i);
            }
        }
        return null;

    }

    private void findViews() {
        editEventProfile_BTN_Editphoto= view.findViewById(R.id.editEventProfile_BTN_Editphoto);
        editEventProfile_BTN_Editphoto.setImageResource(theEvent.getImg());
        editEventProfile_ETXT_editName= view.findViewById(R.id.editEventProfile_ETXT_editName);
        editEventProfile_ETXT_editLocation= view.findViewById(R.id.editEventProfile_ETXT_editLocation);
        editEventProfile_ETXT_editBudget= view.findViewById(R.id.editEventProfile_ETXT_editBudget);
        editEventProfile_BTN_addPartner= view.findViewById(R.id.editEventProfile_BTN_addPartner);
        editEventProfile_BTN_okEdit= view.findViewById(R.id.editEventProfile_BTN_okEdit);
        editEventProfile_LLY_addNewPartner= view.findViewById(R.id.editEventProfile_LLY_addNewPartner);
        editEventProfile_BTN_cancelEdit= view.findViewById(R.id.editEventProfile_BTN_cancelEdit);
        editEventProfile_ETXT_partnerPhone= view.findViewById(R.id.editEventProfile_ETXT_partnerPhone);
        editEventProfile_BTN_addNewPartner= view.findViewById(R.id.editEventProfile_BTN_addNewPartner);
        editEventProfile_RLY_foucus= view.findViewById(R.id.editEventProfile_RLY_foucus);
        editEventProfile_TXT_warning= view.findViewById(R.id.editEventProfile_TXT_warning);
        editEventProfile_BTN_addNewCancel = view.findViewById(R.id.editEventProfile_BTN_addNewCancel);
        editEventProfile_ETXT_editName.setText(theEvent.getName());
        editEventProfile_ETXT_editLocation.setText(theEvent.getLocation());
        editEventProfile_ETXT_editBudget.setText(""+theEvent.getBudget());


    }

    private Fragment_callback fragment_callback;
    @Override
    public void setFragment_callback(Fragment_callback fragment_callback) {
        this.fragment_callback = fragment_callback;
    }
    private get_users_callback get_users_callback= new get_users_callback() {
        @Override
        public void getUsers(HashMap<String, MyUser> users) {
            if (users == null ){
                myUsers=new ArrayList<>();
            }
            else{
                ArrayList<MyUser> allUsers= new ArrayList<>(users.values());
                myUsers=allUsers;
            }
        }

        @Override
        public void getUser(MyUser user) {

        }
    };
}
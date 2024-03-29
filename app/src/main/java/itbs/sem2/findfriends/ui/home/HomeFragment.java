package itbs.sem2.findfriends.ui.home;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import itbs.sem2.findfriends.MainActivity;
import itbs.sem2.findfriends.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    //recupere le view
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //inflate:convertir le fichier xml HomeFragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnSendHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numero=binding.ednumeroHome.getText().toString();
                //envoyer un sms
                if(MainActivity.PERMISSION){
                SmsManager manager=SmsManager.getDefault();//sim par defaut
                manager.sendTextMessage(numero,
                        null,
                        "FindMe:envoyer moi votre position",
                        null,
                        null);
             }
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
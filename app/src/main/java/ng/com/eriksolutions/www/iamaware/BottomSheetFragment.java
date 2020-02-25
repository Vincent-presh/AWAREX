package ng.com.eriksolutions.www.iamaware;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;


@SuppressLint("ValidFragment")
public class BottomSheetFragment extends BottomSheetDialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
   Bitmap bitmap = null;
   String address;
   File mFile;
    private String[] values = new String[] { "Nigeria Police Force", "Nigerian Fire Service", "Federal Road Safety Corp",
            "Nigeria Police Anti Bomb Squad", "Federal Medical Centre"};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @SuppressLint("ValidFragment")
    public BottomSheetFragment(Bitmap bitmap, String address, File mFile) {
        // Required empty public constructor
        this.bitmap = bitmap;
        this.address = address;
        this.mFile = mFile;
    }

    // TODO: Rename and change types and number of parameters
    public static BottomSheetFragment newInstance(Bitmap bitmap, String address, File mFile) {
        BottomSheetFragment fragment = new BottomSheetFragment(bitmap, address, mFile);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        ImageView imageView = view.findViewById(R.id.taken_image);
        TextView textView = view.findViewById(R.id.address);
        VideoView videoView = view.findViewById(R.id.taken_video);
        textView.setText(address);
        if( bitmap!= null ) {
            imageView.setImageBitmap(bitmap);
            videoView.setVisibility(View.INVISIBLE);
        }
        else{
            imageView.setVisibility(View.INVISIBLE);
            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+mFile.getAbsolutePath());
            videoView.setVideoURI(uri);
        }
        final ListView listView = view.findViewById(R.id.lister);
        ListAdapter adapter = new ListAdapter(getActivity(),
                values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RelativeLayout item = view.findViewById(R.id.parent);
               item.setBackgroundColor(Color.parseColor("#f2f2f2"));
            }
        });
        Button submit= view.findViewById(R.id.submit);
        Button Cancel_btn = view.findViewById(R.id.close);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Hello....");
                builder.setMessage("We received your emergency message, we would verify and get to the location shortly.");
                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });
                builder.show();
            }
        });
        Cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}

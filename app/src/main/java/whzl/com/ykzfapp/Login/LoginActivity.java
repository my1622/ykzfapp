package whzl.com.ykzfapp.Login;


import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.base.BaseActivity;
import whzl.com.ykzfapp.utils.ToastUitl;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.username)
    AutoCompleteTextView username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.progress_btn)
    Button progressBtn;



    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    void onClick(){
        ToastUitl.showShort("dianji");
    }


}


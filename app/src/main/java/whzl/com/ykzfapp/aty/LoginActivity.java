package whzl.com.ykzfapp.aty;


import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindView;
import whzl.com.ykzfapp.R;
import whzl.com.ykzfapp.base.BaseActivity;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.username)
    AutoCompleteTextView username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.progress_btn)
    CircularProgressButton progressBtn;
    @BindView(R.id.phone_login_form)
    LinearLayout phoneLoginForm;
    @BindView(R.id.login_form)
    LinearLayout loginForm;

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


}

